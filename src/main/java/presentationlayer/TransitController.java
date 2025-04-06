package presentationlayer;

import datalayer.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
public class TransitController {

//    public User authenticateUser(String email, String password) throws SQLException {
//    // Dummy logic — Replace this with real DB call or business logic
//    if ("admin@example.com".equals(email) && "admin123".equals(password)) {
//        return new User(1, "Admin", email, password, "admin");
//    } else if ("user@example.com".equals(email) && "user123".equals(password)) {
//        return new User(2, "Regular User", email, password, "user");
//    }
//    return null; // Invalid credentials}

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
        // Add real registration logic here
        response.getWriter().write("User registered: " + username);
    }

    public void handleReportGeneration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String reportType = request.getParameter("reportType");
        // Add real report generation logic here
        response.getWriter().write("Generated report: " + reportType);
    }
}
