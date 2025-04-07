/* File: DieselBusStrategy.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This class implements the FuelConsumptionStrategy interface for diesel buses.
 *              It calculates fuel consumption based on a fixed rate of liters per kilometer.
 */

package businesslayer;

/**
 * The {@code DieselBusStrategy} class implements the {@link FuelConsumptionStrategy}
 * interface to provide a specific fuel consumption calculation for diesel buses.
 *
 * <p>This strategy assumes that diesel buses consume a fixed amount of fuel
 * per kilometer traveled.
 *
 * @author Ambika, Saleha, Sarra, Zahra
 * @date 04-06-2025
 */
public class DieselBusStrategy implements FuelConsumptionStrategy {

    /**
     * Calculates fuel consumption for a diesel bus based on the distance traveled.
     *
     * @param distance the distance traveled in kilometers
     * @return the estimated fuel consumption in liters
     */
    @Override
    public double calculateConsumption(double distance) {
        // Example: Diesel bus consumes 0.2 liters per km
        double consumptionRate = 0.2; // Liters per km
        return distance * consumptionRate;
    }
}
