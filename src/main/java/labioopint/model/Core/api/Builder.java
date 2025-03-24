package labioopint.model.Core.api;

import java.util.List;

import labioopint.model.Enemy.api.Enemy;

public interface Builder {
    
    Labyrinth createMaze();

    List<Player> createPlayers();

    Enemy createEnemy();
    
    int getDimension(int dim);
}
