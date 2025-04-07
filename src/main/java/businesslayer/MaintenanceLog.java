/* File: MaintenanceLog.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This class serves as a data model for tracking maintenance logs.
 *              It contains information about vehicle components, their usage,
 *              service thresholds, and scheduled maintenance status.
 */

package businesslayer;

/**
 * The {@code MaintenanceLog} class is a data model representing the usage record
 * of a vehicle component for predictive maintenance tracking.
 *
 * <p>It stores details such as component name, hours used, threshold for wear,
 * and whether maintenance is scheduled. This class is used in conjunction with
 * {@link MaintenanceAlertService} and {@link VehicleWearMonitor} to generate alerts.
 */
public class MaintenanceLog {

    private int logId;
    private int vehicleId;
    private String component;
    private int hoursUsed;
    private int threshold;
    private boolean isScheduled;

    /**
     * Constructs a new {@code MaintenanceLog} with all fields initialized.
     *
     * @param logId the unique ID of the log
     * @param vehicleId the ID of the associated vehicle
     * @param component the name of the component (e.g., brakes, engine)
     * @param hoursUsed the hours this component has been used
     * @param threshold the threshold for triggering maintenance
     * @param isScheduled flag indicating whether maintenance has been scheduled
     */
    public MaintenanceLog(int logId, int vehicleId, String component, int hoursUsed, int threshold, boolean isScheduled) {
        this.logId = logId;
        this.vehicleId = vehicleId;
        this.component = component;
        this.hoursUsed = hoursUsed;
        this.threshold = threshold;
        this.isScheduled = isScheduled;
    }

    /** @return the maintenance log ID */
    public int getLogId() {
        return logId;
    }

    /** @param logId sets the maintenance log ID */
    public void setLogId(int logId) {
        this.logId = logId;
    }

    /** @return the vehicle ID associated with this log */
    public int getVehicleId() {
        return vehicleId;
    }

    /** @param vehicleId sets the vehicle ID */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /** @return the component name */
    public String getComponent() {
        return component;
    }

    /** @param component sets the component name */
    public void setComponent(String component) {
        this.component = component;
    }

    /** @return the number of hours the component has been used */
    public int getHoursUsed() {
        return hoursUsed;
    }

    /** @param hoursUsed sets the hours used for the component */
    public void setHoursUsed(int hoursUsed) {
        this.hoursUsed = hoursUsed;
    }

    /** @return the wear threshold before maintenance is required */
    public int getThreshold() {
        return threshold;
    }

    /** @param threshold sets the maintenance threshold */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    /** @return true if maintenance has been scheduled */
    public boolean isScheduled() {
        return isScheduled;
    }

    /** @param scheduled sets the scheduled maintenance flag */
    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }

    /**
     * Returns a string representation of the maintenance log for display or debugging.
     *
     * @return a string summarizing the maintenance log details
     */
    @Override
    public String toString() {
        return "MaintenanceLog{" +
                "logId=" + logId +
                ", vehicleId=" + vehicleId +
                ", component='" + component + '\'' +
                ", hoursUsed=" + hoursUsed +
                ", threshold=" + threshold +
                ", isScheduled=" + isScheduled +
                '}';
    }
}
