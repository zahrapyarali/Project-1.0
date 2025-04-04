package datalayer;

import java.time.LocalDateTime;

public class BreakLog {
    private int logId;
    private int operatorId;  // Foreign Key: operator_id
    private int vehicleId;   // Foreign Key: vehicle_id
    private LocalDateTime breakStart;
    private LocalDateTime breakEnd;

    // Constructor
    public BreakLog(int logId, int operatorId, int vehicleId, LocalDateTime breakStart, LocalDateTime breakEnd) {
        this.logId = logId;
        this.operatorId = operatorId;
        this.vehicleId = vehicleId;
        this.breakStart = breakStart;
        this.breakEnd = breakEnd;
    }

    // Getters and Setters
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDateTime getBreakStart() {
        return breakStart;
    }

    public void setBreakStart(LocalDateTime breakStart) {
        this.breakStart = breakStart;
    }

    public LocalDateTime getBreakEnd() {
        return breakEnd;
    }

    public void setBreakEnd(LocalDateTime breakEnd) {
        this.breakEnd = breakEnd;
    }
}
