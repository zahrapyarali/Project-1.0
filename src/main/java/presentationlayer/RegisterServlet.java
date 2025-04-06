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
 * The RegisterServlet class handles the user registration process.
 * It manages both GET and POST requests for user registration, including validation
 * of input data, creating new user accounts, and interacting with the database to
 * ensure the uniqueness of the email and successful registration.
 * 
 * The class displays a registration form and processes the form submission
 * by validating the input and registering a new user in the system.
 * 
 * It also handles displaying any errors or success messages to the user.
 * 
 * @author cheny
 */
public class RegisterServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // Initialize the DataSource with credentials (only once for the app)
        DataSource dataSource = DataSource.getInstance();
        dataSource.setCredentials("cst8288", "cst8288");
    } 
    
    private TransitController controller = new TransitController();
    
    /**
     * Handles the POST request to register a new user.
     * It validates the form inputs, checks if the email is unique, and then creates
     * a new user record in the database. If successful, the user is redirected to
     * the login page with a success message. If there are validation errors or other
     * issues, it renders the registration page with appropriate error messages.
     * 
     * @param request the HTTP request
     * @param response the HTTP response
     * @throws ServletException if a servlet exception occurs
     * @throws IOException if an I/O exception occurs
     */    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String roleParam = request.getParameter("role"); 
        
        /*
        // Validate required fields
        if (name == null || name.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            renderRegisterPage(response, "All fields are required", name, email, roleParam);
            return;
        }
        
        // Validate email format
        if (!isValidEmail(email)) {
            renderRegisterPage(response, "Invalid email format", name, email, roleParam);
            return;
        }
        */
        try {            
            UserDAO userDAO = new UserDAO(DataSource.getInstance().createConnection());
            
           /* // Check if email is already registered
            if (!userDAO.isEmailUnique(email)) {
                renderRegisterPage(response, "Email already registered", name, email, roleParam);
                return;
            }
            */
            // Create a new user object
            User newUser = new User(0, name, email, password, roleParam);
            
            // Register user
            userDAO.insert(newUser);
            response.sendRedirect("login?success=Registration successful. Please login.");
            
        } catch (SQLException e) {
            e.printStackTrace();
            renderRegisterPage(response, "Database error: " + e.getMessage(), name, email, roleParam);
        }
    }
    /**
     * Handles the GET request to display the registration page.
     * It renders a blank registration form with the default role as "OPERATOR".
     * 
     * @param request the HTTP request
     * @param response the HTTP response
     * @throws ServletException if a servlet exception occurs
     * @throws IOException if an I/O exception occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display blank registration form
        renderRegisterPage(response, null, "", "", "OPERATOR");
    }
    /**
     * Renders the registration page with the provided error message and pre-filled form values.
     * 
     * @param response the HTTP response
     * @param errorMessage the error message to be displayed (can be null)
     * @param name the pre-filled name value
     * @param email the pre-filled email value
     * @param roleParam the pre-filled role value
     * @throws IOException if an I/O exception occurs
     */
    private void renderRegisterPage(HttpServletResponse response, String errorMessage, String name, String email, String roleParam) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Public Transit - Register</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; }");
            out.println(".register-container { background: white; padding: 2rem; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); width: 100%; max-width: 400px; }");
            out.println("h2 { color: #333; text-align: center; margin-bottom: 1.5rem; }");
            out.println(".form-group { margin-bottom: 1rem; }");
            out.println("label { display: block; margin-bottom: 0.5rem; color: #555; }");
            out.println("input[type='text'], input[type='password'], select { width: 100%; padding: 0.75rem; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }");
            out.println("button { width: 100%; padding: 0.75rem; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 1rem; }");
            out.println("button:hover { background-color: #45a049; }");
            out.println(".error { color: #d9534f; margin-bottom: 1rem; text-align: center; }");
            out.println(".login-link { text-align: center; margin-top: 1rem; }");
            out.println("a { color: #337ab7; text-decoration: none; }");
            out.println("a:hover { text-decoration: underline; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='register-container'>");
            out.println("<h2>Register New Account</h2>");
            
            // Display error message if any
            if (errorMessage != null) {
                out.println("<div class='error'>" + errorMessage + "</div>");
            }
            
            out.println("<form method='post'>");
            out.println("<div class='form-group'>");
            out.println("<label for='name'>Full Name</label>");
            out.println("<input type='text' id='name' name='name' value='" + name + "' required>");
            out.println("</div>");
            out.println("<div class='form-group'>");
            out.println("<label for='email'>Email</label>");
            out.println("<input type='text' id='email' name='email' value='" + email + "' required>");
            out.println("</div>");
            out.println("<div class='form-group'>");
            out.println("<label for='password'>Password</label>");
            out.println("<input type='password' id='password' name='password' required>");
            out.println("</div>");
            out.println("<div class='form-group'>");
            out.println("<label for='role'>Role</label>");
            out.println("<select id='role' name='role'>");
            out.println("<option value='OPERATOR'" + ("OPERATOR".equalsIgnoreCase(roleParam) ? " selected" : "") + ">Operator</option>");
            out.println("<option value='MANAGER'" + ("MANAGER".equalsIgnoreCase(roleParam) ? " selected" : "") + ">Manager</option>");
            out.println("</select>");
            out.println("</div>");
            out.println("<button type='submit'>Register</button>");
            out.println("</form>");
            out.println("<div class='login-link'>");
            out.println("Already have an account? <a href='login'>Login here</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    /**
     * Validates the email format using a regular expression.
     * 
     * @param email the email string to be validated
     * @return true if the email format is valid, false otherwise
     */    
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }
}