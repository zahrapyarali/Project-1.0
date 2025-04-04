package datalayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BreakLogDAO {

    private Connection conn;

    public BreakLogDAO(Connection conn) {
        this.conn = conn;
    }

    // Save a BreakLog to the database
    public void save(BreakLog breakLog) {
        String sql = "INSERT INTO break_log (operator_id, vehicle_id, break_start, break_end) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, breakLog.getOperatorId());
            stmt.setInt(2, breakLog.getVehicleId());
            stmt.setTimestamp(3, Timestamp.valueOf(breakLog.getBreakStart())); // Converting LocalDateTime to Timestamp
            stmt.setTimestamp(4, Timestamp.valueOf(breakLog.getBreakEnd()));     // Converting LocalDateTime to Timestamp
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find a BreakLog by its ID
    public BreakLog findById(int id) {
        String sql = "SELECT * FROM break_log WHERE log_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new BreakLog(
                    rs.getInt("log_id"),
                    rs.getInt("operator_id"),
                    rs.getInt("vehicle_id"),
                    rs.getTimestamp("break_start").toLocalDateTime(), // Convert Timestamp to LocalDateTime
                    rs.getTimestamp("break_end").toLocalDateTime()    // Convert Timestamp to LocalDateTime
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Find all BreakLogs
    public List<BreakLog> findAll() {
        List<BreakLog> breakLogs = new ArrayList<>();
        String sql = "SELECT * FROM break_log";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                breakLogs.add(new BreakLog(
                    rs.getInt("log_id"),
                    rs.getInt("operator_id"),
                    rs.getInt("vehicle_id"),
                    rs.getTimestamp("break_start").toLocalDateTime(), // Convert Timestamp to LocalDateTime
                    rs.getTimestamp("break_end").toLocalDateTime()    // Convert Timestamp to LocalDateTime
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return breakLogs;
    }
}
