package presentationlayer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * The LogoutServlet class handles the user logout process.
 * It is responsible for invalidating the user's session and redirecting
 * them to the login page with a message indicating that they have been logged out.
 * 
 * This servlet only handles GET requests to perform the logout action.
 * It is typically used to end a user session when they choose to log out of the application.
 * 
 * @author cheny
 */
public class LogoutServlet extends HttpServlet {
    /**
     * Handles the GET request to log out the user.
     * It invalidates the current session if it exists and redirects the user
     * to the login page with a logout confirmation message.
     * 
     * @param request the HTTP request
     * @param response the HTTP response
     * @throws ServletException if a servlet exception occurs
     * @throws IOException if an I/O exception occurs
     */    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the current session, if it exists        
        HttpSession session = request.getSession(false);
        // If session exists, invalidate it        
        if (session != null) {
            session.invalidate();
        }
        // Redirect the user to the login page with a logout message        
        response.sendRedirect("login?message=You have been logged out");
    }
}