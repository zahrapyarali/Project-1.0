package datalayer;

import java.time.LocalDateTime;

/**
 * Represents a log entry for a vehicle at a station,
 * capturing details like vehicle ID, location, arrival time, and departure time.
 * 
 * @author Ambika Gadhvi, Saleha Qareen, Sarra Derdar, Zahra Pyarali
 */
public class VehicleStationLog {
    /** The unique identifier of the vehicle. */
    private int vehicleId;

    /** The location of the station where the vehicle is logged. */
    private String location;

    /** The arrival time of the vehicle at the station. */
    private LocalDateTime arrivalTime;

    /** The departure time of the vehicle from the station. */
    private LocalDateTime departureTime;

    /**
     * Constructs a new VehicleStationLog with the specified details.
     *
     * @param vehicleId the unique identifier of the vehicle
     * @param location the station location
     * @param arrivalTime the time the vehicle arrived at the station
     * @param departureTime the time the vehicle departed from the station
     */
    public VehicleStationLog(int vehicleId, String location, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.vehicleId = vehicleId;
        this.location = location;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    /**
     * Returns the vehicle's unique identifier.
     *
     * @return the vehicle ID
     */
    public int getVehicleId() { return vehicleId; }

    /**
     * Returns the location of the station.
     *
     * @return the station location
     */
    public String getLocation() { return location; }

    /**
     * Returns the arrival time of the vehicle at the station.
     *
     * @return the arrival time
     */
    public LocalDateTime getArrivalTime() { return arrivalTime; }

    /**
     * Returns the departure time of the vehicle from the station.
     *
     * @return the departure time
     */
    public LocalDateTime getDepartureTime() { return departureTime; }
}
