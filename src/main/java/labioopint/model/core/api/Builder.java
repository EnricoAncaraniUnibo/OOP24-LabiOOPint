package labioopint.model.core.api;

import labioopint.controller.api.ActionController;
import labioopint.model.maze.api.Labyrinth;
/**
 * Represents a builder for creating core game components.
 * This interface provides methods to create the labyrinth, turn manager, and action controller
 * required for the game.
 */
public interface Builder {

    /**
     * Creates and returns a new labyrinth for the game.
     *
     * @return the {@link Labyrinth} instance representing the game maze
     */
    Labyrinth createMaze();

    /**
     * Creates and returns a new turn manager for the game.
     *
     * @return the {@link TurnManager} instance responsible for managing game turns
     */
    TurnManager createTurnManager();

    /**
     * Creates and returns a new action controller for the game.
     *
     * @return the {@link ActionController} instance responsible for handling game actions
     */
    ActionController createActionController();

}
