package labioopint.model.enemy.api;

import java.io.Serializable;
import java.util.List;

import labioopint.controller.api.ActionPredicate;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.model.utilities.api.Coordinate;
/**
 * Represents the AI logic for an enemy in the game.
 * This interface provides methods to determine the next position of the enemy
 * based on the current game state, including player positions, the labyrinth layout,
 * and valid actions.
 */
public interface EnemyAI extends Serializable {
    /**
     * Determines the next position(s) for the enemy based on the provided game state.
     *
     * @param players the list of {@link Player} instances in the game
     * @param current the current {@link Coordinate} of the enemy
     * @param actionPredicate the {@link ActionPredicate} to determine valid actions
     * @param labyrinth the {@link Labyrinth} representing the game maze
     * @return a list of {@link Coordinate} instances representing the enemy's next position(s)
     */
    List<Coordinate> getNextPosition(List<Player> players, Coordinate current, ActionPredicate actionPredicate,
            Labyrinth labyrinth);

}
