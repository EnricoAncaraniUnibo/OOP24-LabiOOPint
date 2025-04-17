package labioopint.model.Enemy.impl;

import java.util.ArrayList;
import java.util.List;

import labioopint.controller.impl.ActionPredicate;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Enemy.api.EnemyAI;
import labioopint.model.Maze.api.Direction;

import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.api.Coordinate;
import labioopint.model.api.Movable;
import labioopint.model.Maze.impl.LabyrinthImpl;

/**
 * Implementation of the {@link Enemy} interface that provides the behavior
 * for an enemy in the game. The enemy's movement and interactions are
 * controlled
 * by an {@link EnemyAI}.
 */
public class EnemyImpl extends Movable implements Enemy {

    private EnemyAI enemyAI;
    private ActionPredicate ap;
    private TurnManager turn;

    /**
     * Constructs a new EnemyImpl object with the specified EnemyAI and TurnManager.
     * 
     * @param enemyAI the {@link EnemyAI} that controls the enemy's movement.
     * @param tu      the {@link TurnManager} used to manage game state and validate
     *                moves.
     */
    public EnemyImpl(final EnemyAI enemyAI, final TurnManager tu) {
        this.enemyAI = enemyAI;
        turn = tu;
    }

    /**
     * Retrieves the {@link EnemyAI} controlling this enemy.
     * 
     * @return the {@link EnemyAI} instance.
     */
    public EnemyAI getEnemyAI() {
        return enemyAI;
    }

    /**
     * Moves the enemy based on its AI logic and the current game state.
     * 
     * @param players the list of players in the game.
     * @return a list of {@link Coordinate} representing the enemy's movement path.
     */
    @Override
    public List<Coordinate> move(final List<PlayerImpl> players) {
        ap = new ActionPredicate(turn);
        if (!ap.EnemyCanMove(Direction.UP) && !ap.EnemyCanMove(Direction.DOWN)
                && !ap.EnemyCanMove(Direction.LEFT) && !ap.EnemyCanMove(Direction.RIGHT)) {
            return new ArrayList<>();
        } else {
            return enemyAI.getNextPosition(players, turn.getLab().getEnemyCoordinate(this));
        }
    }
    /*
     * Quando un player viene mangiato dal nemico, il player perde il primo PowerUp
     * che è in coda. Se la lista fosse vuota allora il player sta fermo un turno.
     */

    /**
     * Handles the interaction when the enemy hits a player. If a player is at the
     * same position as the enemy, the player loses their first objective. If the
     * player's objective list is empty, the player skips their next turn.
     * 
     * @param players the list of players in the game.
     */
    @Override
    public void playerHit(final List<PlayerImpl> players) {
        LabyrinthImpl maze = turn.getLab();
        for (PlayerImpl player : players) {
            if (maze.getEnemyCoordinate(this).getRow() == maze.getPlayerCoordinate(player).getRow()
                    && maze.getEnemyCoordinate(this).getColumn() == maze.getPlayerCoordinate(player).getColumn()) {
                player.removeObjective();
            }
        }
    }
}
