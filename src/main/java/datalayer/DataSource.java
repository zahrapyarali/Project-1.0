package datalayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class to manage database connections.
 * Encapsulates JDBC connection creation and credentials handling.
 */
public class DataSource {

    // Singleton instance of DataSource
    private static DataSource instance;

    // JDBC URL for the MySQL database
    private final String url = "jdbc:mysql://localhost:3306/public_transit_db";

    // Database credentials
    private String user, password;
    /**
     * Private constructor to prevent direct instantiation.
     * Use getInstance() to obtain the singleton object.
     */
    private DataSource() { }

    /**
     * Returns the singleton instance of DataSource.
     * Uses double-checked locking for thread safety.
     *
     * @return the singleton DataSource instance
     */
    public static DataSource getInstance() {
        if (instance == null) {
            synchronized (DataSource.class) {
                if (instance == null) instance = new DataSource();
            }
        }
        return instance;
    }

    /**
     * Sets the database credentials.
     * Must be called before attempting to create a connection.
     *
     * @param user     the database username
     * @param password the database password
     */
    public void setCredentials(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * Creates a new database connection using the stored credentials.
     *
     * @return a new Connection object
     * @throws SQLException if a database access error occurs
     */
    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
