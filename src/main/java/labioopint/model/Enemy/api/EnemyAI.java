package labioopint.model.enemy.api;

import java.util.List;

import labioopint.model.api.Coordinate;
import labioopint.model.player.impl.PlayerImpl;

/**
 * The {@code EnemyAI} interface defines the behavior of an enemy's artificial
 * intelligence,
 * including determining the next position of the enemy based on the game state.
 */
public interface EnemyAI {

    /**
     * Determines the next position for the enemy based on the current game state,
     * including the positions of players and the enemy's current position.
     *
     * @param players the list of players in the game
     * @param current the current position of the enemy
     * @return a list of {@link Coordinate} representing the enemy's next
     *         position(s)
     */
    List<Coordinate> getNextPosition(List<PlayerImpl> players, Coordinate current);
}
