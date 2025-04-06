package datalayer;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BreakLogDAO {

    private final Connection conn;

    public BreakLogDAO(Connection conn) {
        this.conn = conn;
    }

    // Save a complete BreakLog
    public void save(BreakLog breakLog) {
        String sql = "INSERT INTO break_logs (operator_id, vehicle_id, break_start, break_end) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, breakLog.getOperatorId());
            stmt.setInt(2, breakLog.getVehicleId());
            stmt.setTimestamp(3, Timestamp.valueOf(breakLog.getBreakStart()));
            stmt.setTimestamp(4, Timestamp.valueOf(breakLog.getBreakEnd()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving break log: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Start break: inserts a row with NULL break_end
    public void startBreak(int operatorId, int vehicleId, LocalDateTime breakStart) {
        String sql = "INSERT INTO break_logs (operator_id, vehicle_id, break_start, break_end) VALUES (?, ?, ?, NULL)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, operatorId);
            stmt.setInt(2, vehicleId);
            stmt.setTimestamp(3, Timestamp.valueOf(breakStart));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error starting break: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // End break: updates the latest row for this user & vehicle where break_end is NULL
    public void endBreak(int operatorId, int vehicleId, LocalDateTime breakEnd) {
        String sql = "UPDATE break_logs SET break_end = ? WHERE operator_id = ? AND vehicle_id = ? AND break_end IS NULL ORDER BY break_start DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(breakEnd));
            stmt.setInt(2, operatorId);
            stmt.setInt(3, vehicleId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error ending break: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Find a BreakLog by its ID
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
                    rs.getTimestamp("break_start").toLocalDateTime(),
                    rs.getTimestamp("break_end") != null ? rs.getTimestamp("break_end").toLocalDateTime() : null
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching break log by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Get all BreakLogs
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
                    rs.getTimestamp("break_start").toLocalDateTime(),
                    rs.getTimestamp("break_end") != null ? rs.getTimestamp("break_end").toLocalDateTime() : null
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all break logs: " + e.getMessage());
            e.printStackTrace();
        }
        return breakLogs;
    }
}
