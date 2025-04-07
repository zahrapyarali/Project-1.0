package datalayer;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object (DAO) for managing break logs in the database.
 * Handles operations such as saving, retrieving, and logging operator breaks and statuses.
 */
public class BreakLogDAO {

    /**
     * Database connection object.
     */
    private final Connection conn;

    /**
     * Constructor to initialize BreakLogDAO with a database connection.
     * @param conn the database connection to use
     */
    public BreakLogDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Saves a complete BreakLog entry to the database.
     * @param breakLog the BreakLog object containing break details
     */
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

    /**
     * Logs the start of a break for an operator and vehicle with status "Check-In".
     * @param operatorId the ID of the operator
     * @param vehicleId the ID of the vehicle
     * @param time the timestamp of the break start
     */
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

    /**
     * Logs the end of a break for an operator and vehicle with status "Check-Out".
     * @param operatorId the ID of the operator
     * @param vehicleId the ID of the vehicle
     * @param time the timestamp of the break end
     */
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

    /**
     * Finds a break log by its ID.
     * @param id the ID of the break log
     * @return the BreakLog object if found, otherwise null
     */
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

    /**
     * Retrieves all break logs from the database.
     * @return a list of BreakLog objects
     */
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

    /**
     * Logs an "Out-of-Service" status entry for an operator and vehicle.
     * @param operatorId the ID of the operator
     * @param vehicleId the ID of the vehicle
     * @param time the timestamp of the status log
     */
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

    /**
     * Retrieves the count of logs for a given status grouped by operator ID.
     * @param status the status to count (e.g., "Check-In", "Check-Out", "Out-of-Service")
     * @return a map where the key is the operator ID and the value is the count of records
     */
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
