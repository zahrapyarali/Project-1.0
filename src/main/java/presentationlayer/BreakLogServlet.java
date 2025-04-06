package presentationlayer;

import datalayer.BreakLogDAO;
import datalayer.DataSource;
import datalayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;

@WebServlet("/breakLog")
public class BreakLogServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode"); // "start" or "end"
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

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
            }

            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error logging break: " + e.getMessage());
        }
    }
}
