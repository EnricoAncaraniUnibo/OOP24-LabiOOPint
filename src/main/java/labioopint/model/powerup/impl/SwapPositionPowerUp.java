package labioopint.model.powerup.impl;

import java.util.Random;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.core.api.TurnManager;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;

/**
 * This class represents a power-up that swaps the position of the current
 * player with another random player.
 */
public final class SwapPositionPowerUp extends PowerUpImpl {
    public static final long serialVersionUID = 1L;
    private static final Random RANDOM = new Random();

    /**
     * Construction of the SwapPositionPowerUp by giving it an id.
     * 
     * @param id the identifier.
     */
    public SwapPositionPowerUp(final int id) {
        super(id);
        super.setName("Teleport");
    }

    @Override
    public void activate(final Labyrinth lab, final TurnManager turn) {
        final Player p = lab.getPlayers().get(turn.getCurrentPlayer());
        if (p.getUsablePowerUps().contains(this)) {
            if (isCollected()) {
                final Coordinate currentPlayerCoordinate = lab.getPlayerCoordinate(p);
                boolean condition = true;
                while (condition) {
                    final Player playerSwap = lab.getPlayers().get(RANDOM.nextInt(lab.getPlayers().size()));
                    final Coordinate playerSwapCoordinate = lab.getPlayerCoordinate(playerSwap);
                    if (!p.equals(playerSwap)) {
                        if (playerSwap.isInvincibilityStatus()) {
                            playerSwap.disableInvincible();
                            condition = false;
                        } else {
                            lab.playerUpdateCoordinate(playerSwap, currentPlayerCoordinate);
                            lab.playerUpdateCoordinate(p, playerSwapCoordinate);
                            lab.playerUpdateCoordinate(playerSwap, currentPlayerCoordinate);
                            condition = false;
                        }
                    }
                }
            }
            p.removePowerUp(this);
        }
    }
}
