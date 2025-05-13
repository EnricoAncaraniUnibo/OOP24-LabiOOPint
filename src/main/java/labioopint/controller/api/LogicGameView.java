package labioopint.controller.api;
/**
 * Represents the logic for managing the game view, including actions, power-ups, 
 * turn information, and winner status. This interface provides methods to interact 
 * with the game view and perform various game-related operations.
 */
public interface LogicGameView {

    /**
     * Retrieves the current turn information.
     *
     * @return a {@link String} representing the current turn
     */
    String getTurn();

    /**
     * Retrieves the current action being performed.
     *
     * @return a {@link String} representing the current action
     */
    String getAction();

    /**
     * Retrieves the list of available power-ups.
     *
     * @return an array of {@link String} containing the power-ups
     */
    String[] getPowerUps();

    /**
     * Uses a specified action by its name.
     *
     * @param name the name of the action to be used
     */
    void useAction(String name);

    /**
     * Performs a mouse action at the specified coordinates and size.
     *
     * @param x the x-coordinate of the mouse action
     * @param y the y-coordinate of the mouse action
     * @param size the size of the action
     */
    void mouseAction(int x, int y, int size);

    /**
     * Determines if block placement is allowed.
     *
     * @return {@code true} if block placement is allowed, {@code false} otherwise
     */
    Boolean isBlockPlacement();

    /**
     * Checks if a winner is present in the game.
     *
     * @return {@code true} if a winner is present, {@code false} otherwise
     */
    Boolean isWinnerPresent();

    /**
     * Retrieves the winner of the game.
     *
     * @return a {@link String} representing the winner's name
     */
    String getWinner();

    /**
     * Closes the game view.
     */
    void close();

    /**
     * Retrieves the scores of players in the game.
     *
     * @return a {@link String} representing the scores
     */
    String getScores();

}
