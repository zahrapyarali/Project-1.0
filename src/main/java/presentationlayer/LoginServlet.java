package presentationlayer;

import businesslayer.AuthenticationStrategy;
import businesslayer.DatabaseAuthentication;
import datalayer.DataSource;
import datalayer.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoginServlet extends HttpServlet {
   
    
    @Override
    public void init() throws ServletException {
        // Initialize the DataSource with credentials (only once for the app)
        DataSource dataSource = DataSource.getInstance();
        dataSource.setCredentials("cst8288", "cst8288");
    }

    // Authentication strategy object that handles user authentication    
    private AuthenticationStrategy authStrategy;
    /**
     * Constructor initializes the authentication strategy.
     * The default strategy is DatabaseAuthentication.
     */    
    public LoginServlet() {
        this.authStrategy = new DatabaseAuthentication();
    }    
    
    private TransitController controller = new TransitController();

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
                session.setMaxInactiveInterval(30 * 60);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            // Forward to login.jsp page
        response.sendRedirect("login.html");
    }
}
