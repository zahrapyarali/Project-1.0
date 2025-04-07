/* File: FuelConsumptionReportService.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This service class generates fuel or energy usage reports 
 *              for different public transit vehicles. It calculates estimated 
 *              consumption using the appropriate strategy pattern implementation 
 *              and compares it with predefined thresholds to flag alerts.
 */

package businesslayer;

import datalayer.GPSTracking;
import datalayer.GPSTrackingDAO;
import datalayer.Vehicle;
import datalayer.VehicleDAO;

import java.sql.Connection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The {@code FuelConsumptionReportService} class is responsible for generating
 * fuel and energy usage reports for all vehicles in the system.
 *
 * <p>It uses the {@link FuelConsumptionStrategy} design pattern to apply
 * different consumption calculations based on the vehicle type.
 * The service also estimates distance from GPS logs and determines if
 * an alert should be raised based on consumption thresholds.
 * 
 */
public class FuelConsumptionReportService {

    private final VehicleDAO vehicleDAO;
    private final GPSTrackingDAO gpsTrackingDAO;

    /**
     * Constructs the report service with DAOs for vehicle and GPS data.
     *
     * @param conn the active database connection
     */
    public FuelConsumptionReportService(Connection conn) {
        this.vehicleDAO = new VehicleDAO(conn);
        this.gpsTrackingDAO = new GPSTrackingDAO(conn);
    }

    /**
     * Generates a fuel or energy usage report for all vehicles.
     * Uses departure/arrival timestamps to estimate distances, applies
     * the appropriate fuel strategy, and flags overconsumption alerts.
     *
     * @return a list of {@code FuelReportEntry} containing the vehicle report data
     */
    public List<FuelReportEntry> generateReport() {
        List<FuelReportEntry> report = new ArrayList<>();

        List<Vehicle> vehicles = vehicleDAO.findAll();
        for (Vehicle v : vehicles) {
            List<GPSTracking> logs = gpsTrackingDAO.findByVehicleId(v.getId());

            if (logs == null || logs.size() < 2) {
                continue;
            }

            logs.sort(Comparator.comparing(GPSTracking::getTimestamp));

            double totalDistance = 0.0;
            for (int i = 1; i < logs.size(); i++) {
                GPSTracking prev = logs.get(i - 1);
                GPSTracking curr = logs.get(i);

                if (prev.getStatus().equals("Departure") && curr.getStatus().equals("Arrival")) {
                    long minutes = Duration.between(prev.getTimestamp(), curr.getTimestamp()).toMinutes();
                    double distance = minutes / 2.0; // Estimated: 1 km per 2 minutes
                    totalDistance += distance;
                }
            }

            FuelConsumptionStrategy strategy;
            double threshold;
            switch (v.getType()) {
                case "Diesel Bus" -> {
                    strategy = new DieselBusStrategy();
                    threshold = 15.0; // Liters
                }
                case "Electric Light Rail" -> {
                    strategy = new ElectricRailStrategy();
                    threshold = 25.0; // kWh
                }
                case "Diesel-Electric Train" -> {
                    strategy = new DieselElectricTrainStrategy();
                    threshold = 30.0; // Liters
                }
                default -> {
                    continue;
                }
            }

            double consumption = strategy.calculateConsumption(totalDistance);
            boolean alert = consumption > threshold;

            report.add(new FuelReportEntry(
                    v.getId(),
                    v.getType(),
                    v.getFuelType(),
                    v.getNumber(),
                    totalDistance,
                    consumption,
                    alert
            ));
        }

        return report;
    }
}
