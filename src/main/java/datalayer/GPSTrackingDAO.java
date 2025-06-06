package datalayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Data Access Object (DAO) for managing GPS tracking records in the database.
 * 
 * @author Ambika Gadhvi, Saleha Qareen, Sarra Derdar, Zahra Pyarali
 */
public class GPSTrackingDAO {

    private Connection conn;

    /**
     * Constructor to initialize the DAO with a database connection.
     * @param conn the database connection
     */
    public GPSTrackingDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts a new GPS tracking record into the database.
     * @param gps the GPS tracking data to insert
     * @throws SQLException if a database access error occurs
     */
    public void insertTracking(GPSTracking gps) throws SQLException {
        String sql = "INSERT INTO gps_data (vehicle_id, location, timestamp, status) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, gps.getVehicleId());
        stmt.setString(2, gps.getLocation());
        stmt.setTimestamp(3, java.sql.Timestamp.valueOf(gps.getTimestamp()));
        stmt.setString(4, gps.getStatus());
        stmt.executeUpdate();
        stmt.close();
    }

    /**
     * Saves a new GPS tracking record in the database.
     * @param gpsTracking the GPS tracking data to save
     */
    public void save(GPSTracking gpsTracking) {
        String sql = "INSERT INTO gps_data (vehicle_id, location, timestamp, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gpsTracking.getVehicleId());
            stmt.setString(2, gpsTracking.getLocation());
            stmt.setTimestamp(3, Timestamp.valueOf(gpsTracking.getTimestamp()));
            stmt.setString(4, gpsTracking.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds a GPS tracking record by its tracking ID.
     * @param id the tracking ID
     * @return the GPS tracking record, or null if not found
     */
    public GPSTracking findById(int id) {
        String sql = "SELECT * FROM gps_data WHERE tracking_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new GPSTracking(
                    rs.getInt("tracking_id"),
                    rs.getInt("vehicle_id"),
                    rs.getString("location"),
                    rs.getTimestamp("timestamp").toLocalDateTime(),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all GPS tracking records from the database.
     * @return a list of all GPS tracking records
     */
    public List<GPSTracking> findAll() {
        List<GPSTracking> gpsTrackings = new ArrayList<>();
        String sql = "SELECT * FROM gps_data";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                gpsTrackings.add(new GPSTracking(
                    rs.getInt("tracking_id"),
                    rs.getInt("vehicle_id"),
                    rs.getString("location"),
                    rs.getTimestamp("timestamp").toLocalDateTime(),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gpsTrackings;
    }

    /**
     * Retrieves GPS tracking records for a specific vehicle ID.
     * @param vehicleId the vehicle ID
     * @return a list of GPS tracking records for the specified vehicle
     */
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
                    rs.getString("location"),
                    rs.getTimestamp("timestamp").toLocalDateTime(),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gpsTrackings;
    }

    /**
     * Deletes a GPS tracking record by its tracking ID.
     * @param id the tracking ID of the record to delete
     */
    public void deleteById(int id) {
        String sql = "DELETE FROM gps_data WHERE tracking_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves arrival and departure logs for each vehicle and location.
     * @return a list of vehicle station logs with arrival and departure times
     */
    public List<VehicleStationLog> getArrivalDepartureLogs() {
        List<VehicleStationLog> logs = new ArrayList<>();
        String sql = "SELECT vehicle_id, location, " +
                     "MIN(timestamp) AS arrival_time, MAX(timestamp) AS departure_time " +
                     "FROM gps_data GROUP BY vehicle_id, location";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                logs.add(new VehicleStationLog(
                    rs.getInt("vehicle_id"),
                    rs.getString("location"),
                    rs.getTimestamp("arrival_time").toLocalDateTime(),
                    rs.getTimestamp("departure_time").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    /**
     * Updates an existing GPS tracking record in the database.
     * @param gpsTracking the GPS tracking record to update
     */
    public void update(GPSTracking gpsTracking) {
        String sql = "UPDATE gps_data SET vehicle_id = ?, location = ?, timestamp = ?, status = ? WHERE tracking_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gpsTracking.getVehicleId());
            stmt.setString(2, gpsTracking.getLocation());
            stmt.setTimestamp(3, Timestamp.valueOf(gpsTracking.getTimestamp()));
            stmt.setString(4, gpsTracking.getStatus());
            stmt.setInt(5, gpsTracking.getTrackingId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public double calculateTotalDistance(int vehicleId) {
        double total = 0;
        String sql = "SELECT timestamp, status FROM gps_data WHERE vehicle_id = ? ORDER BY timestamp";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();

            LocalDateTime departTime = null;

            while (rs.next()) {
                String status = rs.getString("status");
                LocalDateTime time = rs.getTimestamp("timestamp").toLocalDateTime();

                if ("Departure".equalsIgnoreCase(status)) {
                    departTime = time;
                } else if ("Arrival".equalsIgnoreCase(status) && departTime != null) {
                    long minutes = java.time.Duration.between(departTime, time).toMinutes();
                    total += minutes; // Treat minutes as "distance" for reporting
                    departTime = null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

}
