package labioopint.model.utilities.impl;

import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.utilities.api.Settings;

public final class SettingsImpl implements Settings {
    private final Integer playerNumber;
    private final Integer enemyNumber;
    private final Integer powerUp;
    private final EnemyDifficulty enemyDifficulty;

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
