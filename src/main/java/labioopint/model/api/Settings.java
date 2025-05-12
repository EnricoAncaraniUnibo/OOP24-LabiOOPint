package labioopint.model.api;

import labioopint.model.enemy.api.EnemyDifficulty;

public final class Settings {
    private final Integer playerNumber;
    private final Integer enemyNumber;
    private final Integer powerUp;
    private final EnemyDifficulty enemyDifficulty;

    public Settings(final Integer enemyNumber, final Integer playerNumber, final Integer powerUp,
            final EnemyDifficulty enemyDifficulty) {
        this.enemyNumber = enemyNumber;
        this.playerNumber = playerNumber;
        this.powerUp = powerUp;
        this.enemyDifficulty = enemyDifficulty;
    }

    public Integer getPowerUps() {
        return powerUp;
    }

    public Integer getPlayers() {
        return playerNumber;
    }

    public Integer getEnemy() {
        return enemyNumber;
    }

    public EnemyDifficulty getEnemyDifficulty() {
        return enemyDifficulty;
    }
}
