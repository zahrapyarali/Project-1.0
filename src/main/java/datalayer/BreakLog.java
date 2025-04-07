package datalayer;

import java.time.LocalDateTime;

/**
 * Represents a break log entry for vehicle operations.
 * Used to track when a vehicle or operator reports a break status.
 * 
 * @author Ambika Gadhvi, Saleha Qareen, Sarra Derdar, Zahra Pyarali
 */
public class BreakLog {

    /** Unique identifier for the log entry */
    private int logId;

    /** Identifier for the operator involved in the break log */
    private int operatorId;

    /** Identifier for the vehicle involved in the break log */
    private int vehicleId;

    /** Status of the break log (e.g., 'Break', 'Out-of-Service') */
    private String status;

    /** Timestamp indicating when the break log was created */
    private LocalDateTime timestamp;

    /**
     * Constructs a new BreakLog instance with all parameters.
     *
     * @param logId the unique identifier for the log
     * @param operatorId the identifier of the operator reporting the log
     * @param vehicleId the identifier of the vehicle involved
     * @param status the status of the break log
     * @param timestamp the time the log was created
     */
    public BreakLog(int logId, int operatorId, int vehicleId, String status, LocalDateTime timestamp) {
        this.logId = logId;
        this.operatorId = operatorId;
        this.vehicleId = vehicleId;
        this.status = status;
        this.timestamp = timestamp;
    }

    /**
     * Gets the unique identifier for the log entry.
     *
     * @return the log ID
     */
    public int getLogId() { return logId; }

    /**
     * Gets the operator ID associated with this log.
     *
     * @return the operator ID
     */
    public int getOperatorId() { return operatorId; }

    /**
     * Gets the vehicle ID associated with this log.
     *
     * @return the vehicle ID
     */
    public int getVehicleId() { return vehicleId; }

    /**
     * Gets the status of the break log.
     *
     * @return the status
     */
    public String getStatus() { return status; }

    /**
     * Gets the timestamp of the log creation.
     *
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() { return timestamp; }

    /**
     * Sets the unique identifier for the log entry.
     *
     * @param logId the log ID to set
     */
    public void setLogId(int logId) { this.logId = logId; }

    /**
     * Sets the operator ID associated with this log.
     *
     * @param operatorId the operator ID to set
     */
    public void setOperatorId(int operatorId) { this.operatorId = operatorId; }

    /**
     * Sets the vehicle ID associated with this log.
     *
     * @param vehicleId the vehicle ID to set
     */
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    /**
     * Sets the status of the break log.
     *
     * @param status the status to set
     */
    public void setStatus(String status) { this.status = status; }

    /**
     * Sets the timestamp of the log creation.
     *
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
