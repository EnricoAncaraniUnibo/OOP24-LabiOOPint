package labioopint.model.powerup.impl;

import labioopint.model.core.impl.TurnManager;
import labioopint.model.player.impl.PlayerImpl;

/**
 * This class represents a power-up that allows a player to take an additional turn.
 */
public final class DoubleTurnPowerUp extends PowerUpImpl {

    private final TurnManager turn;

    /**
     * Constructs a new DoubleTurnPowerUp instance.
     *
     * @param tu the TurnManager instance to manage turns, must not be null
     * @param id the unique identifier for this power-up
     */
    public DoubleTurnPowerUp(final TurnManager tu, final int id) {
        super(id);
        super.setName("Double Turn");
        this.turn = tu;
    }

    /**
     * Activates the power-up for the specified player.
     *
     * @param currentPlayer the player for whom the power-up is activated, must not be null
     */
    @Override
    public void activate(final PlayerImpl currentPlayer) {
        if (currentPlayer.getUsablePowerUps().contains(this)) {
            if (isCollected()) {
                turn.repeatPlayerTurn();
            }
            currentPlayer.removePowerUp(this);
        }
    }

    /**
     * Returns the TurnManager associated with this power-up.
     *
     * @return the TurnManager instance
     */
    public TurnManager getTurnManager() {
        return this.turn;
    }
}
