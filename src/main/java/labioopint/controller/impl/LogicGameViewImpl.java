package labioopint.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import labioopint.controller.api.GameController;
import labioopint.controller.api.LogicGameView;
import labioopint.model.utilities.impl.ActionType;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;
import labioopint.model.utilities.api.Coordinate;

/**
 * Implementation of the Logic of the GameView.
 */
public final class LogicGameViewImpl implements LogicGameView {
    public static final long serialVersionUID = 1L;
    private final GameController gameController;

    /**
     * Construction of a {@code LogicGameViewImpl} with an GameController.
     * 
     * @param gameController the game we want to see on the screen
     */
    public LogicGameViewImpl(final GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public String getTurn() {
        return "Player: " + gameController.getCurrentPlayer().getID();
    }

    @Override
    public String getAction() {
        if (gameController.getTurnManager().getCurrentAction() == ActionType.BLOCK_PLACEMENT) {
            return "Place the block";
        }
        if (gameController.getTurnManager().getCurrentAction() == ActionType.PLAYER_MOVEMENT) {
            return "Move the player";
        }
        return "";
    }

    @Override
    public String[] getPowerUps() {
        final List<PowerUp> powerUpsList = new ArrayList<>();
        powerUpsList.addAll(gameController.getCurrentPlayer().getUsablePowerUps());
        final String[] names = new String[powerUpsList.size()];
        int i = 0;
        for (final PowerUp powerUp : powerUpsList) {
            names[i] = powerUp.getName();
            i++;
        }
        return names;
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
    public boolean isBlockPlacement() {
        return gameController.getTurnManager().getCurrentAction() == ActionType.BLOCK_PLACEMENT;
    }

    @Override
    public boolean isWinnerPresent() {
        return gameController.getLabyrinth().checkWinner().isPresent();
    }

    @Override
    public String getWinner() {
        return Optional.of("The winner is : " + gameController.getLabyrinth().checkWinner().get().getID()).get();
    }

    @Override
    public String getScores() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Player player : gameController.getLabyrinth().getPlayers()) {
            if (player.isInvincibilityStatus()) {
                stringBuilder.append("invincible ").append(player.getID()).append(": ")
                        .append(player.getObjetives().size()).append('\n');
            } else {
                stringBuilder.append(player.getID()).append(": ").append(player.getObjetives().size()).append('\n');
            }
        }
        String s = stringBuilder.toString();
        s = s.replace("\n", "<br>");
        return s;
    }
}
