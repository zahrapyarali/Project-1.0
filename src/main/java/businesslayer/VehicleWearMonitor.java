/* File: VehicleWearMonitor.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This class implements the Subject in the Observer design pattern
 *              to monitor the wear levels of vehicle components and notify 
 *              registered observers about changes.
 */

package businesslayer;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code VehicleWearMonitor} class acts as the Subject in the Observer pattern
 * for predictive maintenance. It monitors the wear level of vehicle components and
 * notifies all attached {@link MaintenanceObserver} instances when the wear level changes.
 *
 * <p>This class is useful for triggering maintenance alerts and scheduling logic
 * based on usage thresholds.
 *
 * @see MaintenanceObserver
 * @see MaintenanceAlert
 */
public class VehicleWearMonitor {

    private List<MaintenanceObserver> observers;
    private double wearLevel;

    /**
     * Constructs a new {@code VehicleWearMonitor} with an empty list of observers.
     */
    public VehicleWearMonitor() {
        observers = new ArrayList<>();
    }

    /**
     * Registers a maintenance observer to receive updates.
     *
     * @param observer the observer to be attached
     */
    public void attach(MaintenanceObserver observer) {
        observers.add(observer);
    }

    /**
     * Unregisters a maintenance observer from receiving updates.
     *
     * @param observer the observer to be removed
     */
    public void detach(MaintenanceObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all attached observers about the current wear level.
     */
    public void notifyObservers() {
        for (MaintenanceObserver observer : observers) {
            observer.update(wearLevel);
        }
    }

    /**
     * Updates the wear level and triggers notifications to all observers.
     *
     * @param level the new wear level to be reported
     */
    public void updateWear(double level) {
        this.wearLevel = level;
        notifyObservers();
    }
}
