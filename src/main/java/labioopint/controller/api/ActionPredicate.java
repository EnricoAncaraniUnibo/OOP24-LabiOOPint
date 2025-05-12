package labioopint.controller.api;

import labioopint.model.api.Coordinate;
import labioopint.model.maze.api.Direction;
import labioopint.model.player.impl.PlayerImpl;

public interface ActionPredicate {

    /**
     * Check if the player can move
     * 
     * @param player        it is the player that want to move
     * @param dir           the direction for the movement 
     * 
     * @return true if the movement is possible, false otherwise
     */
    boolean playerCanMove(PlayerImpl player, Direction direction);

    /**
     * Check if the block selected by the player is movable
     * In particular this contol check if the target block is in the 
     * border of the labyrinth and check if the block is movable
     * 
     * @param blockCoordinate       the coordinate of the block to move
     * 
     * @return true if the movement is possible, false otherwise
     */
    boolean blockCanMove(Coordinate blockCoordinate);

    /**
     * Check if there is an entrance from a specific place in the
     * labyrinth in a direction
     * 
     * @param coor          the coordinate of the origin place
     * @param dir           the direction to check
     * 
     * @return true if there is an entrance, false otherwise
     */
    boolean enemyCanMoveFromPosition(Coordinate coordinate, Direction direction);

    /**
     * Check if the enemy can move in a specific direction
     * 
     * @param dir           the direction where the enemy want to move
     * @return true if it can move in the direction, false otherwise
     */
    boolean enemyCanMove(Direction direction);

}
