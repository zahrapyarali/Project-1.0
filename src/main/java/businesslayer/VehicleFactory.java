package businesslayer;

import businesslayer.strategies.*;
import datalayer.Vehicle;

/**
 * The {@code VehicleFactory} class provides a simple factory method to create
 * {@link Vehicle} instances using the provided vehicle attributes.
 *
 * <p>This class encapsulates the instantiation logic and promotes separation
 * of concerns by isolating object creation from business logic.
 * 
 * <p><strong>Design Pattern:</strong> Simple Factory
 */
public class VehicleFactory {

    /**
     * Creates a new {@link Vehicle} object with the given attributes.
     *
     * @param type the vehicle type (e.g., Diesel Bus, Electric Rail)
     * @param number the unique vehicle number
     * @param fuelType the fuel or energy type
     * @param maxPassengers the maximum passenger capacity
     * @param currentAssignedRoute the currently assigned route for the vehicle
     * @return a fully constructed {@link Vehicle} object
     */
    public Vehicle createVehicle(String type, String number, String fuelType, int maxPassengers, String currentAssignedRoute) {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setNumber(number);
        vehicle.setFuelType(fuelType);
        vehicle.setMaxPassengers(maxPassengers);
        vehicle.setCurrentAssignedRoute(currentAssignedRoute);
        return vehicle;
    }

    /**
     * Returns the fuel consumption strategy for the given vehicle type.
     *
     * @param vehicleType the type of vehicle
     * @return a FuelConsumptionStrategy implementation
     */
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
}
