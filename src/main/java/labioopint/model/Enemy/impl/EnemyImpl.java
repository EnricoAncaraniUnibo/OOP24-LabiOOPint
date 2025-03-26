package labioopint.model.Enemy.impl;

import java.util.*;

import labioopint.model.Core.impl.TurnMenager;
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

    /**
     * Constructs a new EnemyImpl object with the specified EnemyAI.
     * 
     * @param enemyAI the EnemyAI that controls the enemy's movement.
     */
    public EnemyImpl(EnemyAI enemyAI) {
        this.enemyAI = enemyAI;
    }

    @Override
    public Coordinate move(final Labyrinth maze, final List<Player> players) {
        return enemyAI.getNextPosition(maze, players, maze.getEnemyPosition());
    }
    /*
     * Quando un player viene mangiato dal nemico, il player perde il primo PowerUp
     * che è in coda. Se la lista fosse vuota allora il player sta fermo un turno.
     */

    /**
     * Retrieves the current position of the enemy in the labyrinth.
     * 
     * @return the coordinate of the enemy's position.
     */

    @Override
    public void playerHit(final List<Player> players) {
        Labyrinth maze = TurnMenager.GetLab();
        for (Player player : players) {
            if (maze.getEnemyCoordinate(this).equals(maze.getPlayerCoordinate(player))) { 
                player.removeFirst();
            }
        }
    }
}
