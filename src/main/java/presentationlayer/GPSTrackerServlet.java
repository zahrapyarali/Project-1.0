package presentationlayer;

import datalayer.GPSTrackingDAO;
import datalayer.GPSTracking;
import utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Handles GPS tracking features:
 * - POST: Logs new GPS location (for Operators)
 * - GET: Displays GPS logs for a vehicle (for Managers)
 */
@WebServlet("/GPSTrackerServlet")
public class GPSTrackerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            String location = request.getParameter("location");
           
            GPSTracking gps = new GPSTracking(0, vehicleId, location, LocalDateTime.now());
            GPSTrackingDAO dao = new GPSTrackingDAO(DBUtils.getConnection());
            dao.save(gps);

            response.sendRedirect("logGps.jsp?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("logGps.jsp?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            GPSTrackingDAO dao = new GPSTrackingDAO(DBUtils.getConnection());
            List<GPSTracking> logs = dao.findByVehicleId(vehicleId);

            request.setAttribute("gpsLogs", logs);
            request.getRequestDispatcher("gpsReport.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error retrieving GPS logs.");
        }
    }
}
