package businesslayer;

import datalayer.DAOFactory;
import datalayer.User;
import datalayer.UserDAO;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = DAOFactory.getUserDAO();
    }

    public boolean registerUser(String name, String email, String password, String role) {
        if (name == null || email == null || password == null || role == null) {
            return false;
        }

        // Match this constructor to the one in your User class
        User user = new User(name, email, password, role);

        return userDAO.insert(user);
    }

    public User authenticate(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
