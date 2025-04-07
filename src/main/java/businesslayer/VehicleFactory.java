package businesslayer;

import businesslayer.strategies.*;
import datalayer.Vehicle;

public class VehicleFactory {

    // Factory method to return the right fuel strategy based on vehicle type
    public static FuelConsumptionStrategy getStrategy(String vehicleType) {
        switch (vehicleType) {
            case "Diesel Bus":
                return new DieselBusStrategy();
            case "Electric Light Rail":
                return new ElectricRailStrategy();
            case "Diesel-Electric Train":
                return new DieselElectricTrainStrategy();
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }

    // Existing factory method to create a Vehicle (you already had this)
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
