package datalayer;

import businesslayer.DAO;
import java.sql.*;
import java.util.*;

/**
 * UserDAO class implements data access operations for User entity.
 * Provides methods to perform CRUD operations and user-specific queries.
 * 
 * @author Ambika Gadhvi, Saleha Qareen, Sarra Derdar, Zahra Pyarali
 */
public class UserDAO implements DAO<User> {

    private Connection conn;

    /**
     * Constructor to initialize UserDAO with a database connection.
     * 
     * @param conn The database connection.
     */
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Default constructor throws UnsupportedOperationException.
     */
    UserDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Inserts a new user into the database.
     * 
     * @param user The User object to insert.
     */
    @Override
    public void insert(User user) {
        String sql = "INSERT INTO user (name, email, password, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing user in the database.
     * 
     * @param user The User object with updated details.
     */
    @Override
    public void update(User user) {
        String sql = "UPDATE user SET name=?, email=?, password=?, role=? WHERE user_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setInt(5, user.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a user from the database based on user ID.
     * 
     * @param id The ID of the user to delete.
     */
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM user WHERE user_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds a user by their unique ID.
     * 
     * @param id The ID of the user.
     * @return The User object if found, otherwise null.
     */
    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM user WHERE user_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     * 
     * @return A list of all User objects.
     */
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Finds a user by their email address.
     * 
     * @param email The email of the user.
     * @return The User object if found, otherwise null.
     */
    public User findByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if the given email is unique in the database.
     * 
     * @param email The email to check.
     * @return {@code true} if the email is unique, {@code false} if the email already exists.
     * @throws SQLException If there is an error while interacting with the database.
     */
    public boolean isEmailUnique(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) == 0; // If count == 0, email is unique
        }
    }

    /**
     * Logs in a user by checking the email and password.
     * If a user with the given email exists and the password matches, the user is returned.
     * 
     * @param email The email of the user trying to log in.
     * @param password The password of the user trying to log in.
     * @return A {@code User} object if the email and password match, {@code null} otherwise.
     * @throws SQLException If there is an error while interacting with the database.
     */
    public User loginUser(String email, String password) throws SQLException {
        String query = "SELECT id, name, email, password, role FROM user WHERE email = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(password)) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                    );
                }
            }
        } catch (IllegalArgumentException e) {
            // If the role in the database is not "MANAGER" or "OPERATOR", throw an exception
            throw new SQLException("Invalid role in database: " + e.getMessage());
        }
        return null; // User not found or password incorrect
    }
}
