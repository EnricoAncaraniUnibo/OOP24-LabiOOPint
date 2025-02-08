package labioopint.model.Enemy.api;

import java.util.*;
// import Labyrinth e Player (come interfacce)

public interface Enemy {
    
    void move(Labyrinth maze, List<Player> players);

    Optional<Player> playerHit(List<Player> players);
    // nemico dice che ho colpito quel player

}
