package labioopint.model.enemy.impl;

import java.util.ArrayList;
import java.util.List;

import labioopint.controller.api.ActionPredicate;
import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.impl.MovableImpl;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.enemy.api.EnemyAI;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;
/**
 * The Implementation of the Enemy interface.
 */
public final class EnemyImpl extends MovableImpl implements Enemy {
    public static final long serialVersionUID = 1L;
    private final EnemyAI enemyAI;
    private Player lastHit;
    /**
     * Construction of an general enemy with an specified intelligence.
     * 
     * @param enemyAI the intelligence of this enemy.
     */
    public EnemyImpl(final EnemyAI enemyAI) {
        this.enemyAI = enemyAI;
        lastHit = null;
    }

    @Override
    public EnemyAI getEnemyAI() {
        return enemyAI;
    }

    @Override
    public List<Coordinate> move(final List<Player> players, final ActionPredicate actionPredicate, final Labyrinth labyrinth) {
        if (!actionPredicate.enemyCanMove(Direction.UP, this, labyrinth) && !actionPredicate.enemyCanMove(Direction.DOWN, this, labyrinth)
                && !actionPredicate.enemyCanMove(Direction.LEFT, this, labyrinth)
                && !actionPredicate.enemyCanMove(Direction.RIGHT, this, labyrinth)) {
            return new ArrayList<>();
        } else {
            return enemyAI.getNextPosition(players, labyrinth.getEnemyCoordinate(this), actionPredicate, labyrinth);
        }
    }

    @Override
    public void playerHit(final List<Player> players, final Labyrinth labyrinth) {
        for (final Player player : players) {
            if (labyrinth.getEnemyCoordinate(this).equals(labyrinth.getPlayerCoordinate(player))) {
                if (!player.isInvincibilityStatus() && !player.equals(lastHit) && !player.getObjetives().isEmpty()) {
                    final PowerUp pou = player.getObjetives().get(0);
                    player.removeObjectiveSelect(pou);
                    lastHit = player;
                    labyrinth.addPowerUp(pou);
                } else {
                    player.disableInvincible();
                }

            }
        }
    }

    @Override
    public void clearLastHit() {
        lastHit = null;
    }

    @Override
    public boolean isPresentLastHit() {
        return lastHit != null;
    }

    @Override
    public String getLastHitID() {
        return lastHit.getID();
    }
}
