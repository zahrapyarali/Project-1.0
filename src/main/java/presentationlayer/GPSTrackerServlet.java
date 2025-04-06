package presentationlayer;

import datalayer.GPSTracking;
import datalayer.GPSTrackingDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;

public class GPSTrackerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            String location = request.getParameter("location");
            String status = request.getParameter("status");
            LocalDateTime timestamp = LocalDateTime.now();

            // Save to database
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(

                    "jdbc:mysql://localhost:3306/public_transit_db", "cst8288", "cst8288");

            GPSTracking gpsData = new GPSTracking(0, vehicleId, location, timestamp, status);
            GPSTrackingDAO gpsDao = new GPSTrackingDAO(conn);
            gpsDao.insertTracking(gpsData);

            conn.close();

            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error occurred: " + e.getMessage());
        }
    }
}
