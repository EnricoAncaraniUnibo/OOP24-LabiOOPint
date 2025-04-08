package labioopint.model.PowerUp.impl;

import java.util.ArrayList;
import java.util.List;

import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;

public abstract class PowerUpImpl implements PowerUp {
    private boolean collected;
    private List<PowerUp> collectedPowerUps;
    private String name;

    public PowerUpImpl() {
        this.collected = false;
        this.collectedPowerUps = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public abstract void activate(PlayerImpl currentPlayer);

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

    public void setName(String string) {
        this.name = string;
    }
}