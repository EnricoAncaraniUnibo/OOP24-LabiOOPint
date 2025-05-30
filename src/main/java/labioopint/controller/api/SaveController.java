package labioopint.controller.api;

import java.io.Serializable;

import labioopint.model.utilities.api.Settings;
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
    boolean saveGame(GameController gc);

    /**
     * Save the selected settings.
     * 
     * @param settings the settings to be saved
     * @return {@code true} if the settings has been saved correctly, {@code false} otherwise
     */
    boolean saveSettings(Settings settings);

}
