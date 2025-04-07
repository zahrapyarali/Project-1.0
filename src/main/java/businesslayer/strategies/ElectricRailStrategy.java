// ElectricRailStrategy.java
public class ElectricRailStrategy implements FuelConsumptionStrategy {
    public double calculateConsumption(double distance) {
        return distance * 0.2; // example rate
    }
}
