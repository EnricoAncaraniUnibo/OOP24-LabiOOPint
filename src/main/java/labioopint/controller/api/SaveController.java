package labioopint.controller.api;

import java.io.Serializable;
/**
 * Represents the controller responsible for saving the game state.
 * This interface provides a method to save the current game using a {@link GameController}.
 */
public interface SaveController extends Serializable {

    /**
     * Saves the current game state.
     *
     * @param gc the {@link GameController} instance representing the current game
     * @return true if the saving is done succesfully, false otherwise
     */
    boolean save(GameController gc);

}
