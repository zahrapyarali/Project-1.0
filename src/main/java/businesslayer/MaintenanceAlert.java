/* File: MaintenanceAlert.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This class implements the MaintenanceObserver interface. It generates
 *              maintenance alerts when the usage of a vehicle component exceeds
 *              a specified threshold.
 */

package businesslayer;

/**
 * The {@code MaintenanceAlert} class implements the {@link MaintenanceObserver}
 * interface to generate alerts when monitored vehicle components exceed their usage limits.
 *
 * <p>This class is part of the Observer design pattern and receives updates from
 * {@code VehicleWearMonitor}. It checks if maintenance is required and prints an alert.
 * 
 */
public class MaintenanceAlert implements MaintenanceObserver {

    private String alertMessage;

    /**
     * Receives detailed wear updates and prints an alert if the usage exceeds the threshold.
     *
     * @param vehicleId the ID of the vehicle being monitored
     * @param component the name of the component (e.g., brakes, engine)
     * @param hoursUsed the number of hours the component has been used
     * @param threshold the maximum allowed usage before maintenance is needed
     */
    @Override
    public void update(int vehicleId, String component, int hoursUsed, int threshold) {
        if (hoursUsed >= threshold) {
            alertMessage = "️Maintenance Required: Vehicle ID " + vehicleId +
                    " - Component '" + component + "' exceeded usage (" + hoursUsed + "/" + threshold + ")";
            System.out.println(alertMessage);
        } else {
            alertMessage = "Vehicle ID " + vehicleId +
                    " - Component '" + component + "' is within safe usage.";
            System.out.println(alertMessage);
        }
    }

    /**
     * Default implementation of the general wear level update.
     * This method is unused in this specific alert class.
     *
     * @param wearLevel the wear level (not used here)
     */
    @Override
    public void update(double wearLevel) {
        // Optional: Leave empty if not used by this alert
    }

    /**
     * Returns the most recent alert message.
     *
     * @return the last alert message generated
     */
    public String getAlertMessage() {
        return alertMessage;
    }
}
