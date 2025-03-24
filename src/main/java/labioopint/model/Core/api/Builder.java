package labioopint.model.Core.api;

import java.util.List;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.player.impl.Player;

public interface Builder {
    
    Labyrinth createMaze();

    List<Player> createPlayers();

    Enemy createEnemy();
    
    int getDimension(int dim);
}
