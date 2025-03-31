package labioopint.model.Enemy.api;

import java.util.*;

import labioopint.model.api.Coordinate;
import labioopint.model.player.impl.Player;

public interface EnemyAI {

    Coordinate getNextPosition(List<Player> players, Coordinate current);
}
