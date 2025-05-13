package labioopint.model.player.api;

import java.io.Serializable;
import java.util.List;

import labioopint.model.powerup.api.PowerUp;
/**
 * Represents a player in the game.
 * This interface provides methods to manage the player's state, objectives, and power-ups.
 */
public interface Player extends Serializable {

    /**
     * Enables the player's invincibility status.
     */
    void enableInvincible();

    /**
     * Disables the player's invincibility status.
     */
    void disableInvincible();

    /**
     * Checks if the player is currently invincible.
     *
     * @return {@code true} if the player is invincible, {@code false} otherwise
     */
    boolean isInvincibilityStatus();

    /**
     * Adds a power-up to the player's list of objectives.
     *
     * @param pu the {@link PowerUp} to add as an objective
     */
    void addObjective(PowerUp pu);

    /**
     * Retrieves the list of power-ups that the player can use.
     *
     * @return a list of {@link PowerUp} instances that are usable
     */
    List<PowerUp> getUsablePowerUps();

    /**
     * Retrieves the list of the player's objectives.
     *
     * @return a list of {@link PowerUp} instances that are objectives
     */
    List<PowerUp> getObjetives();

    /**
     * Retrieves the unique identifier of the player.
     *
     * @return the player's ID as a {@link String}
     */
    String getID();

    /**
     * Removes a specific objective from the player's list of objectives.
     *
     * @param pou the {@link PowerUp} to remove
     * @return {@code true} if the objective was successfully removed, {@code false} otherwise
     */
    boolean removeObjectiveSelect(PowerUp pou);

    /**
     * Removes a power-up from the player's list of usable power-ups.
     *
     * @param pu the {@link PowerUp} to remove
     */
    void removePowerUp(PowerUp pu);

}
