package labioopint.view;

import labioopint.controller.api.GameController;
import labioopint.controller.api.InformationMessenger;
import labioopint.controller.impl.GameControllerImpl;
import labioopint.controller.impl.InformationMessengerImpl;
import labioopint.model.api.ActionType;
import labioopint.model.api.Coordinate;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.powerup.api.PowerUp;

/**
 * The LogicGameView class acts as a bridge between the game logic and the
 * graphical user interface.
 * It provides methods to retrieve game state information and perform actions
 * based on user input.
 */
public class LogicGameView {

    private final TurnManager turn;
    private final InformationMessenger ifm;
    private final GameController gc;

    /**
     * Constructs a new LogicGameView instance.
     *
     * @param tu the TurnManager instance used to manage the game's turns.
     */
    public LogicGameView(final TurnManager tu) {
        turn = tu;
        ifm = new InformationMessengerImpl(turn);
        gc = new GameControllerImpl(tu);
    }

    /**
     * Retrieves the current turn information as a string.
     *
     * @return the current turn information.
     */
    public String getTurn() {
        return ifm.getTurn();
    }

    /**
     * Retrieves the current action information as a string.
     *
     * @return the current action information.
     */
    public String getAction() {
        return ifm.getAction();
    }

    /**
     * Retrieves a list of available power-ups as an array of strings.
     *
     * @return an array of power-up names.
     */
    public String[] getPowerUps() {
        return ifm.getPowerUpsList();
    }

    /**
     * Activates the specified power-up for the current player.
     *
     * @param powerUp the name of the power-up to activate.
     */
    public void activatePowerUps(final String powerUp) {
        for (final PowerUp pu : turn.getPowerUps()) {
            if (pu.getName().equals(powerUp)) {
                pu.activate(turn.getCurrentPlayer());
            }
        }
    }

    /**
     * Executes an action based on the specified action name.
     *
     * @param name the name of the action to execute.
     */
    public void useAction(final String name) {
        gc.action(name);
    }

    /**
     * Handles mouse input to perform an action based on the clicked coordinates.
     *
     * @param x    the x-coordinate of the mouse click.
     * @param y    the y-coordinate of the mouse click.
     * @param size the size of a single block in the grid.
     */
    public void mouseAction(final int x, final int y, final int size) {
        final Coordinate newCoordinate = new Coordinate((y % size < size / 2) ? y / size - 1 : y / size, x / size);
        gc.action(newCoordinate);
    }

    /**
     * Checks if the current action is block placement.
     *
     * @return true if the current action is block placement, false otherwise.
     */
    public Boolean isBlockPlacement() {
        return turn.getCurrentAction() == ActionType.BLOCK_PLACEMENT;
    }

    /**
     * Checks if a winner is present in the game.
     *
     * @return true if a winner is present, false otherwise.
     */
    public Boolean isWinnerPresent() {
        return ifm.getWinner().isPresent();
    }

    /**
     * Retrieves the name of the winner if present.
     *
     * @return the name of the winner.
     */
    public String getWinner() {
        return ifm.getWinner().get();
    }

    /**
     * Closes the game by terminating the application.
     */
    public void close() {
        System.exit(0);
    }
}
