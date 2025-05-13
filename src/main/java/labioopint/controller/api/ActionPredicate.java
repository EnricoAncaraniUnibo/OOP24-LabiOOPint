package labioopint.controller.api;

import java.io.Serializable;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.api.Direction;
import labioopint.model.player.api.Player;
/**
 * Represents a set of predicates to determine the validity of various actions
 * within the game, such as player movement, block movement, and enemy movement.
 */
public interface ActionPredicate extends Serializable {
    /**
     * Determines if the player can move in the specified direction.
     *
     * @param player the player attempting to move
     * @param dir the direction in which the player wants to move
     * @return {@code true} if the player can move in the specified direction, {@code false} otherwise
     */
    boolean playerCanMove(Player player, Direction dir);

    /**
     * Determines if a block located at the specified coordinate can move.
     *
     * @param blockCoordinate the coordinate of the block
     * @return {@code true} if the block can move, {@code false} otherwise
     */
    boolean blockCanMove(Coordinate blockCoordinate);

    /**
     * Determines if an enemy can move from the specified position in the given direction.
     *
     * @param coor the current coordinate of the enemy
     * @param dir the direction in which the enemy wants to move
     * @return {@code true} if the enemy can move from the position in the specified direction, {@code false} otherwise
     */
    boolean enemyCanMoveFromPosition(Coordinate coor, Direction dir);

    /**
     * Determines if the specified enemy can move in the given direction.
     *
     * @param dir the direction in which the enemy wants to move
     * @param enemy the enemy attempting to move
     * @return {@code true} if the enemy can move in the specified direction, {@code false} otherwise
     */
    boolean enemyCanMove(Direction dir, Enemy enemy);
}
