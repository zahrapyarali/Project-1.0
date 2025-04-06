package datalayer;

import businesslayer.DAO;
import java.sql.*;
import java.util.*;

public class VehicleDAO implements DAO<Vehicle> {
    private Connection conn;

    public VehicleDAO(Connection conn) {
        this.conn = conn;
    }

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

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM vehicles WHERE vehicleId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
