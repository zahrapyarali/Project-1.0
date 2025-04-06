package businesslayer;

/**
 * Data holder for a single vehicle's fuel or energy consumption report entry.
 */
public class FuelReportEntry {
    private final int vehicleId;
    private final String vehicleType;
    private final String fuelType;
    private final String vehicleNumber;
    private final double distanceTravelled;
    private final double fuelOrEnergyUsed;
    private boolean alert; // NEW FIELD

    public FuelReportEntry(int vehicleId, String vehicleType, String fuelType,
                           String vehicleNumber, double distanceTravelled,
                           double fuelOrEnergyUsed, boolean alert) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
        this.vehicleNumber = vehicleNumber;
        this.distanceTravelled = distanceTravelled;
        this.fuelOrEnergyUsed = fuelOrEnergyUsed;
        this.alert = alert;
    }

    // Getters
    public int getVehicleId() { return vehicleId; }
    public String getVehicleType() { return vehicleType; }
    public String getFuelType() { return fuelType; }
    public String getVehicleNumber() { return vehicleNumber; }
    public double getDistanceTravelled() { return distanceTravelled; }
    public double getFuelOrEnergyUsed() { return fuelOrEnergyUsed; }
    public boolean isAlert() { return alert; } // NEW GETTER

    // Setters (optional if needed)
    public void setAlert(boolean alert) {
        this.alert = alert;
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
                ", alert=" + alert +
                '}';
    }
}
