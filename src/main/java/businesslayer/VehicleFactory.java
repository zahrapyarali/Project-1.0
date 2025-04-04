package businesslayer;

import datalayer.Vehicle;

public class VehicleFactory {

    // Method to create a vehicle based on the type and provided details
    public Vehicle createVehicle(String type, String number, String fuelType, int maxPassengers, String currentAssignedRoute) {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setNumber(number);
        vehicle.setFuelType(fuelType);
        vehicle.setMaxPassengers(maxPassengers);
        vehicle.setCurrentAssignedRoute(currentAssignedRoute);
        
        return vehicle;
    }
}
