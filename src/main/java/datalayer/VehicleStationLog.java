package datalayer;

import java.time.LocalDateTime;

public class VehicleStationLog {
    private int vehicleId;
    private String location;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;

    public VehicleStationLog(int vehicleId, String location, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.vehicleId = vehicleId;
        this.location = location;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public int getVehicleId() { return vehicleId; }
    public String getLocation() { return location; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public LocalDateTime getDepartureTime() { return departureTime; }
}
