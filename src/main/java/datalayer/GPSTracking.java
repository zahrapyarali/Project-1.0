package datalayer;

import java.time.LocalDateTime;

/**
 * Represents a GPS tracking record for a vehicle.
 * Stores details like vehicle ID, location, timestamp, and status.
 */
public class GPSTracking {
    private int trackingId;
    private int vehicleId;
    private String location;
    private LocalDateTime timestamp;
    private String status;

    /**
     * Default constructor.
     * Creates an empty GPSTracking object.
     */
    public GPSTracking() {}

    /**
     * Constructor with all fields.
     *
     * @param trackingId Unique identifier for the tracking record.
     * @param vehicleId ID of the vehicle being tracked.
     * @param location Current location of the vehicle.
     * @param timestamp Time of the tracking record.
     * @param status Status of the vehicle (e.g., "Arrival", "Departure").
     */
    public GPSTracking(int trackingId, int vehicleId, String location, LocalDateTime timestamp, String status) {
        this.trackingId = trackingId;
        this.vehicleId = vehicleId;
        this.location = location;
        this.timestamp = timestamp;
        this.status = status;
    }

    /**
     * Constructor without tracking ID (useful for creating new records before persistence).
     *
     * @param vehicleId ID of the vehicle being tracked.
     * @param location Current location of the vehicle.
     * @param status Status of the vehicle (e.g., "Arrival", "Departure").
     * @param timestamp Time of the tracking record.
     */
    public GPSTracking(int vehicleId, String location, String status, LocalDateTime timestamp) {
        this.vehicleId = vehicleId;
        this.location = location;
        this.status = status;
        this.timestamp = timestamp;
    }

    /**
     * Gets the tracking ID.
     *
     * @return Tracking ID.
     */
    public int getTrackingId() {
        return trackingId;
    }

    /**
     * Sets the tracking ID.
     *
     * @param trackingId Tracking ID to set.
     */
    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    /**
     * Gets the vehicle ID.
     *
     * @return Vehicle ID.
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the vehicle ID.
     *
     * @param vehicleId Vehicle ID to set.
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the location of the vehicle.
     *
     * @return Location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the vehicle.
     *
     * @param location Location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the timestamp of the tracking record.
     *
     * @return Timestamp.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the tracking record.
     *
     * @param timestamp Timestamp to set.
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the status of the vehicle.
     *
     * @return Status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the vehicle.
     *
     * @param status Status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
