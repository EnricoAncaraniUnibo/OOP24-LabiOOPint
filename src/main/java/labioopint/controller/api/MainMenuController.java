package labioopint.controller.api;

import java.io.Serializable;

/**
 * Represents the controller for managing the main menu of the game.
 * This interface provides methods to load the main menu, start a new game,
 * load a saved game, and check if a game is loaded.
 */
public interface MainMenuController extends Serializable {

    /**
     * Starts a new game with the specified settings.
     *
     * @return {@code true} if the game is successfully started, {@code false} otherwise
     */
    boolean startGame();

    /**
     * Loads a previously saved game.
     */
    void loadGame();

    /**
     * Checks if loaded game has been successfully loaded.
     *
     * @return {@code true} if the game is loaded, {@code false} otherwise
     */
    boolean isLoaded();

    /**
     * Open the Settings Menù.
     */
    void openSettings();
}
