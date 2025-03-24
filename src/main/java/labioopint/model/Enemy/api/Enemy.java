package labioopint.model.Enemy.api;

import java.util.*;
// import Labyrinth e Player (come interfacce)

import labioopint.model.Core.api.GameObject;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.player.impl.Player;

public interface Enemy extends GameObject {

    void move(Labyrinth maze, List<Player> players);

    Optional<Player> playerHit(List<Player> players);
    // nemico dice che ho colpito quel player

}
