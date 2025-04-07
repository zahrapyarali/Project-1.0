package datalayer;

/**
 * Represents a Vehicle entity in the system.
 * Contains details about vehicle type, number, fuel type, capacity, assigned route, and manager responsible.
 */
public class Vehicle {
    private int id;
    private String type;
    private String number;
    private String fuelType;
    private int maxPassengers;
    private String currentAssignedRoute;
    private int managerId; // FK to User

    /**
     * Default constructor.
     */
    public Vehicle(){};

    /**
     * Parameterized constructor to create a Vehicle object with all details.
     *
     * @param id the vehicle ID
     * @param type the type of the vehicle
     * @param number the vehicle number (identifier)
     * @param fuelType the type of fuel the vehicle uses
     * @param maxPassengers the maximum number of passengers
     * @param currentAssignedRoute the current assigned route of the vehicle
     * @param managerId the manager ID responsible for the vehicle
     */
    public Vehicle(int id, String type, String number, String fuelType, int maxPassengers, String currentAssignedRoute, int managerId) {
        this.id = id;
        this.type = type;
        this.number = number;
        this.fuelType = fuelType;
        this.maxPassengers = maxPassengers;
        this.currentAssignedRoute = currentAssignedRoute;
        this.managerId = managerId;
    }

    // Getters

    /**
     * Gets the vehicle ID.
     * @return the vehicle ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the vehicle type.
     * @return the vehicle type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the vehicle number.
     * @return the vehicle number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Gets the fuel type of the vehicle.
     * @return the fuel type
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * Gets the maximum number of passengers.
     * @return the maximum passengers
     */
    public int getMaxPassengers() {
        return maxPassengers;
    }

    /**
     * Gets the current assigned route of the vehicle.
     * @return the assigned route
     */
    public String getCurrentAssignedRoute() {
        return currentAssignedRoute;
    }

    /**
     * Gets the manager ID responsible for the vehicle.
     * @return the manager ID
     */
    public int getManagerId() {
        return managerId;
    }

    // Setters

    /**
     * Sets the vehicle ID.
     * @param id the vehicle ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the vehicle type.
     * @param type the vehicle type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the vehicle number.
     * @param number the vehicle number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Sets the fuel type of the vehicle.
     * @param fuelType the fuel type to set
     */
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * Sets the maximum number of passengers.
     * @param maxPassengers the maximum passengers to set
     */
    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    /**
     * Sets the current assigned route for the vehicle.
     * @param currentAssignedRoute the route to set
     */
    public void setCurrentAssignedRoute(String currentAssignedRoute) {
        this.currentAssignedRoute = currentAssignedRoute;
    }

    /**
     * Sets the manager ID responsible for the vehicle.
     * @param managerId the manager ID to set
     */
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
