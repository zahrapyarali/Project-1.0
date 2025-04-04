
package businesslayer;

import java.util.ArrayList;
import java.util.List;

public class VehicleWearMonitor {
    private List<MaintenanceObserver> observers;
    private double wearLevel;

    public VehicleWearMonitor() {
        observers = new ArrayList<>();
    }

    // Attach an observer to the list
    public void attach(MaintenanceObserver observer) {
        observers.add(observer);
    }

    // Detach an observer from the list
    public void detach(MaintenanceObserver observer) {
        observers.remove(observer);
    }

    // Notify all observers about a change in the wear level
    public void notifyObservers() {
        for (MaintenanceObserver observer : observers) {
            observer.update(wearLevel);
        }
    }

    // Update the wear level and notify observers
    public void updateWear(double level) {
        this.wearLevel = level;
        notifyObservers();
    }
}
