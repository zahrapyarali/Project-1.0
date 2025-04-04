package businesslayer;

public class FuelConsumptionAlert implements FuelConsumptionObserver {
    private String alertType;

    @Override
    public void update(double consumptionLevel) {
        // Check the consumption level and set the alert type accordingly
        if (consumptionLevel > 80) {
            alertType = "High consumption alert!";
        } else {
            alertType = "Consumption is normal.";
        }

        // Display the alert (this can be replaced by a logging system or notification service)
        System.out.println("Alert: " + alertType + " (Consumption Level: " + consumptionLevel + ")");
    }
}
