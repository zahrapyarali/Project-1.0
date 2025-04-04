package datalayer;

import businesslayer.DAO;
import businesslayer.MaintenanceObserver;
import businesslayer.MaintenanceSubject;
import java.util.ArrayList;
import java.util.List;

public class DAOFactory implements MaintenanceSubject {
    
    private List<MaintenanceObserver> observers;
    
    public DAOFactory() {
        observers = new ArrayList<>();
    }

    // Method to return a DAO object based on the type of entity
    public DAO getDAO(String type) {
        switch (type.toLowerCase()) {
            case "vehicle":
                return new VehicleDAO(); // Assuming VehicleDAO is a valid implementation of DAO
            case "user":
                return new UserDAO(); // Assuming UserDAO is a valid implementation of DAO
            default:
                throw new IllegalArgumentException("Unknown DAO type: " + type);
        }
    }

    // MaintenanceObserver related methods
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
        // Example method to update wear level and notify observers
        System.out.println("Updating wear level: " + level);
        notifyObservers();
    }
}
