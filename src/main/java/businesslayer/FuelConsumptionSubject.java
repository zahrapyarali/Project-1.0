package businesslayer;

public interface FuelConsumptionSubject {
    void attach(FuelConsumptionObserver observer);
    void detach(FuelConsumptionObserver observer);
    void notifyObservers();
}

