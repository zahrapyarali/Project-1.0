package businesslayer;

public class MaintenanceLog {
    private int logId;
    private int vehicleId;
    private String component;
    private int hoursUsed;
    private int threshold;
    private boolean isScheduled; 

    public MaintenanceLog(int logId, int vehicleId, String component, int hoursUsed, int threshold, boolean isScheduled) {
        this.logId = logId;
        this.vehicleId = vehicleId;
        this.component = component;
        this.hoursUsed = hoursUsed;
        this.threshold = threshold;
        this.isScheduled = isScheduled;
    }

    // Getters and Setters
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public int getHoursUsed() {
        return hoursUsed;
    }

    public void setHoursUsed(int hoursUsed) {
        this.hoursUsed = hoursUsed;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }

    @Override
    public String toString() {
        return "MaintenanceLog{" +
                "logId=" + logId +
                ", vehicleId=" + vehicleId +
                ", component='" + component + '\'' +
                ", hoursUsed=" + hoursUsed +
                ", threshold=" + threshold +
                ", isScheduled=" + isScheduled +
                '}';
    }
}
