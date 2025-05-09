package labioopint.model.powerup.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;

/**
 * This class represents a power-up that allows a player to steal an objective
 * (Usable power-up) from another player.
 */
public class StoleObjectPowerUp extends PowerUpImpl {

    private final TurnManager turn;

    /**
     * Constructs a new StoleObjectPowerUp instance.
     *
     * @param turn the TurnManager instance to manage turns, must not be null
     * @param id   the unique identifier for this power-up
     */
    public StoleObjectPowerUp(final TurnManager turn, final int id) {
        super(id);
        super.setName("Stole Object");
        this.turn = turn;
    }

    /**
     * Activates the power-up for the specified player.
     *
     * @param currentPlayer the player for whom the power-up is activated, must not be null
     */
    @Override
    public void activate(final PlayerImpl currentPlayer) {
        if (isCollected()) {
            final List<PlayerImpl> players = turn.getPlayers();
            final List<PlayerImpl> targetPlayers = players.stream()
                        .filter(player -> !player.equals(currentPlayer) && !player.getUsablePowerUps().isEmpty())
                        .toList();
            if (!targetPlayers.isEmpty()) {
                PlayerImpl targetPlayer = targetPlayers.get(new Random().nextInt(targetPlayers.size()));
                Optional<PowerUp> stolenObjective = Optional.of(targetPlayer.getUsablePowerUps().getFirst());
                if (stolenObjective.isPresent()) {
                    targetPlayer.removePowerUp(stolenObjective.get());
                    currentPlayer.addObjective(stolenObjective.get());
                }
            }
            currentPlayer.removePowerUp(this);
        }
    }
}
