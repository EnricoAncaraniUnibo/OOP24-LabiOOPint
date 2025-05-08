package labioopint.controller.impl;

import labioopint.model.api.Settings;
import labioopint.model.enemy.api.EnemyDifficulty;

public class SettingsLogic {

    private final SettingsController controller;

    public SettingsLogic(SettingsController controller){
        this.controller = controller;
    }

    public void saveNewSettings(int enemyNumber,int playersNummber, int powerUpNumber, String enemyDifficultyString){
        EnemyDifficulty enemyDifficulty = (enemyDifficultyString == "EASY") ? EnemyDifficulty.EASY :
                                          (enemyDifficultyString == "MEDIUM") ? EnemyDifficulty.MEDIUM :
                                          EnemyDifficulty.HARD;
        controller.changeSettings(new Settings(enemyNumber,playersNummber,powerUpNumber,enemyDifficulty));
    }
}