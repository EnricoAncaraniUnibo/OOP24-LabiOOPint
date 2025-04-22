package labioopint.model.enemy.impl;

import java.util.ArrayList;
import java.util.List;

import labioopint.controller.api.ActionPredicate;
import labioopint.controller.impl.ActionPredicateImpl;
import labioopint.model.api.Coordinate;
import labioopint.model.api.Movable;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.enemy.api.EnemyAI;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.impl.LabyrinthImpl;
import labioopint.model.player.impl.PlayerImpl;

/**
 * Implementation of the {@link Enemy} interface that provides the behavior
 * for an enemy in the game. The enemy's movement and interactions are
 * controlled
 * by an {@link EnemyAI}.
 */
public class EnemyImpl extends Movable implements Enemy {

    private final EnemyAI enemyAI;
    private final TurnManager turn;

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
    @Override
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
        final ActionPredicate ap = new ActionPredicateImpl(turn);
        if (!ap.enemyCanMove(Direction.UP) && !ap.enemyCanMove(Direction.DOWN)
                && !ap.enemyCanMove(Direction.LEFT) && !ap.enemyCanMove(Direction.RIGHT)) {
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
        final LabyrinthImpl maze = turn.getLab();
        for (final PlayerImpl player : players) {
            if (maze.getEnemyCoordinate(this).equals(maze.getPlayerCoordinate(player))) {
                player.removeObjective();
            }
        }
    }
}
