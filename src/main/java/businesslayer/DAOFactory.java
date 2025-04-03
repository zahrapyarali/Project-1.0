package businesslayer;

import datalayer.*;

public class DAOFactory {

    public static UserDAO getUserDAO() {
        return new UserDAO(DatabaseConnection.getConnection());
    }

    public static VehicleDAO getVehicleDAO() {
        return new VehicleDAO(DatabaseConnection.getConnection());
    }

    public static BreakLogDAO getBreakLogDAO() {
        return new BreakLogDAO(DatabaseConnection.getConnection());
    }

    public static GPSTrackingDAO getGPSTrackingDAO() {
        return new GPSTrackingDAO(DatabaseConnection.getConnection());
    }

    public static MaintenanceAlertDAO getMaintenanceAlertDAO() {
        return new MaintenanceAlertDAO(DatabaseConnection.getConnection());
    }
}
