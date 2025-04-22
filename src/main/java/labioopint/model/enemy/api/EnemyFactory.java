package labioopint.model.enemy.api;

import labioopint.model.core.impl.TurnManager;

/**
 * The {@code EnemyFactory} interface defines methods for creating different
 * types of enemies
 * in the game based on their behavior and movement patterns.
 */
public interface EnemyFactory {

    /**
     * Creates an enemy that moves randomly with multiple steps.
     *
     * @param tu the {@link TurnManager} used to manage game state and validate
     *           moves
     * @return an {@link Enemy} instance with random multi-step movement
     */
    Enemy createRandomEnemy(TurnManager tu);

    /**
     * Creates an enemy that chases players or remains stationary if no path to a
     * player exists.
     *
     * @param tu the {@link TurnManager} used to manage game state and validate
     *           moves
     * @return an {@link Enemy} instance with chasing behavior
     */
    Enemy createChaseEnemy(TurnManager tu);

    /**
     * Creates an enemy that moves randomly by a single step.
     *
     * @param tu the {@link TurnManager} used to manage game state and validate
     *           moves
     * @return an {@link Enemy} instance with single-step random movement
     */
    Enemy createSingleStepEnemy(TurnManager tu);
}
