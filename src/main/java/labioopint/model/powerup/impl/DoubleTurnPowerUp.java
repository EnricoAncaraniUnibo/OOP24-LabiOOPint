package labioopint.model.powerup.impl;

import labioopint.model.core.impl.TurnManager;

/**
 * This class represents a power-up that allows a player to take an additional turn.
 */
public final class DoubleTurnPowerUp extends PowerUpImpl {
    public static final long serialVersionUID = 1L;
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
     */
    @Override
    public void activate() {
        if (turn.getCurrentPlayer().getUsablePowerUps().contains(this)) {
            if (isCollected()) {
                turn.repeatPlayerTurn();
            }
            turn.getCurrentPlayer().removePowerUp(this);
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
