package businesslayer;

import datalayer.Vehicle;

public class ScheduleMaintenanceCommand implements Command {
    private Vehicle vehicle;

    public ScheduleMaintenanceCommand(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void execute() {
        // Logic for scheduling maintenance for the vehicle
        System.out.println("Scheduling maintenance for vehicle: " + vehicle.getType() + " " + vehicle.getNumber());
        // Additional maintenance scheduling logic could go here
    }
}
