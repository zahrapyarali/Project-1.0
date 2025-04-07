/* File: TransitController.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: Controller class that handles communication between presentation 
 *              layer and business logic for user registration and report generation.
 *              Acts as the central dispatcher in a servlet-based 3-tier architecture.
 */

package presentationlayer;

import datalayer.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The {@code TransitController} class acts as a centralized controller for handling 
 * different user actions like registration and report generation. It simulates the 
 * functionality of a front controller in MVC architecture.
 *
 * <p>Supports the following actions:
 * <ul>
 *   <li><b>register</b> – processes user registration</li>
 *   <li><b>report</b> – processes report generation</li>
 * </ul>
 *
 * @author Ambika, Saleha, Sarra, Zahra
 * @date 04-06-2025
 */
public class TransitController {

    /**
     * Dispatches POST requests based on the "action" parameter.
     *
     * @param request  the HTTP request containing action parameters
     * @param response the HTTP response to be written
     * @throws IOException if writing to response fails
     */
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

    /**
     * Handles user registration.
     * This is a placeholder for actual logic that would insert a user into the database.
     *
     * @param request  the HTTP request containing user details
     * @param response the HTTP response confirming registration
     * @throws IOException if response output fails
     */
    public void handleUserRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // TODO: Add real registration logic with validation and DB insert
        response.getWriter().write("User registered: " + username);
    }

    /**
     * Handles report generation.
     * This is a placeholder for actual report processing logic.
     *
     * @param request  the HTTP request with report type info
     * @param response the HTTP response with a simulated report message
     * @throws IOException if writing to response fails
     */
    public void handleReportGeneration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String reportType = request.getParameter("reportType");

        // TODO: Add real report generation logic
        response.getWriter().write("Generated report: " + reportType);
    }
}
