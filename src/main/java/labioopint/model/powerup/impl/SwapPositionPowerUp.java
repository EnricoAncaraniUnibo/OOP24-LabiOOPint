package labioopint.model.powerup.impl;

import java.util.Random;

import labioopint.model.api.Coordinate;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.player.impl.PlayerImpl;

/**
 * This class represents a power-up that swaps the position of the current player with another random player.
 */
public final class SwapPositionPowerUp extends PowerUpImpl {

    private boolean condition = true;
    private final TurnManager turn;

    /**
     * Constructs a new SwapPositionPowerUp instance.
     *
     * @param tu the TurnManager instance to manage turns, must not be null
     * @param id the unique identifier for this power-up
     */
    public SwapPositionPowerUp(final TurnManager tu, final int id) {
        super(id);
        super.setName("Teleport");
        this.turn = tu;
    }

    /**
     * Activates the power-up for the specified player.
     *
     * @param currentPlayer the player for whom the power-up is activated, must not be null
     */
    @Override
    public void activate(final PlayerImpl currentPlayer) {
        if (currentPlayer.getUsablePowerUps().contains(this)) {
            if (isCollected()) {
                Coordinate currentPlayerCoordinate = turn.getLab().getPlayerCoordinate(currentPlayer);
                Random random = new Random();
                while (this.condition) {
                    PlayerImpl playerSwap = turn.getPlayers().get(random.nextInt(turn.getPlayers().size()));
                    Coordinate playerSwapCoordinate = turn.getLab().getPlayerCoordinate(playerSwap);
                    if (turn.getPlayers().size() < 2) {
                        this.condition = false;
                    } else if (turn.getPlayers().size() > 1 && !currentPlayer.equals(playerSwap)) {
                        turn.getLab().playerUpdateCoordinate(playerSwap, currentPlayerCoordinate);
                        turn.getLab().playerUpdateCoordinate(currentPlayer, playerSwapCoordinate);
                        turn.getLab().playerUpdateCoordinate(playerSwap, currentPlayerCoordinate);
                        this.condition = false;
                    }
                }
            }
            currentPlayer.removePowerUp(this);
        }
    }
}
