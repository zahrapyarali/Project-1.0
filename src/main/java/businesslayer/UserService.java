/* File: UserService.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This class handles business logic for user registration and authentication.
 *              It interacts with the data layer using UserDAO via a DAOFactory, ensuring
 *              proper separation of concerns as part of the 3-tier architecture.
 */

package businesslayer;

import datalayer.DAOFactory;
import datalayer.DataSource;
import datalayer.User;
import datalayer.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The {@code UserService} class manages business operations related to user registration
 * and login authentication. It uses the DAO pattern to interact with the underlying
 * database and handles input validation and exception management.
 *
 * <p>This class is part of the Business Layer in the 3-tier architecture.
 *
 * @see UserDAO
 * @see DAOFactory
 * @see DataSource
 * 
 * @author Ambika, Saleha, Sarra, Zahra
 * @date 04-06-2025
 */
public class UserService {

    private final UserDAO userDAO;

    /**
     * Constructs the {@code UserService} and sets up the DAO using DAOFactory and DataSource.
     *
     * @throws SQLException if database connection cannot be established
     */
    public UserService() throws SQLException {
        // Set database credentials before establishing the connection
        DataSource.getInstance().setCredentials("cst8288", "cst8288");

        // Get the connection from the DataSource
        try (Connection conn = DataSource.getInstance().createConnection()) {

            // Create the DAOFactory without passing a connection to the constructor
            DAOFactory factory = new DAOFactory();

            // Instantiate the UserDAO using the DAOFactory
            this.userDAO = (UserDAO) factory.getDAO("user");

        } catch (SQLException e) {
            throw new SQLException("Error establishing a connection to the database", e);
        }
    }

    /**
     * Registers a new user with the given details.
     *
     * @param name     the user's name
     * @param email    the user's email address
     * @param password the user's password
     * @param role     the user type (Manager or Operator)
     * @return true if registration is successful, false otherwise
     */
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
            return false;
        }
    }

    /**
     * Authenticates a user by verifying their email and password.
     *
     * @param email    the user's email address
     * @param password the user's password
     * @return the {@link User} object if authentication is successful, or null if failed
     */
    public User authenticate(String email, String password) {
        try {
            User user = userDAO.findByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
