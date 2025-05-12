package labioopint.model.enemy.api;

import java.util.List;

import labioopint.model.api.Coordinate;
import labioopint.model.player.impl.PlayerImpl;

/**
 * The {@code Enemy} interface defines the behavior of an enemy in the game,
 * including movement, interactions with players, and access to its AI.
 */
public interface Enemy {

    /**
     * Moves the enemy based on its AI logic and the current game state.
     *
     * @param players the list of players in the game
     * @return a list of {@link Coordinate} representing the enemy's movement path
     */
    List<Coordinate> move(List<PlayerImpl> players);

    /**
     * Handles the interaction when the enemy hits a player. This method defines
     * the behavior when a player is at the same position as the enemy.
     *
     * @param players the list of players in the game
     */
    void playerHit(List<PlayerImpl> players);

    /**
     * Retrieves the {@link EnemyAI} controlling this enemy.
     *
     * @return the {@link EnemyAI} instance
     */
    EnemyAI getEnemyAI();

    /**
     * Retrieves the last player hit by the enemy.
     *
     * @return the {@link PlayerImpl} instance representing the last player hit,
     *         or {@code null} if no player has been hit
     */
    PlayerImpl getLastHit();

    /**
     * Clears the record of the last player hit by the enemy.
     */
    void clearLastHit();

    /**
     * Checks if there is a record of the last player hit by the enemy.
     *
     * @return {@code true} if a player was hit, {@code false} otherwise
     */
    boolean isPresentLastHit();
}
