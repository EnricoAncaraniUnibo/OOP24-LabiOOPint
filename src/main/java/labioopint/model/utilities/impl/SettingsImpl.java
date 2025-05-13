package labioopint.model.utilities.impl;

import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.utilities.api.Settings;
/**
 * The implementation of the setting interface.
 * Used to the informations of the settings of the game.
 */
public final class SettingsImpl implements Settings {
    private final Integer playerNumber;
    private final Integer enemyNumber;
    private final Integer powerUp;
    private final EnemyDifficulty enemyDifficulty;
    /**
     * Create a new Settings by passing it the values.
     * 
     * @param enemyNumber if enemy is present or not
     * @param playerNumber number of players
     * @param powerUp number of powerups
     * @param enemyDifficulty difficulty of the enemy
     */
    public SettingsImpl(final Integer enemyNumber, final Integer playerNumber, final Integer powerUp,
            final EnemyDifficulty enemyDifficulty) {
        this.enemyNumber = enemyNumber;
        this.playerNumber = playerNumber;
        this.powerUp = powerUp;
        this.enemyDifficulty = enemyDifficulty;
    }

    @Override
    public Integer getPowerUps() {
        return powerUp;
    }

    @Override
    public Integer getPlayers() {
        return playerNumber;
    }

    @Override
    public Integer getEnemy() {
        return enemyNumber;
    }

    @Override
    public EnemyDifficulty getEnemyDifficulty() {
        return enemyDifficulty;
    }
}
