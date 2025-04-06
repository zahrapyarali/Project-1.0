package datalayer;

import java.time.LocalDateTime;

public class BreakLog {
    private int logId;
    private int operatorId;
    private int vehicleId;
    private String status;
    private LocalDateTime timestamp;

    public BreakLog(int logId, int operatorId, int vehicleId, String status, LocalDateTime timestamp) {
        this.logId = logId;
        this.operatorId = operatorId;
        this.vehicleId = vehicleId;
        this.status = status;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public int getLogId() { return logId; }
    public int getOperatorId() { return operatorId; }
    public int getVehicleId() { return vehicleId; }
    public String getStatus() { return status; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setLogId(int logId) { this.logId = logId; }
    public void setOperatorId(int operatorId) { this.operatorId = operatorId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public void setStatus(String status) { this.status = status; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
