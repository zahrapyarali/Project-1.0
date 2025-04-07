/* File: BreakLogServlet.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This servlet handles POST requests for logging operator breaks and 
 *              vehicle out-of-service events. It interacts with the BreakLogDAO 
 *              and stores timestamps for break start, end, or out-of-service logs.
 *              Accessible only to authenticated Operators.
 */

package presentationlayer;

import datalayer.BreakLogDAO;
import datalayer.DataSource;
import datalayer.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;

/**
 * The {@code BreakLogServlet} handles logging break-related events such as:
 * - Start of a break
 * - End of a break
 * - Marking a vehicle as out-of-service
 *
 * <p>This servlet is accessible only by authenticated Operators and uses the
 * {@link BreakLogDAO} to persist logs in the database.
 * 
 * <p>It supports the following request modes:
 * <ul>
 *   <li><b>start</b>: logs break start time</li>
 *   <li><b>end</b>: logs break end time</li>
 *   <li><b>outofservice</b>: logs a one-time out-of-service event</li>
 * </ul>
 * 
 * @see BreakLogDAO
 * @see HttpServlet
 * @see HttpSession
 * @see User
 * 
 * @author Ambika, Saleha, Sarra, Zahra
 * @date 04-06-2025
 */
public class BreakLogServlet extends HttpServlet {

    /**
     * Processes POST requests to log break or out-of-service events.
     *
     * @param request  the HTTP request containing mode, vehicleId, and session info
     * @param response the HTTP response redirected to the dashboard or error output
     * @throws ServletException if servlet processing fails
     * @throws IOException      if I/O error occurs during processing
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mode = request.getParameter("mode"); // "start", "end", or "outofservice"
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        System.out.println("Mode: " + mode);
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Operator ID: " + user.getUserId());

        if (user == null || !"Operator".equals(user.getRole())) {
            response.sendRedirect("login.html");
            return;
        }

        try (Connection conn = DataSource.getInstance().createConnection()) {
            BreakLogDAO dao = new BreakLogDAO(conn);
            LocalDateTime now = LocalDateTime.now();

            switch (mode) {
                case "start" -> dao.startBreak(user.getUserId(), vehicleId, now);
                case "end" -> dao.endBreak(user.getUserId(), vehicleId, now);
                case "outofservice" -> dao.logOutOfService(user.getUserId(), vehicleId, now);
            }

            response.sendRedirect("dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error logging break: " + e.getMessage());
        }
    }
}
