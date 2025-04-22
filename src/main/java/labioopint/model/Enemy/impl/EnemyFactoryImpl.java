package labioopint.model.enemy.impl;

import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.enemy.api.EnemyFactory;
import labioopint.model.enemy.impl.ais.ChaseAI;
import labioopint.model.enemy.impl.ais.RandomAI;
import labioopint.model.enemy.impl.ais.SingleStepRandomAI;

/**
 * Implementation of the {@link EnemyFactory} interface for creating different
 * types of enemies
 * in the game based on their behavior and movement patterns.
 */
public class EnemyFactoryImpl implements EnemyFactory {

    /**
     * Creates an enemy that moves randomly with multiple steps.
     *
     * @param tu the {@link TurnManager} used to manage game state and validate
     *           moves
     * @return an {@link Enemy} instance with random multi-step movement
     */
    @Override
    public Enemy createRandomEnemy(final TurnManager tu) {
        return new EnemyImpl(new RandomAI(tu), tu);
    }

    /**
     * Creates an enemy that chases players or remains stationary if no path to a
     * player exists.
     *
     * @param tu the {@link TurnManager} used to manage game state and validate
     *           moves
     * @return an {@link Enemy} instance with chasing behavior
     */
    @Override
    public Enemy createChaseEnemy(final TurnManager tu) {
        return new EnemyImpl(new ChaseAI(tu), tu);
    }

    /**
     * Creates an enemy that moves randomly by a single step.
     *
     * @param tu the {@link TurnManager} used to manage game state and validate
     *           moves
     * @return an {@link Enemy} instance with single-step random movement
     */
    @Override
    public Enemy createSingleStepEnemy(final TurnManager tu) {
        return new EnemyImpl(new SingleStepRandomAI(tu), tu);
    }

}
