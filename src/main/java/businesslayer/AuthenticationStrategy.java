/* File: AuthenticationStrategy.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This interface defines the contract for user authentication strategies
 *              used in the Public Transit Fleet Management System. Implementations are
 *              responsible for validating user credentials and returning a User object
 *              if authentication is successful.
 */

package businesslayer;

import datalayer.User;

/**
 * The {@code AuthenticationStrategy} interface defines the contract for implementing
 * different user authentication mechanisms within the system.
 *
 * <p>Implementations of this interface are responsible for validating user credentials
 * (email and password) and returning a {@link User} object if authentication is successful.
 *
 * <p>This allows flexibility to support different authentication approaches (e.g., database-based,
 * third-party OAuth, mock/testing).
 *
 * @author [Your Name]
 */
public interface AuthenticationStrategy {

    /**
     * Authenticates a user based on the provided email and password.
     *
     * @param email the user's email address used for login
     * @param password the user's password
     * @return the authenticated {@link User} object if credentials are valid
     * @throws Exception if authentication fails or an error occurs (e.g., user not found)
     */
    User authenticate(String email, String password) throws Exception;

}
