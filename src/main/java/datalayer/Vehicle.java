package datalayer;

public class Vehicle {
    private int id;
    private String type;
    private String number;
    private String fuelType;
    private int maxPassengers;
    private String currentAssignedRoute;
    private int managerId; // FK to User
    
    
    public Vehicle(){};
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
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public String getCurrentAssignedRoute() {
        return currentAssignedRoute;
    }

    public int getManagerId() {
        return managerId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public void setCurrentAssignedRoute(String currentAssignedRoute) {
        this.currentAssignedRoute = currentAssignedRoute;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
