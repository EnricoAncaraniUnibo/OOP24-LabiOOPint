package labioopint.logic;

import labioopint.model.api.Settings;
import labioopint.model.Enemy.api.EnemyDifficulty;

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