package datalayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


public class GPSTrackingDAO {

    private Connection conn;

    public GPSTrackingDAO(Connection conn) {
        this.conn = conn;
    }

    // Save a new GPS tracking record
    public void save(GPSTracking gpsTracking) {
        String sql = "INSERT INTO gps_data (vehicle_id, location, timestamp) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gpsTracking.getVehicleId());
            stmt.setString(2, gpsTracking.getLocation());  // Store location as string
            stmt.setTimestamp(3, Timestamp.valueOf(gpsTracking.getTimestamp()));  // Convert LocalDateTime to Timestamp
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find a GPS tracking record by its ID
    public GPSTracking findById(int id) {
        String sql = "SELECT * FROM gps_data WHERE tracking_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new GPSTracking(
                    rs.getInt("tracking_id"),
                    rs.getInt("vehicle_id"),
                    rs.getString("location"),  // Retrieve location as string
                    rs.getTimestamp("timestamp").toLocalDateTime()  // Convert Timestamp to LocalDateTime
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Find all GPS tracking records
    public List<GPSTracking> findAll() {
        List<GPSTracking> gpsTrackings = new ArrayList<>();
        String sql = "SELECT * FROM gps_data";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                gpsTrackings.add(new GPSTracking(
                    rs.getInt("tracking_id"),
                    rs.getInt("vehicle_id"),
                    rs.getString("location"),  // Retrieve location as string
                    rs.getTimestamp("timestamp").toLocalDateTime()  // Convert Timestamp to LocalDateTime
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gpsTrackings;
    }

    // Find GPS tracking records for a specific vehicle ID
    public List<GPSTracking> findByVehicleId(int vehicleId) {
        List<GPSTracking> gpsTrackings = new ArrayList<>();
        String sql = "SELECT * FROM gps_data WHERE vehicle_id = ? ORDER BY timestamp DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                gpsTrackings.add(new GPSTracking(
                    rs.getInt("tracking_id"),
                    rs.getInt("vehicle_id"),
                    rs.getString("location"),  // Retrieve location as string
                rs.getTimestamp("timestamp").toLocalDateTime()  // Convert Timestamp to LocalDateTime
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gpsTrackings;
    }

    // Delete a GPS tracking record by its ID
    public void deleteById(int id) {
        String sql = "DELETE FROM gps_data WHERE tracking_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update a GPS tracking record
    public void update(GPSTracking gpsTracking) {
        String sql = "UPDATE gps_data SET vehicle_id = ?, location = ?, timestamp = ? WHERE tracking_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gpsTracking.getVehicleId());
            stmt.setString(2, gpsTracking.getLocation());  // Update location
           stmt.setTimestamp(5, Timestamp.valueOf(gpsTracking.getTimestamp()));  // Convert LocalDateTime to Timestamp
            stmt.setInt(6, gpsTracking.getTrackingId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
