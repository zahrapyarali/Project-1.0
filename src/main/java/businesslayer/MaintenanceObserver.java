package businesslayer;

/**
 * Observer interface for maintenance notifications.
 * Implemented by classes that want to receive maintenance alerts.
 * 
 * @author YourName
 */
public interface MaintenanceObserver {
    void update(int vehicleId, String component, int hoursUsed, int threshold);

    public void update(double wearLevel);
}
