/* File: MaintenanceSubject.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This interface defines the Subject role in the Observer design pattern
 *              for the predictive maintenance system. It allows observers to register
 *              for updates about component wear and maintenance needs.
 */

package businesslayer;

/**
 * The {@code MaintenanceSubject} interface defines the contract for classes that act
 * as the Subject in the Observer design pattern for predictive maintenance.
 *
 * <p>It allows {@link MaintenanceObserver} implementations to be registered and notified
 * when a monitored component's wear level or usage data changes.
 *
 * @see MaintenanceObserver
 * @see VehicleWearMonitor
 */
public interface MaintenanceSubject {

    /**
     * Registers an observer to receive maintenance updates.
     *
     * @param observer the observer to be attached
     */
    void attach(MaintenanceObserver observer);

    /**
     * Unregisters an observer from receiving updates.
     *
     * @param observer the observer to be detached
     */
    void detach(MaintenanceObserver observer);

    /**
     * Notifies all registered observers of an update.
     */
    void notifyObservers();
}
