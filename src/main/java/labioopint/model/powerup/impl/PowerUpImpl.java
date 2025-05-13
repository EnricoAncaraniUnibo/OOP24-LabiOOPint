package labioopint.model.powerup.impl;

import java.util.ArrayList;
import java.util.List;

import labioopint.model.core.api.TurnManager;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.powerup.api.PowerUp;

/**
 * This abstract class provides a base implementation for power-ups in the game.
 * It includes common functionality such as tracking collection status and managing collected power-ups.
 */
public abstract class PowerUpImpl implements PowerUp {
    public static final long serialVersionUID = 1L;
    private final int id;
    private boolean collected;
    private final List<PowerUp> collectedPowerUps;
    private String name;

    public PowerUpImpl(final int id) {
        this.id = id;
        this.collected = false;
        this.collectedPowerUps = new ArrayList<>();
    }

    @Override
    public final int getID() {
        return id;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public abstract void activate(Labyrinth lab, TurnManager turn);

    @Override
    public final boolean isCollected() {
        return collected;
    }

    @Override
    public final void collect() {
        this.collected = true;
        this.collectedPowerUps.add(this);
    }

    @Override
    public final List<PowerUp> getCollectedPowerUps() {
        return new ArrayList<>(collectedPowerUps);
    }

    @Override
    public final void setName(final String string) {
        this.name = string;
    }
}
