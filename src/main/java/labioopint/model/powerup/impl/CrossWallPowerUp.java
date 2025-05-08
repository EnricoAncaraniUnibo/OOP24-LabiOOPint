package labioopint.model.powerup.impl;

import labioopint.model.player.impl.PlayerImpl;

/**
 * This class represents a power-up that allows a player to cross walls.
 */
public final class CrossWallPowerUp extends PowerUpImpl {

    /**
     * Constructs a new CrossWallPowerUp instance.
     * 
     * @param id the unique identifier for this power-up
     */
    public CrossWallPowerUp(final int id) {
        super(id);
        super.setName("Cross Wall");
    }

    /**
     * Activates the power-up for the specified player.
     * 
     * @param currentPlayer the player for whom the power-up is activated, must not be null
     */
    @Override
    public void activate(final PlayerImpl currentPlayer) {
        if (isCollected()) {
            // Specific logic for crossing a wall
        }
    }
}
