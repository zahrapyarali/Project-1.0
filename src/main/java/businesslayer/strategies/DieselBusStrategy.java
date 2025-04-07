// DieselBusStrategy.java
public class DieselBusStrategy implements FuelConsumptionStrategy {
    public double calculateConsumption(double distance) {
        return distance * 0.3; // example rate
    }
}
