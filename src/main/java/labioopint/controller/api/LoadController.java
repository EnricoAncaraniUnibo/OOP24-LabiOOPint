package labioopint.controller.api;

import java.io.Serializable;

import labioopint.model.utilities.api.Settings;

/**
 * Represents a controller for loading game-related data, such as the last saved
 * game.
 * This interface provides methods to load the last game, check if a game is
 * loaded,
 * and retrieve the associated {@link GameController}.
 */
public interface LoadController extends Serializable {

    /**
     * Loads the last saved game.
     * 
     * @return {@code true} if a game is loaded, {@code false} otherwise
     */
    boolean loadLastGame();

    /**
     * Checks if a game has been successfully loaded.
     *
     * @return {@code true} if a game is loaded, {@code false} otherwise
     */
    boolean isLoadedGame();

    /**
     * Check if the settings have been sucessfully loaded.
     * 
     * @return {@code true} if the settings are loaded {@code false} otherwise
     */
    boolean isLoadedSettings();

    /**
     * Retrieves the {@link GameController} associated with the loaded game.
     *
     * @return the {@link GameController} instance
     */
    GameController getGameController();

    /**
     * Load the saved settings.
     * 
     * @return {@code true} if the settings are loaded, {@code false} otherwise
     */
    boolean loadSettings();

    /**
     * Retrieves the {@link Settings} loaded.
     *
     * @return the {@link Settings} instance
     */
    Settings getSettings();
}
