package labioopint.model.player.impl;

import java.util.ArrayList;
import java.util.List;

import labioopint.model.api.Movable;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;
/**
 * The PlayerImpl class implements the Player interface and represents a player
 * in the game. It manages the player's ID, objectives, usable power-ups, and
 * interactions with the TurnManager.
 */
public final class PlayerImpl extends Movable implements Player {
    private final String id;
    private final List<PowerUp> objectives;
    private final List<PowerUp> usablePowerUps;
    private final TurnManager turn;
    /**
     * Constructs a PlayerImpl with the specified ID and TurnManager.
     *
     * @param id the unique identifier for the player
     * @param tu the TurnManager instance to manage game turns
     */
    public PlayerImpl(final String id, final TurnManager tu) {
        this.id = id;
        objectives = new ArrayList<>();
        usablePowerUps = new ArrayList<>();
        turn = tu;
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
    public void removeObjective() {
        if (!objectives.isEmpty()) {
            final PowerUp p = objectives.get(0);
            if (usablePowerUps.contains(p)) {
                usablePowerUps.remove(p);
            }
            objectives.remove(p);
            turn.getLab().addPowerUp(p);
        }
    }

    @Override
    public void removePowerUp(final PowerUp pu) {
        usablePowerUps.remove(pu);
    }
}
