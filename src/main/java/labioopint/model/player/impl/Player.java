package labioopint.model.player.impl;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.Movable;

public class Player extends Movable {
    private final Integer id;
    private final List<PowerUp> objectives;
    private final List<PowerUp> usablePowerUps;
    private final Image image;
    private final Image imageTurn;

    public Player(final Integer id, final List<Image> im) {
        this.id = id;
        objectives = new ArrayList<>();
        usablePowerUps = new ArrayList<>();
        this.image = im.get(0);
        this.imageTurn = im.get(1);
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

    public Image getImage() {
        if(TurnManager.GetCurrentPlayer()!=this) {
            return image;
        } else {
            return imageTurn;
        }
    }

    public Integer getID() {
        return id;
    }

    public void removeFirst() {
        objectives.remove(0);
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
}
