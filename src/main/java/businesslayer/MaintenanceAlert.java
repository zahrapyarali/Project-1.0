package businesslayer;

public class MaintenanceAlert implements MaintenanceObserver {

    private String alertMessage;

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

    // This is required because it's declared in the interface
    @Override
    public void update(double wearLevel) {
        // Optional: Leave empty if not used by this alert
    }

    public String getAlertMessage() {
        return alertMessage;
    }
}
