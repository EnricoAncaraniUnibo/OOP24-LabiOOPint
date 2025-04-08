package labioopint.model.Enemy.api;

import java.util.*;

import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.api.Coordinate;

public interface EnemyAI {

    Coordinate getNextPosition(List<PlayerImpl> players, Coordinate current);
}
