package presentationlayer;

import datalayer.BreakLogDAO;
import datalayer.DataSource;
import datalayer.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;

public class BreakLogServlet extends HttpServlet {
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            if ("start".equals(mode)) {
                dao.startBreak(user.getUserId(), vehicleId, now);
            } else if ("end".equals(mode)) {
                dao.endBreak(user.getUserId(), vehicleId, now);
            } else if ("outofservice".equals(mode)) {
                dao.logOutOfService(user.getUserId(), vehicleId, now); // <-- new method
            }

            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error logging break: " + e.getMessage());
        }
    }
}