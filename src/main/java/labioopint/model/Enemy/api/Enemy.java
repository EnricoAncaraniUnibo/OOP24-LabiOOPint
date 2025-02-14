package labioopint.model.Enemy.api;

import java.util.*;
// import Labyrinth e Player (come interfacce)

import labioopint.model.Labyrinth.api.Labyrinth;
import labioopint.model.Player.api.Player;

public interface Enemy {

    void move(Labyrinth maze, List<Player> players);

    Optional<Player> playerHit(List<Player> players);
    // nemico dice che ho colpito quel player

}
