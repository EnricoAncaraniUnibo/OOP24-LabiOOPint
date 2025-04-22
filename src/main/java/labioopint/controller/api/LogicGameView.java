package labioopint.controller.api;

public interface LogicGameView {

    /**
     * Retrieves the current turn information as a string.
     *
     * @return the current turn information.
     */
    String getTurn();

    /**
     * Retrieves the current action information as a string.
     *
     * @return the current action information.
     */
    String getAction();

    /**
     * Retrieves a list of available power-ups as an array of strings.
     *
     * @return an array of power-up names.
     */
    String[] getPowerUps();

    /**
     * Activates the specified power-up for the current player.
     *
     * @param powerUp the name of the power-up to activate.
     */
    void activatePowerUps(String powerUp);

    /**
     * Executes an action based on the specified action name.
     *
     * @param name the name of the action to execute.
     */
    void useAction(String name);

    /**
     * Handles mouse input to perform an action based on the clicked coordinates.
     *
     * @param x    the x-coordinate of the mouse click.
     * @param y    the y-coordinate of the mouse click.
     * @param size the size of a single block in the grid.
     */
    void mouseAction(int x, int y, int size);

    /**
     * Checks if the current action is block placement.
     *
     * @return true if the current action is block placement, false otherwise.
     */
    Boolean isBlockPlacement();

    /**
     * Checks if a winner is present in the game.
     *
     * @return true if a winner is present, false otherwise.
     */
    Boolean isWinnerPresent();

    /**
     * Retrieves the name of the winner if present.
     *
     * @return the name of the winner.
     */
    String getWinner();

    /**
     * Closes the game by terminating the application.
     */
    void close();

}
