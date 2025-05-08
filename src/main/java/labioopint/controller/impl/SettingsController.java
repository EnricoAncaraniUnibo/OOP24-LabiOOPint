package labioopint.controller.impl;

import labioopint.model.api.Settings;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.view.SettingsMenu;

/**
 * This class manages the settings of the application, including loading and modifying them.
 */
public final class SettingsController {

    private static final int DEFAULT_LEVEL = 1;
    private static final int DEFAULT_PLAYERS = 4;
    private static final int DEFAULT_TURNS = 6;
    private static final EnemyDifficulty DEFAULT_DIFFICULTY = EnemyDifficulty.MEDIUM;

    private Settings settings;
    private final SettingsMenu view;

    /**
     * Constructs a new SettingsController instance with default settings.
     */
    public SettingsController() {
        view = new SettingsMenu(this);
        settings = new Settings(DEFAULT_LEVEL, DEFAULT_PLAYERS, DEFAULT_TURNS, DEFAULT_DIFFICULTY);
    }

    /**
     * Loads and displays the settings menu.
     */
    public void load() {
        view.setVisible(true);
    }

    /**
     * Retrieves the current settings.
     *
     * @return the current settings
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Updates the settings with new values.
     *
     * @param newSettings the new settings to apply, must not be null
     */
    public void changeSettings(final Settings newSettings) {
        this.settings = newSettings;
    }
}
