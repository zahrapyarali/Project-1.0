package datalayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GPSTrackingDAO {

    private Connection conn;

    public GPSTrackingDAO(Connection conn) {
        this.conn = conn;
    }

    // Save a new GPS tracking record
    public void save(GPSTracking gpsTracking) {
        String sql = "INSERT INTO gps_tracking (vehicle_id, location, timestamp) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gpsTracking.getVehicleId());
            stmt.setString(2, gpsTracking.getLocation());
            stmt.setTimestamp(3, Timestamp.valueOf(gpsTracking.getTimestamp()));  // Convert LocalDateTime to Timestamp
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find a GPS tracking record by its ID
    public GPSTracking findById(int id) {
        String sql = "SELECT * FROM gps_tracking WHERE tracking_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new GPSTracking(
                    rs.getInt("tracking_id"),
                    rs.getInt("vehicle_id"),
                    rs.getString("location"),
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
        String sql = "SELECT * FROM gps_tracking";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                gpsTrackings.add(new GPSTracking(
                    rs.getInt("tracking_id"),
                    rs.getInt("vehicle_id"),
                    rs.getString("location"),
                    rs.getTimestamp("timestamp").toLocalDateTime()  // Convert Timestamp to LocalDateTime
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gpsTrackings;
    }
}
