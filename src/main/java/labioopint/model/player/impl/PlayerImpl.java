package labioopint.model.player.impl;

import java.util.ArrayList;
import java.util.List;

import labioopint.model.utilities.impl.MovableImpl;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;

public final class PlayerImpl extends MovableImpl implements Player {
    public static final long serialVersionUID = 1L;
    private final String id;
    private final List<PowerUp> objectives;
    private final List<PowerUp> usablePowerUps;
    private boolean invicible;

    public PlayerImpl(final String id) {
        this.id = id;
        objectives = new ArrayList<>();
        usablePowerUps = new ArrayList<>();
        invicible = false;
    }

    @Override
    public void enableInvincible() {
        invicible = true;
    }

    @Override
    public void disableInvincible() {
        invicible = false;
    }

    @Override
    public boolean isInvincibilityStatus() {
        return invicible;
    }

    @Override
    public void addObjective(final PowerUp pu) {
        objectives.add(pu);
        usablePowerUps.add(pu);
    }

    @Override
    public List<PowerUp> getUsablePowerUps() {
        return usablePowerUps;
    }

    @Override
    public List<PowerUp> getObjetives() {
        return objectives;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public boolean removeObjectiveSelect(final PowerUp pou) {
        if (objectives.contains(pou)) {
            if (usablePowerUps.contains(pou)) {
                usablePowerUps.remove(pou);
            }
            objectives.remove(pou);
            return true;
        }
        return false;
    }

    @Override
    public void removePowerUp(final PowerUp pu) {
        usablePowerUps.remove(pu);
    } 
}
