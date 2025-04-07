/* File: RegisterServlet.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This servlet handles user registration for the public transit system.
 *              It displays a registration form and processes submitted user data 
 *              using the UserDAO class. It supports both GET and POST methods.
 */

package presentationlayer;

import datalayer.DataSource;
import datalayer.User;
import datalayer.UserDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * The {@code RegisterServlet} is responsible for rendering the registration page and
 * handling user submissions for account creation.
 *
 * <p>It supports:
 * <ul>
 *   <li>Displaying a styled registration form (GET request)</li>
 *   <li>Validating and saving new users to the database (POST request)</li>
 * </ul>
 *
 * <p>Note: This servlet is part of the Presentation Layer in the 3-tier architecture.
 *
 * @see User
 * @see UserDAO
 * @see DataSource
 * @see HttpServlet
 * 
 * @author Ambika, Saleha, Sarra, Zahra
 * @date 04-06-2025
 */
public class RegisterServlet extends HttpServlet {

    private TransitController controller = new TransitController();

    /**
     * Initializes the database connection credentials using {@link DataSource}.
     *
     * @throws ServletException if servlet initialization fails
     */
    @Override
    public void init() throws ServletException {
        DataSource.getInstance().setCredentials("cst8288", "cst8288");
    }

    /**
     * Processes POST request to register a new user.
     *
     * @param request  HTTP request containing form inputs
     * @param response HTTP response to redirect or display errors
     * @throws ServletException if servlet error occurs
     * @throws IOException      if I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String roleParam = request.getParameter("role");

        try {
            UserDAO userDAO = new UserDAO(DataSource.getInstance().createConnection());
            User newUser = new User(0, name, email, password, roleParam);
            userDAO.insert(newUser);
            response.sendRedirect("login?success=Registration successful. Please login.");

        } catch (SQLException e) {
            e.printStackTrace();
            renderRegisterPage(response, "Database error: " + e.getMessage(), name, email, roleParam);
        }
    }

    /**
     * Displays the registration form (GET request).
     *
     * @param request  HTTP request
     * @param response HTTP response that renders the form
     * @throws ServletException if servlet error occurs
     * @throws IOException      if I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        renderRegisterPage(response, null, "", "", "OPERATOR");
    }

    /**
     * Renders the registration page with optional pre-filled data and error messages.
     *
     * @param response     HTTP response to write HTML
     * @param errorMessage optional error message
     * @param name         pre-filled name value
     * @param email        pre-filled email value
     * @param roleParam    pre-selected role
     * @throws IOException if I/O error occurs
     */
    private void renderRegisterPage(HttpServletResponse response, String errorMessage,
                                    String name, String email, String roleParam) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Public Transit - Register</title>");
            out.println("<style>");
            out.println("body { font-family: Arial; background-color: #f4f4f9; display: flex; justify-content: center; align-items: center; height: 100vh; }");
            out.println(".register-container { background: white; padding: 2rem; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 400px; width: 100%; }");
            out.println(".form-group { margin-bottom: 1rem; }");
            out.println("label { display: block; margin-bottom: 0.5rem; }");
            out.println("input, select, button { width: 100%; padding: 0.75rem; margin-bottom: 0.5rem; border-radius: 4px; }");
            out.println(".error { color: #d9534f; text-align: center; margin-bottom: 1rem; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='register-container'>");
            out.println("<h2>Register New Account</h2>");
            if (errorMessage != null) {
                out.println("<div class='error'>" + errorMessage + "</div>");
            }
            out.println("<form method='post'>");
            out.println("<div class='form-group'><label for='name'>Full Name</label>");
            out.println("<input type='text' name='name' value='" + name + "' required></div>");
            out.println("<div class='form-group'><label for='email'>Email</label>");
            out.println("<input type='text' name='email' value='" + email + "' required></div>");
            out.println("<div class='form-group'><label for='password'>Password</label>");
            out.println("<input type='password' name='password' required></div>");
            out.println("<div class='form-group'><label for='role'>Role</label>");
            out.println("<select name='role'>");
            out.println("<option value='OPERATOR'" + ("OPERATOR".equalsIgnoreCase(roleParam) ? " selected" : "") + ">Operator</option>");
            out.println("<option value='MANAGER'" + ("MANAGER".equalsIgnoreCase(roleParam) ? " selected" : "") + ">Manager</option>");
            out.println("</select></div>");
            out.println("<button type='submit'>Register</button>");
            out.println("</form>");
            out.println("<div class='login-link'>Already have an account? <a href='login'>Login here</a></div>");
            out.println("</div></body></html>");
        }
    }

    /**
     * Validates email format using regex.
     *
     * @param email the email to check
     * @return true if email format is valid
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }
}
