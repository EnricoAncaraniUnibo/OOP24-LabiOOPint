package labioopint.controller.impl;

import labioopint.model.api.Settings;
import labioopint.model.enemy.api.EnemyDifficulty;

/**
 * This class handles the logic for managing and saving new settings.
 */
public final class SettingsLogic {

    private final SettingsController controller;

    /**
     * Constructs a new SettingsLogic instance.
     *
     * @param controller the settings controller, must not be null
     */
    public SettingsLogic(final SettingsController controller) {
        this.controller = controller;
    }

    /**
     * Saves new settings based on the provided parameters.
     *
     * @param enemyNumber           the number of enemies
     * @param playersNumber         the number of players
     * @param powerUpNumber         the number of power-ups
     * @param enemyDifficultyString the difficulty level as a string ("EASY", "MEDIUM", "HARD")
     */
    public void saveNewSettings(final int enemyNumber, final int playersNumber, 
                                final int powerUpNumber, final String enemyDifficultyString) {
        final EnemyDifficulty enemyDifficulty = "EASY".equals(enemyDifficultyString) 
                ? EnemyDifficulty.EASY 
                : "MEDIUM".equals(enemyDifficultyString) 
                    ? EnemyDifficulty.MEDIUM 
                    : EnemyDifficulty.HARD;
        controller.changeSettings(new Settings(enemyNumber, playersNumber, powerUpNumber, enemyDifficulty));
    }
}
