package labioopint.model.enemy.api;

/**
 * Factory interface for creating different types of enemies in the game.
 * This interface provides methods to instantiate various enemy types
 * with predefined behaviors.
 */
public interface EnemyFactory {

    /**
     * Creates an enemy with random behavior.
     *
     * @return an instance of {@link Enemy} with random behavior
     */
    Enemy createRandomEnemy();

    /**
     * Creates an enemy that chases players.
     *
     * @return an instance of {@link Enemy} with chasing behavior
     */
    Enemy createChaseEnemy();

    /**
     * Creates an enemy that moves a single step at a time.
     *
     * @return an instance of {@link Enemy} with single-step movement behavior
     */
    Enemy createSingleStepEnemy();

}
