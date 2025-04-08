package labioopint.model.Player.impl;

import java.util.ArrayList;
import java.util.List;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Player.api.Player;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.Movable;

public class PlayerImpl extends Movable implements Player {
    private final String id;
    private final List<PowerUp> objectives;
    private final List<PowerUp> usablePowerUps;

    public PlayerImpl(final String id) {
        this.id = id;
        objectives = new ArrayList<>();
        usablePowerUps = new ArrayList<>();
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
        return usablePowerUps;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
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

    @Override
    public void removePowerUp(PowerUp pu) {
        usablePowerUps.remove(pu);
    }
}
