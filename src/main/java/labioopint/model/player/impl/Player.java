package labioopint.model.player.impl;

import java.util.ArrayList;
import java.util.List;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.Movable;

public class Player extends Movable {
    private final String id;
    private final List<PowerUp> objectives;
    private final List<PowerUp> usablePowerUps;

    public Player(final String id) {
        this.id = id;
        objectives = new ArrayList<>();
        usablePowerUps = new ArrayList<>();
    }

    public void addObjective(final PowerUp pu) {
        objectives.add(pu);
        usablePowerUps.add(pu);
    }

    public List<PowerUp> getUsablePowerUps() {
        return usablePowerUps;
    }

    public List<PowerUp> getObjetives() {
        return usablePowerUps;
    }

    public String getID() {
        return id;
    }

    public void removeObjective() {
        if(objectives.size()!=0) {
            PowerUp p = objectives.get(0);
            if(usablePowerUps.contains(p)) {
                usablePowerUps.remove(p);
            }
            objectives.remove(p);
            TurnManager.GetLab().addPowerUp(p);
        }
    }

    public void removePowerUp(PowerUp pu) {
        usablePowerUps.remove(pu);
    }
}
