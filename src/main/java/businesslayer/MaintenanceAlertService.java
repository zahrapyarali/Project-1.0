package businesslayer;

import datalayer.MaintenanceLogDAO;
import datalayer.DataSource;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to generate maintenance alerts based on wear levels
 * and notify observers (implements Observer Pattern).
 * 
 * @author Saleha
 */
public class MaintenanceAlertService {

    private final MaintenanceLogDAO dao;
    private final VehicleWearMonitor monitor;

    public MaintenanceAlertService(Connection conn) {
        this.dao = new MaintenanceLogDAO(conn);
        this.monitor = new VehicleWearMonitor();
    }

    /**
     * Attaches an observer and generates alerts for all overdue logs.
     *
     * @param observer the observer to notify
     * @return a list of alert messages
     */
    public List<String> generateAlerts(MaintenanceObserver observer) {
        List<String> messages = new ArrayList<>();

        // Attach observer
        monitor.attach(observer);

        // Fetch all logs
        List<MaintenanceLog> logs = dao.findAll();

        // Notify observer for each log
        for (MaintenanceLog log : logs) {
            observer.update(log.getVehicleId(), log.getComponent(), log.getHoursUsed(), log.getThreshold());

            if (log.getHoursUsed() >= log.getThreshold() && observer instanceof MaintenanceAlert) {
                messages.add(((MaintenanceAlert) observer).getAlertMessage());
            }
        }

        return messages;
    }
}
