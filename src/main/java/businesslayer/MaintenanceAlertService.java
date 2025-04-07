/* File: MaintenanceAlertService.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This service class generates maintenance alerts based on component
 *              wear data. It uses the Observer design pattern to notify registered
 *              observers and collect alerts for components that exceed usage thresholds.
 */

package businesslayer;

import datalayer.MaintenanceLogDAO;
import datalayer.DataSource;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code MaintenanceAlertService} class manages the process of monitoring
 * maintenance logs and notifying observers about components that exceed wear thresholds.
 *
 * <p>It leverages the {@link VehicleWearMonitor} as the subject and any
 * {@link MaintenanceObserver} (such as {@link MaintenanceAlert}) as observers.
 *
 * <p>This service encapsulates the logic for collecting alerts from logs and
 * returning them as a list of messages for display or logging.
 * 
 * <p><strong>Design Pattern:</strong> Observer
 */
public class MaintenanceAlertService {

    private final MaintenanceLogDAO dao;
    private final VehicleWearMonitor monitor;

    /**
     * Constructs the service using a database connection.
     *
     * @param conn the active database connection
     */
    public MaintenanceAlertService(Connection conn) {
        this.dao = new MaintenanceLogDAO(conn);
        this.monitor = new VehicleWearMonitor();
    }

    /**
     * Attaches the given observer and generates maintenance alerts
     * based on all maintenance logs found in the database.
     *
     * @param observer the observer to notify (e.g., {@link MaintenanceAlert})
     * @return a list of alert messages for logs that exceeded thresholds
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
