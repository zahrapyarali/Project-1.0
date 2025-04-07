// DieselElectricTrainStrategy.java
public class DieselElectricTrainStrategy implements FuelConsumptionStrategy {
    public double calculateConsumption(double distance) {
        return distance * 0.5; // example rate
    }
}
