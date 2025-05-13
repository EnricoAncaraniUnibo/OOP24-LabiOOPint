package labioopint.controller.api;
/**
 * Represents a controller for loading game-related data, such as the last saved game.
 * This interface provides methods to load the last game, check if a game is loaded,
 * and retrieve the associated {@link GameController}.
 */
public interface LoadController {

    /**
     * Loads the last saved game.
     */
    void loadLastGame();

    /**
     * Checks if a game has been successfully loaded.
     *
     * @return {@code true} if a game is loaded, {@code false} otherwise
     */
    boolean isLoaded();

    /**
     * Retrieves the {@link GameController} associated with the loaded game.
     *
     * @return the {@link GameController} instance
     */
    GameController getGameController();

}
