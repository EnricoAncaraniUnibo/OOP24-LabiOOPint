package labioopint.model.Player.api;

import java.util.List;

import labioopint.model.PowerUp.api.PowerUp;

public interface Player {

    void addObjective(PowerUp pu);

    List<PowerUp> getUsablePowerUps();

    List<PowerUp> getObjetives();

    String getID();

    void removeObjective();

    void removePowerUp(PowerUp pu);

}