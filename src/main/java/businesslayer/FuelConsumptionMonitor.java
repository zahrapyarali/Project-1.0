package businesslayer;

import java.util.ArrayList;
import java.util.List;

public class FuelConsumptionMonitor {
    private List<FuelConsumptionObserver> observers;
    private double consumptionLevel;

    public FuelConsumptionMonitor() {
        this.observers = new ArrayList<>();
    }

    public void attach(FuelConsumptionObserver observer) {
        observers.add(observer);
    }

    public void detach(FuelConsumptionObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (FuelConsumptionObserver observer : observers) {
            observer.update(consumptionLevel);
        }
    }

    public void updateConsumption(double level) {
        this.consumptionLevel = level;
        notifyObservers();
    }
}
