/* File: DieselElectricTrainStrategy.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This class implements the FuelConsumptionStrategy interface for 
 *              diesel-electric trains. It calculates fuel consumption based 
 *              on a fixed rate per kilometer traveled.
 */

package businesslayer;

/**
 * The {@code DieselElectricTrainStrategy} class implements the
 * {@link FuelConsumptionStrategy} interface for diesel-electric trains.
 *
 * <p>This strategy provides a specific fuel consumption calculation
 * based on a standard rate per kilometer.
 */
public class DieselElectricTrainStrategy implements FuelConsumptionStrategy {

    /**
     * Calculates fuel consumption for a diesel-electric train based on distance.
     *
     * @param distance the distance traveled in kilometers
     * @return the estimated fuel consumption in liters
     */
    @Override
    public double calculateConsumption(double distance) {
        // Example: Diesel-electric train consumes 0.1 liters per km
        double consumptionRate = 0.1; // Liters per km
        return distance * consumptionRate;
    }
}
