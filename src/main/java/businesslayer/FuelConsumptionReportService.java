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
 * Service class to generate fuel/energy consumption reports
 * for different types of public transit vehicles.
 */
public class FuelConsumptionReportService {

    private final VehicleDAO vehicleDAO;
    private final GPSTrackingDAO gpsTrackingDAO;

    public FuelConsumptionReportService(Connection conn) {
        this.vehicleDAO = new VehicleDAO(conn);
        this.gpsTrackingDAO = new GPSTrackingDAO(conn);
    }

    /**
     * Generates a fuel/energy usage report for all vehicles.
     *
     * @return List of FuelReportEntry containing usage stats
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
                    alert // Pass alert flag here
            ));
        }

        return report;
    }
}
