package labioopint.controller.impl;

import labioopint.model.api.Settings;
import labioopint.model.enemy.api.EnemyDifficulty;

public class SettingsLogic {

    private Settings currentSettings;

    public void applySettings(int enemy, int players, int powerUps, String difficulty) {
        this.currentSettings = new Settings(
            enemy,
            players,
            powerUps,
            EnemyDifficulty.valueOf(difficulty)
        );
    }

    public Settings getSettings() {
        return this.currentSettings;
    }
}