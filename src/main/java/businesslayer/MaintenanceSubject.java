package businesslayer;

public interface MaintenanceSubject {
    void attach(MaintenanceObserver observer);
    void detach(MaintenanceObserver observer);
    void notifyObservers();
}