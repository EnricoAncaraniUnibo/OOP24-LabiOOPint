package labioopint.model.core.impl;

import labioopint.model.utilities.impl.ActionType;
import labioopint.model.core.api.TurnManager;


/**
 * Manages the turns and actions in the game, including players, enemies, and
 * power-ups.
 */
public final class TurnManagerImpl implements TurnManager {
    public static final long serialVersionUID = 1L;
    private ActionType currentAction;
    private int currentPlayer;
    private final int numberOfPlayers;
    private boolean doubleTurn = false;

    /**
     * Construction of the {@code TurnManagerImpl} with the number of players.
     * 
     * @param numberOfPlayers number of players playing the game.
     */
    public TurnManagerImpl(final int numberOfPlayers) {
        currentAction = ActionType.BLOCK_PLACEMENT;
        currentPlayer = 0;
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public void repeatPlayerTurn() {
        doubleTurn = true;
    }

    @Override
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public ActionType getCurrentAction() {
        return currentAction;
    }

    @Override
    public void nextAction() {
        if (currentAction == ActionType.BLOCK_PLACEMENT) {
            currentAction = ActionType.PLAYER_MOVEMENT;
        } else if (currentAction == ActionType.PLAYER_MOVEMENT && doubleTurn == false) {
            currentPlayer = (currentPlayer + 1) % numberOfPlayers;
            currentAction = ActionType.BLOCK_PLACEMENT;
        } else {
            doubleTurn = false;
            currentAction = ActionType.BLOCK_PLACEMENT;
        }
    }

    @Override
    public boolean getDoubleTurnValue() {
        return doubleTurn;
    }
}
