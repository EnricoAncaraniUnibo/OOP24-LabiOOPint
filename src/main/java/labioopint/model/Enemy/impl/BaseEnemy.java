package labioopint.model.Enemy.impl;

import java.util.*;

import labioopint.commons.Coordinate;
import labioopint.model.Enemy.api.Enemy;

public abstract class BaseEnemy implements Enemy {

    /*
     * Quando un player viene mangiato dal nemico, il player perde il primo PowerUp
     * che è in coda. Se la lista fosse vuota allora il player sta fermo un turno.
     */
    public void hit(Player player) {
        if (getPosition(maze) == Player.position) {
            playerPowerUP.removeFirst();
        }
    }

    public Coordinate getPosition(Labyrinth maze) {
        return maze.getEnemyCoordinate;
    }
}
