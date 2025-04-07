/* File: FuelConsumptionMonitor.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This class implements the Subject in the Observer design pattern.
 *              It maintains a list of observers that are notified when the 
 *              fuel/energy consumption level changes.
 */

package businesslayer;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code FuelConsumptionMonitor} class acts as the Subject in the Observer pattern.
 * It tracks the overall fuel or energy consumption level and notifies all registered
 * {@link FuelConsumptionObserver} instances when changes occur.
 *
 * <p>This is part of the Observer design pattern used for triggering alerts based
 * on abnormal energy or fuel usage.
 */
public class FuelConsumptionMonitor {

    private List<FuelConsumptionObserver> observers;
    private double consumptionLevel;

    /**
     * Constructs a new {@code FuelConsumptionMonitor} with an empty list of observers.
     */
    public FuelConsumptionMonitor() {
        this.observers = new ArrayList<>();
    }

    /**
     * Registers an observer to be notified of consumption updates.
     *
     * @param observer the observer to attach
     */
    public void attach(FuelConsumptionObserver observer) {
        observers.add(observer);
    }

    /**
     * Unregisters an observer from receiving updates.
     *
     * @param observer the observer to detach
     */
    public void detach(FuelConsumptionObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers of the current consumption level.
     */
    public void notifyObservers() {
        for (FuelConsumptionObserver observer : observers) {
            observer.update(consumptionLevel);
        }
    }

    /**
     * Updates the internal consumption level and triggers notifications to observers.
     *
     * @param level the new consumption level
     */
    public void updateConsumption(double level) {
        this.consumptionLevel = level;
        notifyObservers();
    }
}
