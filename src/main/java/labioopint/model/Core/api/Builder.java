package labioopint.model.Core.api;

import java.util.List;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Maze.impl.LabyrinthImpl;
import labioopint.model.Player.impl.PlayerImpl;

public interface Builder {
    
    LabyrinthImpl createMaze();

    List<PlayerImpl> createPlayers();

    Enemy createEnemy();
    
    int getDimension(int dim);
}
