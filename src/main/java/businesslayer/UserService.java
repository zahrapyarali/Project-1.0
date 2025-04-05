package businesslayer;

import datalayer.DAOFactory;
import datalayer.User;
import datalayer.UserDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserService {

    private final UserDAO userDAO;

    

    public UserService() throws SQLException {
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/your_db", "username", "password"
        );
        DAOFactory factory = new DAOFactory(conn);
        this.userDAO = (UserDAO) factory.getDAO("user");
    }


    // ... authenticate and registerUser methods unchanged


    public boolean registerUser(String name, String email, String password, String role) {
        if (name == null || email == null || password == null || role == null) {
            return false;
        }

        // Create a new user instance
        User user = new User(name, email, password, role);

        try {
            // Insert the user into the database
            userDAO.insert(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // Return false in case of an error
        }
    }

    public User authenticate(String email, String password) {
        try {
            // Retrieve user by email
            User user = userDAO.findByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;  // Return null if authentication fails
    }
}
