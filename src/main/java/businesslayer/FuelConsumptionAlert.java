/* File: FuelConsumptionAlert.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This class implements the FuelConsumptionObserver interface to 
 *              receive and process fuel consumption alerts based on the 
 *              monitored consumption level.
 */

package businesslayer;

/**
 * The {@code FuelConsumptionAlert} class implements the
 * {@link FuelConsumptionObserver} interface and acts as an observer
 * that receives notifications about fuel or energy consumption levels.
 *
 * <p>It triggers an alert if the consumption level exceeds a defined threshold.
 * This alert could be printed, logged, or integrated with a notification system.
 * 
 * <p>This class is part of the Observer design pattern implementation.
 */
public class FuelConsumptionAlert implements FuelConsumptionObserver {

    private String alertType;

    /**
     * Called when the observed consumption level is updated.
     * Triggers an alert if the level is considered high.
     *
     * @param consumptionLevel the current fuel/energy consumption level
     */
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
