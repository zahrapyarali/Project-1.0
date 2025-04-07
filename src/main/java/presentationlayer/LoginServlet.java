/* File: LoginServlet.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This servlet handles user login functionality using the Strategy design pattern.
 *              It validates user credentials via the AuthenticationStrategy interface and 
 *              redirects users based on login success or failure.
 *              Successfully authenticated users are stored in session.
 */

package presentationlayer;

import businesslayer.AuthenticationStrategy;
import businesslayer.DatabaseAuthentication;
import datalayer.DataSource;
import datalayer.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code LoginServlet} handles user authentication logic. It uses the
 * {@link AuthenticationStrategy} (Strategy design pattern) to validate login credentials.
 * 
 * <p>On successful login, user details are stored in the session and the user is redirected
 * to the dashboard. On failure, the user is redirected to the login page with an error.
 * 
 * @see AuthenticationStrategy
 * @see DatabaseAuthentication
 * @see HttpSession
 * @see User
 * 
 * @author Ambika, Saleha, Sarra, Zahra
 * @date 04-06-2025
 */
public class LoginServlet extends HttpServlet {

    // Strategy instance for authentication
    private AuthenticationStrategy authStrategy;

    // Controller to handle role-based processing (if needed)
    private TransitController controller = new TransitController();

    /**
     * Initializes the authentication strategy.
     * Default implementation uses database authentication.
     */
    public LoginServlet() {
        this.authStrategy = new DatabaseAuthentication();
    }

    /**
     * Initializes the DataSource credentials once when the servlet is first loaded.
     *
     * @throws ServletException if initialization fails
     */
    @Override
    public void init() throws ServletException {
        DataSource dataSource = DataSource.getInstance();
        dataSource.setCredentials("cst8288", "cst8288");
    }

    /**
     * Handles POST requests for user login.
     * Validates credentials and redirects the user based on success or failure.
     *
     * @param request  the HTTP request with email and password
     * @param response the HTTP response (redirects to dashboard or login page)
     * @throws ServletException if servlet-level error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = authStrategy.authenticate(email, password);
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                session.setAttribute("role", user.getRole());
                session.setMaxInactiveInterval(30 * 60); // Session timeout: 30 minutes
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("login.html?error=invalid");
            }
        } catch (SQLException e) {
            response.sendRedirect("login.html?error=invalidexcepttion");
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles GET requests by redirecting to the login page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if servlet-level error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}
