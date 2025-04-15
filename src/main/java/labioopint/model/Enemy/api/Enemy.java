package labioopint.model.Enemy.api;

import java.util.*;

import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.api.Coordinate;

public interface Enemy {

    List<Coordinate> move(List<PlayerImpl> players);

    void playerHit(List<PlayerImpl> players);
    // nemico dice che ho colpito quel player
    EnemyAI getEnemyAI();
}
