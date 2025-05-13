package labioopint.model.powerup.impl;

import labioopint.model.core.api.TurnManager;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;

/**
 * This class represents a power-up that allows a player to take an additional turn.
 */
public final class DoubleTurnPowerUp extends PowerUpImpl {
    public static final long serialVersionUID = 1L;

    public DoubleTurnPowerUp(final int id) {
        super(id);
        super.setName("Double Turn");
    }

    @Override
    public void activate(final Labyrinth lab, final TurnManager turn) {
        Player p = lab.getPlayers().get(turn.getCurrentPlayer());
        if (p.getUsablePowerUps().contains(this)) {
            if (isCollected()) {
                turn.repeatPlayerTurn();
            }
            p.removePowerUp(this);
        }
    }
}
