package labioopint.model.api;

import labioopint.model.Enemy.api.EnemyDifficulty;

public class Settings {
    Integer playerNumber;
    Integer enemyNumber;
    Integer powerUp;
    EnemyDifficulty enemyDifficulty;

    public Settings(){
        enemyNumber = Integer.valueOf(1);
        playerNumber = Integer.valueOf(4);
        powerUp = Integer.valueOf(0);
        enemyDifficulty = EnemyDifficulty.MEDIUM;
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
