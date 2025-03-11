package labioopint.model.Enemy.impl;

import java.util.*;

import labioopint.commons.Coordinate;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Labyrinth.api.Labyrinth;
import labioopint.model.Player.api.Player;

/**
 * BaseEnemy is an abstract class that provides a base implementation for the
 * Enemy interface.
 */
public abstract class BaseEnemy extends Movable implements Enemy {

    /*
     * Quando un player viene mangiato dal nemico, il player perde il primo PowerUp
     * che è in coda. Se la lista fosse vuota allora il player sta fermo un turno.
     */
    
    /**
     * When a player is hit by the enemy, the player loses the first PowerUp in their queue.
     * If the player's PowerUp list is empty, the player skips a turn.
     * 
     * @param player the player that is hit by the enemy.
     */
    public void hit(Player player) {
        if (getPosition(maze) == Player.position) {
            playerPowerUP.removeFirst();
        }
    }

    /**
     * Retrieves the current position of the enemy in the labyrinth.
     * 
     * @return the coordinate of the enemy's position.
     */
    public Coordinate getPosition() {
        // TODO 
        // return maze.getEnemyCoordinate;
        return null;
    }
}
