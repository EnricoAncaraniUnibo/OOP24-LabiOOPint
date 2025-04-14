package labioopint.model.Player.api;

import java.util.List;

import labioopint.model.PowerUp.api.PowerUp;
/**
 * The Player interface defines the behavior and attributes of a player
 * within the game. It provides methods for managing objectives, power-ups,
 * and player identification.
 */
public interface Player {
/**
    * Adds a power-up to the player's list of objectives.
    *
    * @param pu the power-up to add as an objective
    */
    void addObjective(PowerUp pu);
    /**
     * Retrieves the list of power-ups that the player can use.
     *
     * @return a list of usable power-ups
     */
    List<PowerUp> getUsablePowerUps();
    /**
     * Retrieves the list of the player's objectives.
     *
     * @return a list of objectives
     */
    List<PowerUp> getObjetives();
    /**
     * Retrieves the unique identifier of the player.
     *
     * @return the player's ID as a String
     */
    String getID();
    /**
     * Removes the player's first taken objective.
     */
    void removeObjective();
    /**
     * Removes a specific power-up from the player.
     *
     * @param pu the power-up to remove
     */
    void removePowerUp(PowerUp pu);
}
