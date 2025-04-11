package labioopint.model.api;

import labioopint.model.Enemy.api.EnemyDifficulty;

public class Settings {
    Integer playerNumber;
    Integer enemyNumber;
    Integer powerUp;
    EnemyDifficulty enemyDifficulty;

    public Settings(Integer enemyNumber, Integer playerNumber, Integer powerUp, EnemyDifficulty enemyDifficulty){
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
