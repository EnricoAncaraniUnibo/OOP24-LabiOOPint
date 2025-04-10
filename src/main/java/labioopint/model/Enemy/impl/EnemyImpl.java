package labioopint.model.Enemy.impl;

import java.util.*;

import labioopint.controller.impl.ActionPredicate;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Enemy.api.EnemyAI;
import labioopint.model.Maze.api.Direction;

import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.api.Coordinate;
import labioopint.model.Maze.impl.LabyrinthImpl;

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

    public EnemyAI getEnemyAI() {
        return enemyAI;
    }

    @Override
    public List<Coordinate> move(final List<PlayerImpl> players) {
        if(!ActionPredicate.EnemyCanMove(Direction.UP) && !ActionPredicate.EnemyCanMove(Direction.DOWN)
                && !ActionPredicate.EnemyCanMove(Direction.LEFT) && !ActionPredicate.EnemyCanMove(Direction.RIGHT)) {
            return new ArrayList<>();
        } else {
            return enemyAI.getNextPosition(players, TurnManager.GetLab().getEnemyCoordinate(this));
        }
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
    public void playerHit(final List<PlayerImpl> players) {
        LabyrinthImpl maze = TurnManager.GetLab();
        for (PlayerImpl player : players) {
            if (maze.getEnemyCoordinate(this).getRow() == maze.getPlayerCoordinate(player).getRow()
                    && maze.getEnemyCoordinate(this).getColumn() == maze.getPlayerCoordinate(player).getColumn()) { 
                player.removeObjective();
            }
        }
    }
}
