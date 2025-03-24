package labioopint.model.Enemy.impl;

import java.util.*;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Enemy.api.EnemyAI;
import labioopint.model.api.Coordinate;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.player.impl.Player;

/**
 * BaseEnemy is an class that provides a base implementation for the
 * Enemy interface.
 */
public class EnemyImpl extends Movable implements Enemy {

    private EnemyAI enemyAI;
    private Coordinate position;

    /**
     * Constructs a new EnemyImpl object with the specified EnemyAI and position.
     * 
     * @param enemyAI the EnemyAI that controls the enemy's movement.
     */
    public EnemyImpl(EnemyAI enemyAI) {
        this.enemyAI = enemyAI;
    }

    @Override
    public void move(Labyrinth maze, List<Player> players) {
        position = enemyAI.getNextPosition(maze, players, position);
    }
    /*
     * Quando un player viene mangiato dal nemico, il player perde il primo PowerUp
     * che è in coda. Se la lista fosse vuota allora il player sta fermo un turno.
     */

    /**
     * When a player is hit by the enemy, the player loses the first PowerUp in
     * their queue.
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

    @Override
    public Optional<Player> playerHit(List<Player> players) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playerHit'");
    }

    @Override
    public Coordinate getCoordinate() {
        return position;
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.position = coordinate;
    }
}
