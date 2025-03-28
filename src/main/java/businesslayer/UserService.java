package businesslayer;

import datalayer.User;
import datalayer.UserDAO;

public class UserService {
    
    private final UserDAO userDAO;
    

    public UserService() {
        this.userDAO = DAOFactory.getUserDAO();
    }

    public boolean registerUser(String name, String email, String password, String role) {
        // Input validation (very basic, can be improved)
        if (name == null || email == null || password == null || role == null) {
            return false;
        }

        // Create new user object
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password); // In real use, hash this
        user.setRole(role);

        // Insert into DB using DAO
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
