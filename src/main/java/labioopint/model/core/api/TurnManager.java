package labioopint.model.core.api;

import java.io.Serializable;

import labioopint.model.utilities.impl.ActionType;
/**
 * Represents the turn manager in the game.
 * This interface provides methods to manage and control the flow of turns
 * during the game, including determining the current turn and advancing to the next turn.
 */
public interface TurnManager extends Serializable {

    /**
     * Repeats the current player's turn.
     * This method allows the current player to perform their turn again.
     */
    void repeatPlayerTurn();

    /**
     * Retrieves the current player's identifier.
     *
     * @return the identifier of the current player as an integer
     */
    int getCurrentPlayer();

    /**
     * Retrieves the current action type for the turn.
     *
     * @return the {@link ActionType} representing the current action
     */
    ActionType getCurrentAction();

    /**
     * Advances the game to the next action.
     * This method updates the state of the game to reflect the transition to the next action.
     */
    void nextAction();

    boolean getDoubleTurnValue();

}
