package labioopint.controller.impl;

import labioopint.controller.api.GameController;
import labioopint.controller.api.InformationMessenger;
import labioopint.controller.api.LogicGameView;
import labioopint.model.utilities.impl.ActionType;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.utilities.api.Coordinate;

/**
 * Implementation of the Logic of the GameView.
 */
public final class LogicGameViewImpl implements LogicGameView {
    public static final long serialVersionUID = 1L;
    private final GameController gameController;
    private final InformationMessenger informationMessenger;

    /**
     * Construction of a {@code LogicGameViewImpl} with an GameController.
     * 
     * @param gameController the game we want to see on the screen
     */
    public LogicGameViewImpl(final GameController gameController) {
        this.gameController = gameController;
        informationMessenger = new InformationMessengerImpl();
    }

    @Override
    public String getTurn() {
        return informationMessenger.getTurn(gameController);
    }

    @Override
    public String getAction() {
        return informationMessenger.getAction(gameController);
    }

    @Override
    public String[] getPowerUps() {
        return informationMessenger.getPowerUpsList(gameController);
    }

    @Override
    public void useAction(final String name) {
        gameController.action(name);
    }

    @Override
    public void mouseAction(final int x, final int y, final int size) {
        final Coordinate newCoordinate = new CoordinateImpl((y % size < size / 2) ? y / size - 1 : y / size, x / size);
        gameController.action(newCoordinate);
    }

    @Override
    public Boolean isBlockPlacement() {
        return gameController.getTurnManager().getCurrentAction() == ActionType.BLOCK_PLACEMENT;
    }

    @Override
    public Boolean isWinnerPresent() {
        return informationMessenger.getWinner(gameController).isPresent();
    }

    @Override
    public String getWinner() {
        return informationMessenger.getWinner(gameController).get();
    }

    @Override
    public String getScores() {
        return informationMessenger.getNamesScores(gameController).replace("\n", "<br>");
    }
}
