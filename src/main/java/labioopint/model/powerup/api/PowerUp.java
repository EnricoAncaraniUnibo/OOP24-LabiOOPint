package labioopint.model.powerup.api;

import java.io.Serializable;
import java.util.List;

import labioopint.model.core.api.TurnManager;
import labioopint.model.maze.api.Labyrinth;
/**
 * The interface that define the PowerUp and his methods
 * to be used and collected.
 */
public interface PowerUp extends Serializable {

    /**
     * Returns the ID of the power-up.
     *
     * @return the unique ID of the power-up
     */
    int getID();

    /**
     * Returns the name of the power-up.
     *
     * @return the name of the power-up
     */
    String getName();
    /**
     * Activate the powerUp.
     * 
     * @param lab the labyrinth rappresenting the game state of other objects.
     * @param turn the turnManager rappresenting the turn state of the game.
     */
    void activate(Labyrinth lab, TurnManager turn);

    /**
     * Checks if the power-up has been collected.
     *
     * @return true if the power-up has been collected, false otherwise
     */
    boolean isCollected();

    /**
     * Marks the power-up as collected and adds it to the list of collected power-ups.
     */
    void collect();

    /**
     * Returns a list of collected power-ups.
     *
     * @return a list of collected power-ups
     */
    List<PowerUp> getCollectedPowerUps();

    /**
     * Sets the name of the power-up.
     *
     * @param string the name to set, must not be null
     */
    void setName(String string);

}
