package businesslayer;

public class MaintenanceAlert implements FuelConsumptionObserver, MaintenanceObserver {
    private String alertType;

    

    @Override
    public void update(double wearLevel) {
        // React to changes in vehicle wear and issue a maintenance alert
        if (wearLevel > 75) {
            alertType = "Vehicle wear level is high! Maintenance required!";
            System.out.println(alertType);
        } else {
            alertType = "Vehicle is in good condition.";
            System.out.println(alertType);
        }
    }

    // Optional: You could add getters or setters to retrieve or update the alert type.
    public String getAlertType() {
        return alertType;
    }
}
