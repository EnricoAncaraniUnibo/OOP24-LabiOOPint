package labioopint.controller.api;

import java.io.Serializable;

import labioopint.model.core.api.TurnManager;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
/**
 * Represents the main controller for managing the game logic, including player actions,
 * turn management, and labyrinth interactions.
 */
public interface GameController extends Serializable {

    /**
     * Retrieves the current player in the game.
     *
     * @return the current {@link Player}
     */
    Player getCurrentPlayer();

    /**
     * Retrieves the turn manager responsible for handling the game's turn-based logic.
     *
     * @return the {@link TurnManager} instance
     */
    TurnManager getTurnManager();

    /**
     * Retrieves the labyrinth associated with the game.
     *
     * @return the {@link Labyrinth} instance
     */
    Labyrinth getLabyrinth();

    /**
     * Performs an action based on the specified input.
     *
     * @param s the input object representing the action to be performed
     */
    void action(Object s);

}
