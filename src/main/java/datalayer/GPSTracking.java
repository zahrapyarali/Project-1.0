package datalayer;

import java.time.LocalDateTime;

public class GPSTracking {
    private int trackingId;
    private int vehicleId;
    private String location;
    private LocalDateTime timestamp;
    private String status;

    public GPSTracking() {}

    public GPSTracking(int trackingId, int vehicleId, String location, LocalDateTime timestamp, String status) {
        this.trackingId = trackingId;
        this.vehicleId = vehicleId;
        this.location = location;
        this.timestamp = timestamp;
        this.status = status;
    }

    // ✅ New constructor
    public GPSTracking(int vehicleId, String location, String status, LocalDateTime timestamp) {
        this.vehicleId = vehicleId;
        this.location = location;
        this.status = status;
        this.timestamp = timestamp;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
