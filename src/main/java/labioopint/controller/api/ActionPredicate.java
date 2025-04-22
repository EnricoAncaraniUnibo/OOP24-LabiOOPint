package labioopint.controller.api;

import labioopint.model.api.Coordinate;
import labioopint.model.maze.api.Direction;
import labioopint.model.player.impl.PlayerImpl;

public interface ActionPredicate {

    boolean PlayerCanMove(PlayerImpl p, Direction dir);

    boolean BlockCanMove(Coordinate blockCoordinate);

    boolean CanMoveFromPosition(Coordinate coor, Direction dir);

    boolean EnemyCanMove(Direction dir);

}