/* File: DAO.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This interface defines the generic Data Access Object (DAO) pattern.
 *              It provides basic CRUD operations to interact with persistent storage
 *              for any entity type in the system.
 */

package businesslayer;

import java.util.List;

/**
 * The {@code DAO<T>} interface defines a generic contract for data access operations
 * such as insert, update, delete, and retrieval for any type of object.
 *
 * <p>This interface supports the DAO design pattern to separate the persistence
 * logic from the business logic, promoting modularity and maintainability.
 *
 * @param <T> the type of the entity managed by the DAO
 */
public interface DAO<T> {

    /**
     * Inserts the given object into the data source.
     *
     * @param obj the object to insert
     */
    void insert(T obj);

    /**
     * Updates the given object in the data source.
     *
     * @param obj the object to update
     */
    void update(T obj);

    /**
     * Deletes the object with the specified ID from the data source.
     *
     * @param id the ID of the object to delete
     */
    void delete(int id);

    /**
     * Retrieves all objects of type {@code T} from the data source.
     *
     * @return a list of all objects
     */
    List<T> findAll();

    /**
     * Finds a single object by its unique ID.
     *
     * @param id the ID of the object to find
     * @return the object if found, or {@code null} otherwise
     */
    Object findById(int id);
}
