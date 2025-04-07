/* File: ScheduleMaintenanceCommand.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This class implements the Command design pattern to encapsulate
 *              the request for scheduling vehicle maintenance. It allows the action
 *              to be executed, queued, or logged as a command object.
 */

package businesslayer;

import datalayer.Vehicle;

/**
 * The {@code ScheduleMaintenanceCommand} class encapsulates the action of scheduling
 * maintenance for a given vehicle using the Command design pattern.
 *
 * <p>This class decouples the action execution from the caller and provides flexibility
 * in invoking the command at a later time or through a command processor.
 *
 * @see Command
 * @see Vehicle
 */
public class ScheduleMaintenanceCommand implements Command {

    private Vehicle vehicle;

    /**
     * Constructs the command with the target vehicle.
     *
     * @param vehicle the vehicle for which maintenance is to be scheduled
     */
    public ScheduleMaintenanceCommand(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Executes the maintenance scheduling action for the specified vehicle.
     * This could be extended to integrate with a scheduling system or database.
     */
    @Override
    public void execute() {
        System.out.println("Scheduling maintenance for vehicle: " + vehicle.getType() + " " + vehicle.getNumber());
        // Additional maintenance scheduling logic could go here
    }
}
