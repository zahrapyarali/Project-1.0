package datalayer;


import businesslayer.DAO;
import businesslayer.MaintenanceObserver;
import businesslayer.MaintenanceSubject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DAOFactory implements MaintenanceSubject {

    private List<MaintenanceObserver> observers;
    private Connection conn;

    public DAOFactory(Connection conn) {
        this.conn = conn; // Assuming you pass a valid Connection object
        observers = new ArrayList<>();
    }

    public DAO getDAO(String type) {
        switch (type.toLowerCase()) {
            case "vehicle":
                return new VehicleDAO(conn); // Pass connection to VehicleDAO
            case "user":
                return new UserDAO(conn); // Pass connection to UserDAO
            default:
                throw new IllegalArgumentException("Unknown DAO type: " + type);
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
            observer.update(75.0); // Example of updating with a sample wear level
        }
    }

    public void updateWear(double level) {
        // Update wear level logic can be enhanced or made dynamic
        System.out.println("Updating wear level: " + level);
        notifyObservers();
    }
}
