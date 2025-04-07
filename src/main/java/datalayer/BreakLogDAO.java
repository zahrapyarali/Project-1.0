package datalayer;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreakLogDAO {

    private final Connection conn;

    public BreakLogDAO(Connection conn) {
        this.conn = conn;
    }

    // Save complete log manually (if needed)
    public void save(BreakLog breakLog) {
        String sql = "INSERT INTO break_logs (operator_id, vehicle_id, status, timestamp) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, breakLog.getOperatorId());
            stmt.setInt(2, breakLog.getVehicleId());
            stmt.setString(3, breakLog.getStatus());
            stmt.setTimestamp(4, Timestamp.valueOf(breakLog.getTimestamp()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving break log: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Start break with status = "Break"
    public void startBreak(int operatorId, int vehicleId, LocalDateTime time) {
        String sql = "INSERT INTO break_logs (operator_id, vehicle_id, status, timestamp) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, operatorId);
            stmt.setInt(2, vehicleId);
            stmt.setString(3, "Check-In");
            stmt.setTimestamp(4, Timestamp.valueOf(time));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error starting break: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // End break with status = "Out-of-Service"
    public void endBreak(int operatorId, int vehicleId, LocalDateTime time) {
        String sql = "INSERT INTO break_logs (operator_id, vehicle_id, status, timestamp) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, operatorId);
            stmt.setInt(2, vehicleId);
            stmt.setString(3, "Check-Out");
            stmt.setTimestamp(4, Timestamp.valueOf(time));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error ending break: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public BreakLog findById(int id) {
    String sql = "SELECT * FROM break_logs WHERE log_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new BreakLog(
                rs.getInt("log_id"),
                rs.getInt("operator_id"),
                rs.getInt("vehicle_id"),
                rs.getString("status"),
                rs.getTimestamp("timestamp").toLocalDateTime()
            );
        }
    } catch (SQLException e) {
        System.err.println("Error fetching break log by ID: " + e.getMessage());
        e.printStackTrace();
    }
    return null;
}


    // Get all logs
    public List<BreakLog> findAll() {
        List<BreakLog> breakLogs = new ArrayList<>();
        String sql = "SELECT * FROM break_logs";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                breakLogs.add(new BreakLog(
                    rs.getInt("log_id"),
                    rs.getInt("operator_id"),
                    rs.getInt("vehicle_id"),
                    rs.getString("status"),
                    rs.getTimestamp("timestamp").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all break logs: " + e.getMessage());
            e.printStackTrace();
        }
        return breakLogs;
    }
    // Log Out-of-Service as a separate entry
    public void logOutOfService(int operatorId, int vehicleId, LocalDateTime time) {
        String sql = "INSERT INTO break_logs (operator_id, vehicle_id, status, timestamp) VALUES (?, ?, 'Out-of-Service', ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, operatorId);
            stmt.setInt(2, vehicleId);
            stmt.setTimestamp(3, Timestamp.valueOf(time));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error logging out-of-service: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public Map<Integer, Integer> getStatusCount(String status) {
        Map<Integer, Integer> statusCount = new HashMap<>();
        String sql = "SELECT operator_id, COUNT(*) AS count FROM break_logs WHERE status = ? GROUP BY operator_id";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int operatorId = rs.getInt("operator_id");
                int count = rs.getInt("count");
                statusCount.put(operatorId, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statusCount;
    }


}
