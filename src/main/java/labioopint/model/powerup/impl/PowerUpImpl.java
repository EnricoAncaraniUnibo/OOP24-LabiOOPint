package labioopint.model.powerup.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import labioopint.model.powerup.api.PowerUp;

/**
 * This abstract class provides a base implementation for power-ups in the game.
 * It includes common functionality such as tracking collection status and managing collected power-ups.
 */
public abstract class PowerUpImpl implements PowerUp, Serializable {
    public static final long serialVersionUID = 1L;
    private final int id;
    private boolean collected;
    private final List<PowerUp> collectedPowerUps;
    private String name;

    /**
     * Constructs a new PowerUpImpl instance with default values.
     *
     * @param id the unique identifier for the power-up
     */
    public PowerUpImpl(final int id) {
        this.id = id;
        this.collected = false;
        this.collectedPowerUps = new ArrayList<>();
    }

    /**
     * Returns the ID of the power-up.
     *
     * @return the unique ID of the power-up
     */
    @Override
    public int getID() {
        return id;
    }

    /**
     * Returns the name of the power-up.
     *
     * @return the name of the power-up
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Activates the power-up for the specified player.
     *
     * @param currentPlayer the player for whom the power-up is activated, must not be null
     */
    @Override
    public abstract void activate();

    /**
     * Checks if the power-up has been collected.
     *
     * @return true if the power-up has been collected, false otherwise
     */
    @Override
    public boolean isCollected() {
        return collected;
    }

    /**
     * Marks the power-up as collected and adds it to the list of collected power-ups.
     */
    @Override
    public void collect() {
        this.collected = true;
        this.collectedPowerUps.add(this);
    }

    /**
     * Returns a list of collected power-ups.
     *
     * @return a list of collected power-ups
     */
    @Override
    public List<PowerUp> getCollectedPowerUps() {
        return new ArrayList<>(collectedPowerUps);
    }

    /**
     * Sets the name of the power-up.
     *
     * @param string the name to set, must not be null
     */
    public void setName(final String string) {
        this.name = string;
    }
}
