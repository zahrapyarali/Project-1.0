/* File: RegisterVehicleServlet.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This servlet handles vehicle registration, update, deletion, and listing.
 *              It uses the Builder and DAO design patterns to manage Vehicle objects.
 *              Transit Managers can add or edit vehicles, and view them in vehicles.jsp.
 */

package presentationlayer;

import businesslayer.VehicleBuilder;
import businesslayer.VehicleFactory;
import businesslayer.VehicleWearMonitor;
import datalayer.DataSource;
import datalayer.Vehicle;
import datalayer.VehicleDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code RegisterVehicleServlet} is responsible for handling vehicle-related operations
 * such as registration, updating, deletion, and listing. This servlet supports both
 * GET and POST methods.
 *
 * <p>It demonstrates the use of the Builder Pattern for constructing vehicle instances,
 * and the DAO Pattern for database interactions.
 *
 * @author Ambika, Saleha, Sarra, Zahra
 * @date 04-06-2025
 */
public class RegisterVehicleServlet extends HttpServlet {

    private VehicleFactory vehicleFactory;

    /**
     * Initializes the {@link VehicleFactory} and sets up database credentials using {@link DataSource}.
     */
    @Override
    public void init() throws ServletException {
        vehicleFactory = new VehicleFactory();
        DataSource.getInstance().setCredentials("cst8288", "cst8288");
    }

    /**
     * Handles GET requests to:
     * <ul>
     *   <li>Delete a vehicle if action=delete</li>
     *   <li>List all vehicles by default</li>
     * </ul>
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String vehicleIdParam = request.getParameter("vehicleId");

        try (Connection conn = DataSource.getInstance().createConnection()) {
            VehicleDAO vehicleDAO = new VehicleDAO(conn);

            if ("delete".equals(action) && vehicleIdParam != null) {
                int vehicleId = Integer.parseInt(vehicleIdParam);
                vehicleDAO.delete(vehicleId);
            }

            listVehicles(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        }
    }

    /**
     * Handles POST requests for vehicle registration and updates.
     * Uses {@link VehicleBuilder} to construct a new {@link Vehicle} and persists it via {@link VehicleDAO}.
     *
     * @param request  the HTTP request with vehicle details
     * @param response the HTTP response
     * @throws ServletException if servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String vehicleIdParam = request.getParameter("vehicleId");
        String type = request.getParameter("type");
        String number = request.getParameter("vehicleNumber");
        String fuelType = request.getParameter("fuelType");
        int maxPassengers = Integer.parseInt(request.getParameter("maxPassengers"));
        String currentAssignedRoute = request.getParameter("currentRoute");

        // Retrieve manager ID from session
        HttpSession session = request.getSession(false);
        int managerId = 0;
        if (session != null && session.getAttribute("user") != null) {
            datalayer.User user = (datalayer.User) session.getAttribute("user");
            managerId = user.getUserId();
        }

        // Build vehicle object
        Vehicle vehicle = new VehicleBuilder()
                .setType(type)
                .setNumber(number)
                .setFuelType(fuelType)
                .setMaxPassengers(maxPassengers)
                .setCurrentAssignedRoute(currentAssignedRoute)
                .build();

        vehicle.setManagerId(managerId);

        try (Connection conn = DataSource.getInstance().createConnection()) {
            VehicleDAO vehicleDAO = new VehicleDAO(conn);

            if (vehicleIdParam != null && !vehicleIdParam.isEmpty()) {
                vehicle.setId(Integer.parseInt(vehicleIdParam));
                vehicleDAO.update(vehicle);
            } else {
                vehicleDAO.insert(vehicle);
            }

            // Initialize wear monitor (could be used for predictive maintenance)
            VehicleWearMonitor wearMonitor = new VehicleWearMonitor();
            wearMonitor.updateWear(0); // Reset wear level on registration

            listVehicles(request, response);

        } catch (SQLException e) {
            Logger.getLogger(RegisterVehicleServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("registerVehicleError.html");
        }
    }

    /**
     * Fetches all vehicles and forwards them to the vehicles.jsp view.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    private void listVehicles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DataSource.getInstance().createConnection()) {
            VehicleDAO vehicleDAO = new VehicleDAO(conn);
            List<Vehicle> vehicleList = vehicleDAO.findAll();
            request.setAttribute("vehicleList", vehicleList);
            request.getRequestDispatcher("vehicles.jsp").forward(request, response);
        } catch (SQLException e) {
            Logger.getLogger(RegisterVehicleServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        }
    }
}
