package businesslayer;

import java.time.LocalDateTime;

public class MaintenanceLog {
    private int maintenanceId;
    private int vehicleId;  // Foreign Key referencing the Vehicle table
    private double cost;
    private LocalDateTime date;

    public MaintenanceLog(int maintenanceId, int vehicleId, double cost, LocalDateTime date) {
        this.maintenanceId = maintenanceId;
        this.vehicleId = vehicleId;
        this.cost = cost;
        this.date = date;
    }

    // Getters and Setters
    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MaintenanceLog{" +
                "maintenanceId=" + maintenanceId +
                ", vehicleId=" + vehicleId +
                ", cost=" + cost +
                ", date=" + date +
                '}';
    }
}
