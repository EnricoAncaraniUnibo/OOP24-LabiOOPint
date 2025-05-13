package labioopint.controller.api;
/**
 * Represents the logic for managing the main menu of the game.
 * This interface provides methods to start a new game, load a saved game,
 * check if a game is loaded, open the settings menu, and quit the game.
 */
public interface MainMenuLogic {

    /**
     * Starts a new game.
     */
    void startGame();

    /**
     * Loads a previously saved game.
     */
    void loadGame();

    /**
     * Checks if the previous game has been successfully loaded.
     *
     * @return {@code true} if the game is loaded, {@code false} otherwise
     */
    boolean isLoaded();

    /**
     * Opens the settings menu.
     */
    void openSettings();

    /**
     * Quits the game.
     */
    void quitGame();

}
