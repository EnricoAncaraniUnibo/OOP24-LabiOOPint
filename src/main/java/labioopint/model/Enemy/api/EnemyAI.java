package labioopint.model.Enemy.api;

import java.util.*;

import labioopint.model.api.Coordinate;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.player.impl.Player;

public interface EnemyAI {

    Coordinate getNextPosition(Labyrinth maze, List<Player> players, Coordinate current);
}
