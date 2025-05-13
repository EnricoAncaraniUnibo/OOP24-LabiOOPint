package labioopint.model.player.api;

import java.io.Serializable;
import java.util.List;

import labioopint.model.powerup.api.PowerUp;

public interface Player extends Serializable {

    void enableInvincible();

    void disableInvincible();

    boolean isInvincibilityStatus();

    void addObjective(PowerUp pu);

    List<PowerUp> getUsablePowerUps();

    List<PowerUp> getObjetives();

    String getID();

    boolean removeObjectiveSelect(PowerUp pou);

    void removePowerUp(PowerUp pu);

}
