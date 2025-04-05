package datalayer;

import businesslayer.DAO;
import businesslayer.MaintenanceObserver;
import businesslayer.MaintenanceSubject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOFactory implements MaintenanceSubject {

    private List<MaintenanceObserver> observers;

    // Default constructor - connection is now fetched from DataSource
    public DAOFactory() {
        observers = new ArrayList<>();
    }

    // Method to get the database connection using DataSource
    private Connection getConnection() throws SQLException {
        // Retrieve connection from DataSource
        return DataSource.getInstance().createConnection();
    }

    // Factory method to return the appropriate DAO
    public DAO getDAO(String type) {
        try (Connection conn = getConnection()) {  // Automatically close connection after use
            switch (type.toLowerCase()) {
                case "vehicle":
                    return new VehicleDAO(conn);  // Pass connection to VehicleDAO
                case "user":
                    return new UserDAO(conn);  // Pass connection to UserDAO
                default:
                    throw new IllegalArgumentException("Unknown DAO type: " + type);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error obtaining connection from DataSource", e);
        }
    }

    // Observer Pattern Methods (Unchanged)
    @Override
    public void attach(MaintenanceObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(MaintenanceObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (MaintenanceObserver observer : observers) {
            observer.update(75.0);  // Example of updating with a sample wear level
        }
    }

    public void updateWear(double level) {
        System.out.println("Updating wear level: " + level);
        notifyObservers();
    }
}
