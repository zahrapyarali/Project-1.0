/* File: ElectricRailStrategy.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This class implements the FuelConsumptionStrategy interface for
 *              electric light rail vehicles. It calculates energy consumption 
 *              based on a fixed kWh rate per kilometer.
 */

package businesslayer;

/**
 * The {@code ElectricRailStrategy} class implements the
 * {@link FuelConsumptionStrategy} interface for electric light rail systems.
 *
 * <p>This strategy calculates energy usage in kilowatt-hours (kWh) based
 * on a constant rate of energy consumption per kilometer.
 */
public class ElectricRailStrategy implements FuelConsumptionStrategy {

    /**
     * Calculates energy consumption for electric rail based on distance.
     *
     * @param distance the distance traveled in kilometers
     * @return the estimated energy consumption in kilowatt-hours (kWh)
     */
    @Override
    public double calculateConsumption(double distance) {
        // Example: Electric rail consumes 0.5 kWh per km
        double consumptionRate = 0.5; // kWh per km
        return distance * consumptionRate;
    }
}
