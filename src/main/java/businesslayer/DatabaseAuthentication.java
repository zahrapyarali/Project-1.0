/* File: DatabaseAuthentication.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This class implements the AuthenticationStrategy interface to provide
 *              database-based user authentication. It uses UserDAO to validate user
 *              credentials against records in the MySQL database.
 */

package businesslayer;

import datalayer.DataSource;
import datalayer.User;
import datalayer.UserDAO;
import java.sql.SQLException;

/**
 * The {@code DatabaseAuthentication} class provides an implementation of the
 * {@link AuthenticationStrategy} interface using a relational database.
 *
 * <p>This class uses {@code UserDAO} and a connection from the {@code DataSource}
 * singleton to authenticate users by checking email and password credentials.
 *
 * @author Ambika, Saleha, Sarra, Zahra
 * @date 04-06-2025
 */
public class DatabaseAuthentication implements AuthenticationStrategy {

    /**
     * Authenticates a user by checking the provided email and password
     * against stored records in the database.
     *
     * @param email the user's email
     * @param password the user's password
     * @return the {@link User} object if authentication is successful
     * @throws SQLException if a database access error occurs
     */
    @Override
    public User authenticate(String email, String password) throws SQLException {
        UserDAO userDAO = new UserDAO(DataSource.getInstance().createConnection());
        return userDAO.loginUser(email, password);
    }
}
