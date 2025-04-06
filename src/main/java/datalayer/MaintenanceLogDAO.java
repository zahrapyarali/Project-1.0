package datalayer;

import businesslayer.MaintenanceLog;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for interacting with the maintenance_logs table.
 */
public class MaintenanceLogDAO {

    private final Connection conn;

    public MaintenanceLogDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts a new maintenance log entry into the database.
     */
    public void save(MaintenanceLog log) {
        String sql = "INSERT INTO maintenance_logs (vehicle_id, component, hours_used, threshold) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, log.getVehicleId());
            stmt.setString(2, log.getComponent());
            stmt.setInt(3, log.getHoursUsed());
            stmt.setInt(4, log.getThreshold());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all maintenance logs from the database.
     */
    public List<MaintenanceLog> findAll() {
        List<MaintenanceLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_logs";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MaintenanceLog log = new MaintenanceLog(
                    rs.getInt("log_id"),
                    rs.getInt("vehicle_id"),
                    rs.getString("component"),
                    rs.getInt("hours_used"),
                    rs.getInt("threshold"),
                    rs.getBoolean("is_scheduled")
                );
                logs.add(log);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    /**
     * Returns only the logs where wear exceeds the threshold (used for triggering alerts).
     */
    public List<MaintenanceLog> findOverdueAlerts() {
        List<MaintenanceLog> alerts = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_logs WHERE hours_used >= threshold";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MaintenanceLog log = new MaintenanceLog(
                    rs.getInt("log_id"),
                    rs.getInt("vehicle_id"),
                    rs.getString("component"),
                    rs.getInt("hours_used"),
                    rs.getInt("threshold"),
                    rs.getBoolean("is_scheduled")
                );
                alerts.add(log);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alerts;
    }
    
    public void scheduleMaintenance(int logId) {
        String sql = "UPDATE maintenance_logs SET is_scheduled = true WHERE log_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, logId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
