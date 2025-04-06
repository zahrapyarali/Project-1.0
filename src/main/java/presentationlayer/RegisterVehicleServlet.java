package presentationlayer;

import businesslayer.VehicleBuilder;
import businesslayer.VehicleFactory;
import businesslayer.VehicleWearMonitor;
import datalayer.DataSource;
import datalayer.Vehicle;
import datalayer.VehicleDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegisterVehicleServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        vehicleFactory = new VehicleFactory();

        // Initialize DataSource credentials (make sure to set your actual DB user/password)
        DataSource dataSource = DataSource.getInstance();
        dataSource.setCredentials("cst8288", "cst8288");
    }
    
    private VehicleFactory vehicleFactory;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //  Retrieve form parameters
        String type = request.getParameter("type");
        String number = request.getParameter("number");
        String fuelType = request.getParameter("fuelType");
        int maxPassengers = Integer.parseInt(request.getParameter("maxPassengers"));
        String currentAssignedRoute = request.getParameter("currentAssignedRoute");

        //  Retrieve manager ID from session
        HttpSession session = request.getSession(false);
        int managerId = 0;
        if (session != null && session.getAttribute("user") != null) {
            // Assuming your User object is stored in session
            datalayer.User user = (datalayer.User) session.getAttribute("user");
            managerId = user.getUserId();
        }

        //  Create vehicle using VehicleBuilder
        Vehicle vehicle = new VehicleBuilder()
                .setType(type)
                .setNumber(number)
                .setFuelType(fuelType)
                .setMaxPassengers(maxPassengers)
                .setCurrentAssignedRoute(currentAssignedRoute)
                .build();

        vehicle.setManagerId(managerId); //  Set manager ID for DAO

        try (Connection conn = DataSource.getInstance().createConnection()) {
            VehicleDAO vehicleDAO = new VehicleDAO(DataSource.getInstance().createConnection());
            vehicleDAO.insert(vehicle);

            //  Optionally: initialize VehicleWearMonitor
            VehicleWearMonitor wearMonitor = new VehicleWearMonitor();
            wearMonitor.updateWear(0); // Initial wear level

            response.sendRedirect("registerVehicleSuccess.html");
        } catch (SQLException e) {
            Logger.getLogger(RegisterVehicleServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("registerVehicleError.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("registerVehicle.html");
    }
}
