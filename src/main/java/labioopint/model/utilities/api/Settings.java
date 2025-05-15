package labioopint.model.utilities.api;

import java.io.Serializable;

import labioopint.model.enemy.api.EnemyDifficulty;

/**
 * Represents the settings configuration for a game.
 * This interface provides methods to retrieve various settings such as the
 * number of power-ups, players, enemies, and the enemy difficulty level.
 */
public interface Settings extends Serializable {

    /**
     * Retrieves the number of power-ups available in the game.
     *
     * @return the number of power-ups as an {@link Integer}
     */
    Integer getPowerUps();

    /**
     * Retrieves the number of players in the game.
     *
     * @return the number of players as an {@link Integer}
     */
    Integer getPlayers();

    /**
     * Retrieves the number of enemies in the game.
     *
     * @return the number of enemies as an {@link Integer}
     */
    Integer getEnemy();

    /**
     * Retrieves the difficulty level of the enemies in the game.
     *
     * @return the {@link EnemyDifficulty} representing the enemy difficulty level
     */
    EnemyDifficulty getEnemyDifficulty();

}
