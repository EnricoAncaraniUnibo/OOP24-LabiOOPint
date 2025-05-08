package labioopint.model.powerup.api;

import java.util.List;

import labioopint.model.player.impl.PlayerImpl;

/**
 * This interface defines the behavior of a power-up in the game.
 */
public interface PowerUp {

    /**
     * Activates the power-up for the specified player.
     * 
     * @param currentPlayer the player for whom the power-up is activated, must not be null
     */
    void activate(PlayerImpl currentPlayer);

    /**
     * Checks if the power-up has been collected.
     * 
     * @return true if the power-up has been collected, false otherwise
     */
    boolean isCollected();

    /**
     * Collects the power-up.
     */
    void collect();

    /**
     * Returns the list of collected power-ups.
     * 
     * @return the list of collected power-ups
     */
    List<PowerUp> getCollectedPowerUps();

    /**
     * Returns the name of the power-up.
     * 
     * @return the name of the power-up
     */
    String getName();
}
