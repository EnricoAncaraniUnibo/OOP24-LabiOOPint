package labioopint.controller.api;

import labioopint.model.api.Coordinate;
import labioopint.model.maze.api.Direction;
import labioopint.model.player.impl.PlayerImpl;

public interface ActionPredicate {

    boolean playerCanMove(PlayerImpl p, Direction dir);

    boolean blockCanMove(Coordinate blockCoordinate);

    boolean canMoveFromPosition(Coordinate coor, Direction dir);

    boolean enemyCanMove(Direction dir);

}