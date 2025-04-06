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

            if (logs == null || logs.size() < 2) continue;

            logs.sort(Comparator.comparing(GPSTracking::getTimestamp));

            // Debug output
            System.out.println("Processing Vehicle ID: " + v.getId() + " (" + v.getNumber() + ")");
            System.out.println("Log entries: " + logs.size());

            double totalDistance = 0.0;

            for (int i = 1; i < logs.size(); i++) {
                GPSTracking prev = logs.get(i - 1);
                GPSTracking curr = logs.get(i);

                String prevStatus = prev.getStatus() != null ? prev.getStatus().toLowerCase() : "";
                String currStatus = curr.getStatus() != null ? curr.getStatus().toLowerCase() : "";

                if (prevStatus.equals("departure") && currStatus.equals("arrival")) {
                    Duration duration = Duration.between(prev.getTimestamp(), curr.getTimestamp());
                    long minutes = duration.toMinutes();
                    double distance = minutes / 2.0; // Estimated: 1 km per 2 minutes
                    totalDistance += distance;

                    // Debug entry
                    System.out.println("Matched pair: Departure@" + prev.getTimestamp() + " to Arrival@" + curr.getTimestamp() + " => " + distance + " km");
                }
            }

            if (totalDistance == 0.0) continue; // Skip if no valid travel found

            FuelConsumptionStrategy strategy;
            switch (v.getType()) {
                case "Diesel Bus":
                    strategy = new DieselBusStrategy();
                    break;
                case "Electric Light Rail":
                    strategy = new ElectricRailStrategy();
                    break;
                case "Diesel-Electric Train":
                    strategy = new DieselElectricTrainStrategy();
                    break;
                default:
                    continue; // Unknown type
            }

            double consumption = strategy.calculateConsumption(totalDistance);

            report.add(new FuelReportEntry(
                v.getId(),
                v.getType(),
                v.getFuelType(),
                v.getNumber(),
                totalDistance,
                consumption
            ));
        }

        return report;
    }
}
