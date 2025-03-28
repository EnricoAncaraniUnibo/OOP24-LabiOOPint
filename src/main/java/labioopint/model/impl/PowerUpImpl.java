package labioopint.model.impl;

import java.util.ArrayList;
import java.util.List;
import labioopint.model.player.impl;

import labioopint.model.api.PowerUp;

public abstract class PowerUpImpl implements PowerUp {
    private boolean collected;
    private List<PowerUp> collectedPowerUps;

    public PowerUpImpl() {
        this.collected = false;
        this.collectedPowerUps = new ArrayList<>();
    }

    @Override
    public abstract void activate(Player currentPlayer);

    @Override
    public boolean isCollected() {
        return collected;
    }

    @Override
    public void collect() {
        this.collected = true;
        this.collectedPowerUps.add(this);
    }

    @Override
    public List<PowerUp> getCollectedPowerUps() {
        return new ArrayList<>(collectedPowerUps);
    }
}