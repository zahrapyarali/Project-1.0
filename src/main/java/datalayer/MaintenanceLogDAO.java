package datalayer;

import businesslayer.MaintenanceLog;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceLogDAO {

    private Connection conn;

    public MaintenanceLogDAO(Connection conn) {
        this.conn = conn;
    }

    // Save a new MaintenanceLog record
    public void save(MaintenanceLog maintenanceLog) {
        String sql = "INSERT INTO maintenance_log (vehicle_id, cost, date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maintenanceLog.getVehicleId());
            stmt.setDouble(2, maintenanceLog.getCost());
            stmt.setTimestamp(3, Timestamp.valueOf(maintenanceLog.getDate()));  // Convert LocalDateTime to Timestamp
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find a MaintenanceLog record by its ID
    public MaintenanceLog findById(int id) {
        String sql = "SELECT * FROM maintenance_log WHERE maintenance_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MaintenanceLog(
                    rs.getInt("maintenance_id"),
                    rs.getInt("vehicle_id"),
                    rs.getDouble("cost"),
                    rs.getTimestamp("date").toLocalDateTime()  // Convert Timestamp to LocalDateTime
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Find all MaintenanceLog records
    public List<MaintenanceLog> findAll() {
        List<MaintenanceLog> maintenanceLogs = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_log";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                maintenanceLogs.add(new MaintenanceLog(
                    rs.getInt("maintenance_id"),
                    rs.getInt("vehicle_id"),
                    rs.getDouble("cost"),
                    rs.getTimestamp("date").toLocalDateTime()  // Convert Timestamp to LocalDateTime
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maintenanceLogs;
    }
}
