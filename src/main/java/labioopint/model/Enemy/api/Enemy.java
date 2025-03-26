package labioopint.model.Enemy.api;

import java.util.*;

import labioopint.model.api.Coordinate;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.player.impl.Player;

public interface Enemy {

    Coordinate move(Labyrinth maze, List<Player> players);

    void playerHit(List<Player> players);
    // nemico dice che ho colpito quel player

}
