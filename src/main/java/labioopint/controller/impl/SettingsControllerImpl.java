package labioopint.controller.impl;

import labioopint.controller.api.SaveController;
import labioopint.controller.api.SettingsController;
import labioopint.model.utilities.api.Settings;
import labioopint.model.utilities.impl.SettingsImpl;
import labioopint.model.enemy.api.EnemyDifficulty;

/**
 * This class manages the settings of the application, including loading and
 * modifying them.
 */
public final class SettingsControllerImpl implements SettingsController {

    private static final int DEFAULT_ENEMY_NUMBER = 1;
    private static final int DEFAULT_PLAYERS = 4;
    private static final int DEFAULT_POWER_UPS = 6;
    private static final String DEFAULT_DIFFICULTY = "MEDIUM";
    public static final long serialVersionUID = 1L;

    private final SaveController saveController;

    /**
     * Construction of the {@code SettingsControllerImpl}.
     * By default it create the Menù for the setting and create some settings for a
     * default scenario.
     */
    public SettingsControllerImpl() {
        saveController = new SaveControllerImpl();
        saveNewSettings(DEFAULT_ENEMY_NUMBER, DEFAULT_PLAYERS, DEFAULT_POWER_UPS, DEFAULT_DIFFICULTY);
    }

    @Override
    public boolean saveNewSettings(final int numberOfEnemy, final int numberOfPlayers, final int numberOfPowerUps,
            final String enemyDifficulty) {
        final EnemyDifficulty difficulty = "EASY".equals(enemyDifficulty) ? EnemyDifficulty.EASY
                : "MEDIUM".equals(enemyDifficulty) ? EnemyDifficulty.MEDIUM : EnemyDifficulty.HARD;
        final Settings settings = new SettingsImpl(numberOfEnemy, numberOfPlayers, numberOfPowerUps, difficulty);
        return saveController.saveSettings(settings);
    }
}
