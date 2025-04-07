/* File: MaintenanceObserver.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This interface defines the Observer in the Observer design pattern
 *              for the predictive maintenance system. It declares methods that 
 *              allow observers to receive notifications about component wear and usage.
 */

package businesslayer;

/**
 * The {@code MaintenanceObserver} interface defines the contract for observers
 * that want to receive notifications about vehicle component wear and maintenance needs.
 *
 * <p>This interface is part of the Observer design pattern and is used by classes
 * such as {@link MaintenanceAlert} to react when components exceed safe operating levels.
 *
 * @see VehicleWearMonitor
 * @see MaintenanceAlert
 * @see MaintenanceAlertService
 */
public interface MaintenanceObserver {

    /**
     * Notifies the observer when a specific component has crossed its threshold.
     *
     * @param vehicleId the ID of the vehicle
     * @param component the name of the component (e.g., brakes, engine)
     * @param hoursUsed how many hours the component has been used
     * @param threshold the maximum safe usage hours before maintenance is needed
     */
    void update(int vehicleId, String component, int hoursUsed, int threshold);

    /**
     * Notifies the observer about general wear level updates.
     * This method may remain unused in some implementations.
     *
     * @param wearLevel the numeric wear level (optional usage)
     */
    void update(double wearLevel);
}
