package labioopint.model.powerup.impl;

import labioopint.model.core.api.TurnManager;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;

/**
 * This class represents a power-up that allows a player to take an additional turn.
 */
public final class DoubleTurnPowerUp extends PowerUpImpl {
    public static final long serialVersionUID = 1L;

    public DoubleTurnPowerUp(final int powerUpId) {
        super(powerUpId);
        super.setName("Double Turn");
    }

    @Override
    public void activate(final Labyrinth labyrinth, final TurnManager turnManager) {
        Player player = labyrinth.getPlayers().get(turnManager.getCurrentPlayer());
        if (player.getUsablePowerUps().contains(this)) {
            if (isCollected()) {
                turnManager.repeatPlayerTurn();
            }
            player.removePowerUp(this);
        }
    }
}
