/* File: FuelReportEntry.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This class is a data holder for a single vehicle's fuel or energy
 *              consumption report entry. It includes vehicle information, distance 
 *              traveled, consumption data, and an alert flag for excessive usage.
 */

package businesslayer;

/**
 * The {@code FuelReportEntry} class represents a single report entry that summarizes
 * fuel or energy usage data for a specific vehicle.
 *
 * <p>This class is used in conjunction with {@link FuelConsumptionReportService}
 * to generate usage analytics and flag vehicles with abnormal consumption levels.
 * 
 * @author Ambika, Saleha, Sarra, Zahra
 * @date 04-06-2025
 */
public class FuelReportEntry {

    private final int vehicleId;
    private final String vehicleType;
    private final String fuelType;
    private final String vehicleNumber;
    private final double distanceTravelled;
    private final double fuelOrEnergyUsed;
    private boolean alert;

    /**
     * Constructs a new {@code FuelReportEntry} with all relevant values.
     *
     * @param vehicleId the unique ID of the vehicle
     * @param vehicleType the type of vehicle (e.g., Diesel Bus, Electric Rail)
     * @param fuelType the fuel or energy type used
     * @param vehicleNumber the assigned vehicle number
     * @param distanceTravelled the distance traveled in kilometers
     * @param fuelOrEnergyUsed the total fuel or energy consumed
     * @param alert true if consumption exceeded the expected threshold
     */
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

    /** @return the vehicle ID */
    public int getVehicleId() { return vehicleId; }

    /** @return the type of vehicle */
    public String getVehicleType() { return vehicleType; }

    /** @return the fuel or energy type */
    public String getFuelType() { return fuelType; }

    /** @return the vehicle's assigned number */
    public String getVehicleNumber() { return vehicleNumber; }

    /** @return the total distance the vehicle traveled */
    public double getDistanceTravelled() { return distanceTravelled; }

    /** @return the total fuel or energy used */
    public double getFuelOrEnergyUsed() { return fuelOrEnergyUsed; }

    /** @return whether an alert was triggered */
    public boolean isAlert() { return alert; }

    /**
     * Sets the alert flag to indicate abnormal consumption.
     *
     * @param alert true to flag as overconsuming, false otherwise
     */
    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    /**
     * Returns a string representation of the report entry for debugging or logging.
     *
     * @return a string summarizing the report entry
     */
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
