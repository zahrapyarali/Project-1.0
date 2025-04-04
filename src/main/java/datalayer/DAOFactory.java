package datalayer;

import datalayer.*;

public class DAOFactory {

    public static UserDAO getUserDAO() {
        return new UserDAO();
    }

    public static VehicleDAO getVehicleDAO() {
        return new VehicleDAO();
    }

    public static BreakLogDAO getBreakLogDAO() {
        return new BreakLogDAO();
    }

    public static GPSTrackingDAO getGPSTrackingDAO() {
        return new GPSTrackingDAO();
    }

    public static MaintenanceAlertDAO getMaintenanceAlertDAO() {
        return new MaintenanceAlertDAO();
    }

}