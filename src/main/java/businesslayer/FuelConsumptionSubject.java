/* File: FuelConsumptionSubject.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This interface defines the Subject in the Observer design pattern
 *              for fuel or energy monitoring. Classes implementing this interface
 *              can register, remove, and notify observers of consumption updates.
 */

package businesslayer;

/**
 * The {@code FuelConsumptionSubject} interface defines the contract for any class
 * that acts as a Subject in the Observer pattern for fuel or energy monitoring.
 *
 * <p>Implementing classes allow observers to be attached or detached and provide
 * a method to notify all observers when the consumption level changes.
 *
 * @see FuelConsumptionObserver
 * @see FuelConsumptionMonitor
 * 
 */
public interface FuelConsumptionSubject {

    /**
     * Attaches an observer to the subject.
     *
     * @param observer the observer to be added
     */
    void attach(FuelConsumptionObserver observer);

    /**
     * Detaches an observer from the subject.
     *
     * @param observer the observer to be removed
     */
    void detach(FuelConsumptionObserver observer);

    /**
     * Notifies all attached observers of a state change.
     */
    void notifyObservers();
}
