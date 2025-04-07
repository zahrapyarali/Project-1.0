/* File: VehicleBuilder.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This class implements the Builder design pattern for creating
 *              instances of the Vehicle class. It provides a step-by-step
 *              fluent interface to set vehicle attributes before building the object.
 */

package businesslayer;

import datalayer.Vehicle;

/**
 * The {@code VehicleBuilder} class provides a fluent interface for creating
 * {@link Vehicle} objects using the Builder design pattern.
 *
 * <p>This approach simplifies the creation of complex vehicle objects by
 * allowing chained method calls for setting attributes before finalizing the build.
 *
 * @see Vehicle
 * @see VehicleFactory
 */
public class VehicleBuilder {
    private String type;
    private String number;
    private String fuelType;
    private int maxPassengers;
    private String currentAssignedRoute;

    /**
     * Sets the type of the vehicle (e.g., Diesel Bus, Electric Rail).
     *
     * @param type the type of the vehicle
     * @return the builder instance
     */
    public VehicleBuilder setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Sets the unique number assigned to the vehicle.
     *
     * @param number the vehicle's number
     * @return the builder instance
     */
    public VehicleBuilder setNumber(String number) {
        this.number = number;
        return this;
    }

    /**
     * Sets the fuel or energy type used by the vehicle.
     *
     * @param fuelType the fuel type (e.g., Diesel, Electric)
     * @return the builder instance
     */
    public VehicleBuilder setFuelType(String fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    /**
     * Sets the maximum number of passengers the vehicle can carry.
     *
     * @param maxPassengers the capacity of the vehicle
     * @return the builder instance
     */
    public VehicleBuilder setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
        return this;
    }

    /**
     * Sets the current assigned route for the vehicle.
     *
     * @param route the route string
     * @return the builder instance
     */
    public VehicleBuilder setCurrentAssignedRoute(String route) {
        this.currentAssignedRoute = route;
        return this;
    }

    /**
     * Builds and returns the {@link Vehicle} object with the specified attributes.
     *
     * @return the fully constructed Vehicle object
     */
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
