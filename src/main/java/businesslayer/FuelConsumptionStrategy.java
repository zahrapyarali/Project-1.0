/* File: FuelConsumptionStrategy.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This interface defines the Strategy design pattern for calculating
 *              fuel or energy consumption based on the vehicle type and distance.
 *              Different vehicle types implement this interface with their own logic.
 */

package businesslayer;

/**
 * The {@code FuelConsumptionStrategy} interface defines a strategy contract
 * for calculating the fuel or energy consumption of a vehicle based on distance.
 *
 * <p>This interface is implemented by various concrete classes such as
 * {@link DieselBusStrategy}, {@link ElectricRailStrategy}, and {@link DieselElectricTrainStrategy}
 * to support different calculation rules based on vehicle type.
 *
 * <p>It follows the Strategy design pattern, allowing dynamic selection of
 * consumption logic at runtime.
 * 
 * @author Ambika, Saleha, Sarra, Zahra
 * @date 04-06-2025
 */
public interface FuelConsumptionStrategy {

    /**
     * Calculates the estimated fuel or energy consumption for a given distance.
     *
     * @param distance the distance traveled in kilometers
     * @return the estimated fuel or energy consumed
     */
    double calculateConsumption(double distance);
}
