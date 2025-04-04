package businesslayer;

public class DieselElectricTrainStrategy implements FuelConsumptionStrategy {

    @Override
    public double calculateConsumption(double distance) {
        // Example: Diesel-electric train consumes 0.1 liters per km
        double consumptionRate = 0.1; // Liters per km
        return distance * consumptionRate;
    }
}
