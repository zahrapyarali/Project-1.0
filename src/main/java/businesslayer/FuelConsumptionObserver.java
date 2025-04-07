/* File: FuelConsumptionObserver.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This interface defines the Observer in the Observer design pattern
 *              for monitoring fuel or energy consumption levels. Classes that 
 *              implement this interface will receive updates from the subject 
 *              (e.g., FuelConsumptionMonitor).
 */

package businesslayer;

/**
 * The {@code FuelConsumptionObserver} interface defines the contract for objects
 * that want to be notified of changes in fuel or energy consumption levels.
 *
 * <p>This interface is part of the Observer design pattern and is implemented
 * by classes that respond to consumption data updates.
 */
public interface FuelConsumptionObserver {

    /**
     * Called by the subject (e.g., {@code FuelConsumptionMonitor}) to notify
     * the observer of a new fuel/energy consumption level.
     *
     * @param consumptionLevel the updated consumption level
     */
    void update(double consumptionLevel);
}
