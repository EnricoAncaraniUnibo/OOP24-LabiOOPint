package labioopint.model.powerup.impl;

import java.util.Random;

import labioopint.model.api.Coordinate;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.player.impl.PlayerImpl;

/**
 * This class represents a power-up that swaps the position of the current player with another random player.
 */
public final class SwapPositionPowerUp extends PowerUpImpl {
    public static final long serialVersionUID = 1L;
    private boolean condition = true;
    private final TurnManager turn;

    /**
     * Constructs a new SwapPositionPowerUp instance.
     *
     * @param tu the TurnManager instance to manage turns, must not be null
     * @param id the unique identifier for this power-up
     */
    public SwapPositionPowerUp(final TurnManager tu, final int id) {
        super(id);
        super.setName("Teleport");
        this.turn = tu;
    }

    /**
     * Activates the power-up for the specified player.
     * 
     */
    @Override
    public void activate() {
        if (turn.getCurrentPlayer().getUsablePowerUps().contains(this)) {
            if (isCollected()) {
                final Coordinate currentPlayerCoordinate = turn.getLab().getPlayerCoordinate(turn.getCurrentPlayer());
                final Random random = new Random();
                while (this.condition) {
                    final PlayerImpl playerSwap = turn.getPlayers().get(random.nextInt(turn.getPlayers().size()));
                    final Coordinate playerSwapCoordinate = turn.getLab().getPlayerCoordinate(playerSwap);
                    if (turn.getPlayers().size() < 2) {
                        this.condition = false;
                    } else if (turn.getPlayers().size() > 1 && !turn.getCurrentPlayer().equals(playerSwap)) {
                        turn.getLab().playerUpdateCoordinate(playerSwap, currentPlayerCoordinate);
                        turn.getLab().playerUpdateCoordinate(turn.getCurrentPlayer(), playerSwapCoordinate);
                        turn.getLab().playerUpdateCoordinate(playerSwap, currentPlayerCoordinate);
                        this.condition = false;
                    }
                }
            }
            turn.getCurrentPlayer().removePowerUp(this);
        }
    }
}
