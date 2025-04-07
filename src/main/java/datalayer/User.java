package datalayer;

/**
 * Represents a User entity with user details such as id, name, email, password, and role.
 */
public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String role;

    /**
     * Constructor to create a new User without specifying userId.
     * Typically used for creating new users before inserting into the database.
     *
     * @param name     the name of the user
     * @param email    the email of the user
     * @param password the password of the user
     * @param role     the role of the user (e.g., admin, manager, etc.)
     */
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Constructor to create a User with userId.
     * Typically used for retrieving users from the database.
     *
     * @param userId   the unique identifier for the user
     * @param name     the name of the user
     * @param email    the email of the user
     * @param password the password of the user
     * @param role     the role of the user
     */
    public User(int userId, String name, String email, String password, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets the user ID.
     *
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the user's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user's email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the user's role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user ID.
     *
     * @param userId the unique identifier for the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Sets the user's name.
     *
     * @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the user's email.
     *
     * @param email the email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the user's password.
     *
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the user's role.
     *
     * @param role the role assigned to the user
     */
    public void setRole(String role) {
        this.role = role;
    }
}
