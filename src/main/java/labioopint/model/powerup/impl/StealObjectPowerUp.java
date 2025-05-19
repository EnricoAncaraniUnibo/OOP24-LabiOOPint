package labioopint.model.powerup.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import labioopint.model.core.api.TurnManager;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;

/**
 * This class represents a power-up that allows a player to steal an objective
 * (Usable power-up) from another player.
 */
public final class StealObjectPowerUp extends PowerUpImpl {
    public static final long serialVersionUID = 1L;
    private static final Random RANDOM = new Random();
    /**
     * Construction of the StealObjectPowerUp by giving it an id.
     * @param id the identifier.
     */
    public StealObjectPowerUp(final int id) {
        super(id);
        super.setName("Steal Object");
    }

    @Override
    public void activate(final Labyrinth lab, final TurnManager turn) {
        final Player p = lab.getPlayers().get(turn.getCurrentPlayer());
        if (isCollected()) {
            final List<Player> players = lab.getPlayers();
            final List<Player> targetPlayers = players.stream()
                        .filter(player -> !player.equals(p) && !player.getObjetives().isEmpty())
                        .toList();
            if (!targetPlayers.isEmpty()) {
                final Player targetPlayer = targetPlayers.get(RANDOM.nextInt(targetPlayers.size()));
                final Optional<PowerUp> stolenObjective = Optional.of(targetPlayer.getObjetives().get(0));
                if (targetPlayer.isInvincibilityStatus()) {
                    targetPlayer.disableInvincible();
                } else if (stolenObjective.isPresent()) {
                    targetPlayer.removeObjectiveSelect(stolenObjective.get());
                    p.addObjective(stolenObjective.get());
                }
            }
           p.removePowerUp(this);
        }
    }
}
