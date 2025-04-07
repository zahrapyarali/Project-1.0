/* File: Command.java
 * Author: Ambika, Saleha, Sarra, Zahra
 * Date: 04-06-2025
 * Description: This interface represents the Command design pattern.
 *              It defines a generic execute method to encapsulate a request
 *              as an object, allowing parameterization and queuing of actions.
 */

package businesslayer;

/**
 * The {@code Command} interface represents a generic command in the system.
 *
 * <p>This interface is used to implement the Command design pattern, allowing
 * different actions to be encapsulated as objects that can be executed, queued,
 * or logged. This pattern is especially useful for operations like scheduling
 * maintenance tasks.
 */
public interface Command {

    /**
     * Executes the command action.
     */
    void execute();
}
