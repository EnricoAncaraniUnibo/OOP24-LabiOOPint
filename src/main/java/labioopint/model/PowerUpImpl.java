package labioopint.model;

import java.util.ArrayList;
import java.util.List;

public class PowerUpImpl implements PowerUp {
    private boolean collected;
    private List<PowerUp> collectedPowerUps;

    public PowerUpImpl() {
        this.collected = false;
        this.collectedPowerUps = new ArrayList<>();
    }

    @Override
    public void activate() {
        if (collected) {
            // Logica per attivare il power-up
        }
    }

    @Override
    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        this.collected = true;
        this.collectedPowerUps.add(this);
    }

    @Override
    public List<PowerUp> getCollectedPowerUps() {
        return new ArrayList<>(collectedPowerUps);
    }
}