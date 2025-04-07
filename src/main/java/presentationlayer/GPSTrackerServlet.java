/* File: GPSTrackerServlet.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This servlet handles POST requests to log GPS tracking data from vehicles.
 *              It stores the vehicle's current location, status, and timestamp in the database.
 *              Used for real-time tracking and reporting of vehicle movements.
 */

package presentationlayer;

import datalayer.GPSTracking;
import datalayer.GPSTrackingDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;

/**
 * The {@code GPSTrackerServlet} receives GPS data via POST requests and stores it
 * in the database using {@link GPSTrackingDAO}. This data is used for tracking 
 * vehicle locations and statuses such as "Arrival" or "Departure."
 *
 * <p>Each request must contain:
 * <ul>
 *   <li>{@code vehicleId} – the vehicle being tracked</li>
 *   <li>{@code location} – current location of the vehicle</li>
 *   <li>{@code status} – either "Arrival" or "Departure"</li>
 * </ul>
 */
public class GPSTrackerServlet extends HttpServlet {

    /**
     * Handles POST requests to log GPS data for a vehicle.
     *
     * @param request  the HTTP request containing vehicleId, location, and status
     * @param response the HTTP response that redirects to dashboard or displays errors
     * @throws ServletException if the servlet encounters an issue
     * @throws IOException      if input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            String location = request.getParameter("location");
            String status = request.getParameter("status");
            LocalDateTime timestamp = LocalDateTime.now();

            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/public_transit_db", "cst8288", "cst8288");

            // Create GPS tracking data object
            GPSTracking gpsData = new GPSTracking(0, vehicleId, location, timestamp, status);

            // Insert GPS data into the database
            GPSTrackingDAO gpsDao = new GPSTrackingDAO(conn);
            gpsDao.insertTracking(gpsData);

            conn.close();

            // Redirect to dashboard after successful insert
            response.sendRedirect("dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error occurred: " + e.getMessage());
        }
    }
}
