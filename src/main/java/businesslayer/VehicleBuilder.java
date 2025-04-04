
package businesslayer;

import datalayer.Vehicle;

public class VehicleBuilder {
    private String type;
    private String number;
    private String fuelType;
    private int maxPassengers;
    private String currentAssignedRoute;

    // Set the type and return the builder instance
    public VehicleBuilder setType(String type) {
        this.type = type;
        return this;
    }

    // Set the number and return the builder instance
    public VehicleBuilder setNumber(String number) {
        this.number = number;
        return this;
    }

    // Set the fuel type and return the builder instance
    public VehicleBuilder setFuelType(String fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    // Set max passengers and return the builder instance
    public VehicleBuilder setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
        return this;
    }

    // Set the current assigned route and return the builder instance
    public VehicleBuilder setCurrentAssignedRoute(String route) {
        this.currentAssignedRoute = route;
        return this;
    }

    // Build and return a Vehicle instance
    public Vehicle build() {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setNumber(number);
        vehicle.setFuelType(fuelType);
        vehicle.setMaxPassengers(maxPassengers);
        vehicle.setCurrentAssignedRoute(currentAssignedRoute);
        return vehicle;
    }
}
