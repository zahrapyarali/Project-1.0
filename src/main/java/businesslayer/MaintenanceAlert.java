package businesslayer;
public class MaintenanceAlert {
    private String alertType;

    // Constructor
    public MaintenanceAlert(String alertType) {
        this.alertType = alertType;
    }

    // Method to update the alert when the wear level changes
    public void update(double wearLevel) {
        // You can log or display the alert here
        System.out.println("Alert: " + alertType + " (Wear Level: " + wearLevel + ")");
    }

    // Getter for alertType
    public String getAlertType() {
        return alertType;
    }

    // Setter for alertType
    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }
}