package datalayer;

import businesslayer.DAO;
import businesslayer.MaintenanceObserver;
import businesslayer.MaintenanceSubject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory class to create Data Access Objects (DAO).
 * Implements MaintenanceSubject to notify observers of maintenance updates.
 */
public class DAOFactory implements MaintenanceSubject {

    private List<MaintenanceObserver> observers;

    /**
     * Default constructor.
     * Initializes the list of observers.
     */
    public DAOFactory() {
        observers = new ArrayList<>();
    }

    /**
     * Retrieves a database connection from the DataSource.
     *
     * @return a new {@link Connection} object.
     * @throws SQLException if a database access error occurs.
     */
    private Connection getConnection() throws SQLException {
        // Retrieve connection from DataSource
        return DataSource.getInstance().createConnection();
    }

    /**
     * Factory method to obtain a specific DAO implementation based on the type.
     *
     * @param type the type of DAO required ("vehicle" or "user").
     * @return an instance of {@link DAO} for the specified type.
     * @throws RuntimeException if an unknown DAO type is requested or a connection error occurs.
     */
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

    /**
     * Attaches an observer to the list of maintenance observers.
     *
     * @param observer the {@link MaintenanceObserver} to attach.
     */
    @Override
    public void attach(MaintenanceObserver observer) {
        observers.add(observer);
    }

    /**
     * Detaches an observer from the list of maintenance observers.
     *
     * @param observer the {@link MaintenanceObserver} to detach.
     */
    @Override
    public void detach(MaintenanceObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all attached observers with a sample wear level.
     */
    @Override
    public void notifyObservers() {
        for (MaintenanceObserver observer : observers) {
            observer.update(75.0);  // Example of updating with a sample wear level
        }
    }

    /**
     * Updates the wear level and notifies all observers.
     *
     * @param level the new wear level to update.
     */
    public void updateWear(double level) {
        System.out.println("Updating wear level: " + level);
        notifyObservers();
    }
}
