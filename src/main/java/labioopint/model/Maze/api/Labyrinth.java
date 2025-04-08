package labioopint.model.Maze.api;

import java.util.List;

import labioopint.model.Block.api.Rotation;
import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Maze.impl.MazeImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.Coordinate;

public interface Labyrinth {

    void Default();

    BlockImpl getOutsideBlock();

    boolean moveBlock(Coordinate c, Direction d);

    Coordinate getPlayerCoordinate(PlayerImpl p);

    Coordinate getPowerUp(PowerUp p);

    List<PowerUp> getListOfPowerUps();

    Coordinate getEnemyCoordinate(Enemy e);

    void movePlayer(PlayerImpl p, Direction dir);

    void absoluteUpdateCoordinate(Object o, Coordinate coor);

    MazeImpl getGrid();

    void RotateOutsideBlock(Rotation blockRotation);

    void addPowerUp(PowerUp p);

}