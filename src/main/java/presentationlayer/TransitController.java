package presentationlayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TransitController {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            handleUserRegistration(request, response);
        } else if ("report".equals(action)) {
            handleReportGeneration(request, response);
        } else {
            response.getWriter().write("Invalid action");
        }
    }

    public void handleUserRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // Add registration logic here (e.g., call business layer)
        response.getWriter().write("User registered: " + username);
    }

    public void handleReportGeneration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String reportType = request.getParameter("reportType");
        // Add report generation logic here
        response.getWriter().write("Generated report: " + reportType);
    }
}