package businesslayer;

public class ElectricRailStrategy implements FuelConsumptionStrategy {

    @Override
    public double calculateConsumption(double distance) {
        // Example: Electric rail consumes 0.5 kWh per km
        double consumptionRate = 0.5; // kWh per km
        return distance * consumptionRate;
    }
}