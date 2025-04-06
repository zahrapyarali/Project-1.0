package datalayer;

import java.time.LocalDateTime;


public class GPSTracking {

    private int trackingId;
    private int vehicleId;  // Foreign Key referencing the vehicle
    private String location;  // Store the location as a string (e.g., "New York" or "coordinates")
    private LocalDateTime timestamp;

    // Default constructor
    public GPSTracking() {}

    // Constructor with latitude and longitude
    public GPSTracking(int trackingId, int vehicleId, String location, LocalDateTime timestamp) {
        this.trackingId = trackingId;
        this.vehicleId = vehicleId;
        this.location = location;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "GPSTracking{" +
                "trackingId=" + trackingId +
                ", vehicleId=" + vehicleId +
                ", location='" + location + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
