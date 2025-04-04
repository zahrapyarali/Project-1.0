package businesslayer;

public class DieselBusStrategy implements FuelConsumptionStrategy {

    @Override
    public double calculateConsumption(double distance) {
        // Example: Diesel bus consumes 0.2 liters per km
        double consumptionRate = 0.2; // Liters per km
        return distance * consumptionRate;
    }
}