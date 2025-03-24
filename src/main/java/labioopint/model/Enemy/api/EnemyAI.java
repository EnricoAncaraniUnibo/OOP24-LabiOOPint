package labioopint.model.Enemy.api;

import java.util.*;

import labioopint.commons.Coordinate;
import labioopint.model.Labyrinth.api.Labyrinth;
import labioopint.model.Player.api.Player;

public interface EnemyAI {

    Coordinate getNextPosition(Labyrinth maze, List<Player> players, Coordinate current);
}
