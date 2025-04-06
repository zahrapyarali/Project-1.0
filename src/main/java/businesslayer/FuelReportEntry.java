package businesslayer;

/**
 * Data holder for a single vehicle's fuel or energy consumption report entry.
 */
public class FuelReportEntry {
    private int vehicleId;
    private String vehicleType;
    private String fuelType;
    private String vehicleNumber;
    private double distanceTravelled;
    private double fuelOrEnergyUsed;

    public FuelReportEntry(int vehicleId, String vehicleType, String fuelType,
                           String vehicleNumber, double distanceTravelled, double fuelOrEnergyUsed) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
        this.vehicleNumber = vehicleNumber;
        this.distanceTravelled = distanceTravelled;
        this.fuelOrEnergyUsed = fuelOrEnergyUsed;
    }

    // Getters and Setters
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public double getFuelOrEnergyUsed() {
        return fuelOrEnergyUsed;
    }

    public void setFuelOrEnergyUsed(double fuelOrEnergyUsed) {
        this.fuelOrEnergyUsed = fuelOrEnergyUsed;
    }

    @Override
    public String toString() {
        return "FuelReportEntry{" +
                "vehicleId=" + vehicleId +
                ", vehicleType='" + vehicleType + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", distanceTravelled=" + distanceTravelled +
                ", fuelOrEnergyUsed=" + fuelOrEnergyUsed +
                '}';
    }
}
