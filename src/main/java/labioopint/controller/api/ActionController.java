package labioopint.controller.api;

import java.io.Serializable;

import labioopint.model.core.api.TurnManager;
import labioopint.model.maze.api.Labyrinth;
/**
 * Defines a controller interface for handling actions.
*/
public interface ActionController extends Serializable {
/**
     * Executes a specified action within the provided labyrinth and turn management context.
     * The behavior of this method depends on the concrete implementation, allowing for
     * flexibility in handling different types of actions.
     *
     * @param action       The action to be performed. The type and interpretation of this
     *                     parameter are implementation-specific.
     * @param labyrinth    The labyrinth structure where the action occurs.
     * @param turnManager  The turn manager controlling the game's turn flow.
     */
    void action(Object action, Labyrinth labyrinth, TurnManager turnManager);

}
