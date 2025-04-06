package businesslayer;

import datalayer.DataSource;
import datalayer.User;
import datalayer.UserDAO;
import java.sql.SQLException;



public class DatabaseAuthentication implements AuthenticationStrategy {

    @Override
    public User authenticate(String email, String password) throws SQLException {
        UserDAO userDAO = new UserDAO(DataSource.getInstance().createConnection());
        return userDAO.loginUser(email, password);
    }

}

