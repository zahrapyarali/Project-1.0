package datalayer;

import businesslayer.DAO;
import java.sql.*;
import java.util.*;

/**
 * Data Access Object (DAO) implementation for Vehicle entity.
 * Provides methods to perform CRUD operations on the vehicles table.
 */
public class VehicleDAO implements DAO<Vehicle> {
    private Connection conn;

    /**
     * Constructor to initialize VehicleDAO with a database connection.
     *
     * @param conn the database connection
     */
    public VehicleDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts a new Vehicle record into the vehicles table.
     *
     * @param vehicle the Vehicle object to insert
     */
    @Override
    public void insert(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (vehicleType, vehicleNumber, fuelType, maxPassengers, assignedRoute, manager_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getType());
            stmt.setString(2, vehicle.getNumber());
            stmt.setString(3, vehicle.getFuelType());
            stmt.setInt(4, vehicle.getMaxPassengers());
            stmt.setString(5, vehicle.getCurrentAssignedRoute());
            stmt.setInt(6, vehicle.getManagerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing Vehicle record in the vehicles table.
     *
     * @param vehicle the Vehicle object with updated details
     */
    @Override
    public void update(Vehicle vehicle) {
        String sql = "UPDATE vehicles SET vehicleType=?, vehicleNumber=?, fuelType=?, maxPassengers=?, assignedRoute=?, manager_id=? WHERE vehicleId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getType());
            stmt.setString(2, vehicle.getNumber());
            stmt.setString(3, vehicle.getFuelType());
            stmt.setInt(4, vehicle.getMaxPassengers());
            stmt.setString(5, vehicle.getCurrentAssignedRoute());
            stmt.setInt(6, vehicle.getManagerId());
            stmt.setInt(7, vehicle.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a Vehicle record and its associated data from related tables.
     *
     * @param id the vehicle ID to delete
     */
    @Override
    public void delete(int id) {
        String sql_vehicles         = "DELETE FROM vehicles WHERE vehicleId=?";
        String sql_gps_data         = "DELETE FROM gps_data WHERE vehicle_id=?";
        String sql_break_logs       = "DELETE FROM break_logs WHERE vehicle_id=?";
        String sql_maintenance_logs = "DELETE FROM maintenance_logs WHERE vehicle_id=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql_gps_data)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
                
        try (PreparedStatement stmt = conn.prepareStatement(sql_break_logs)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        try (PreparedStatement stmt = conn.prepareStatement(sql_maintenance_logs)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }         
        
        try (PreparedStatement stmt = conn.prepareStatement(sql_vehicles)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }

    /**
     * Finds a Vehicle by its ID.
     *
     * @param id the vehicle ID to search for
     * @return the Vehicle object if found, otherwise null
     */
    @Override
    public Vehicle findById(int id) {
        String sql = "SELECT * FROM vehicles WHERE vehicleId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Vehicle(
                    rs.getInt("vehicleId"),
                    rs.getString("vehicleType"),
                    rs.getString("vehicleNumber"),
                    rs.getString("fuelType"),
                    rs.getInt("maxPassengers"),
                    rs.getString("assignedRoute"),
                    rs.getInt("manager_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all Vehicle records from the vehicles table.
     *
     * @return a list of all Vehicle objects
     */
    @Override
    public List<Vehicle> findAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(new Vehicle(
                    rs.getInt("vehicleId"),
                    rs.getString("vehicleType"),
                    rs.getString("vehicleNumber"),
                    rs.getString("fuelType"),
                    rs.getInt("maxPassengers"),
                    rs.getString("assignedRoute"),
                    rs.getInt("manager_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
}
