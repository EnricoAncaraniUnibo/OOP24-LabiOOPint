package labioopint.controller.impl;

import labioopint.controller.api.SettingsController;
import labioopint.model.utilities.api.Settings;
import labioopint.model.utilities.impl.SettingsImpl;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.view.SettingsMenu;

/**
 * This class manages the settings of the application, including loading and modifying them.
 */
public final class SettingsControllerImpl implements SettingsController {

    private static final int DEFAULT_LEVEL = 1;
    private static final int DEFAULT_PLAYERS = 4;
    private static final int DEFAULT_TURNS = 6;
    private static final EnemyDifficulty DEFAULT_DIFFICULTY = EnemyDifficulty.MEDIUM;
    public static final long serialVersionUID = 1L;

    private Settings settings;
    private final SettingsMenu view;
    /**
     * Construction of the {@code SettingsControllerImpl}.
     * By default it create the Menù for the setting and create some settings for a default scenario.
     */
    public SettingsControllerImpl() {
        view = new SettingsMenu(this);
        settings = new SettingsImpl(DEFAULT_LEVEL, DEFAULT_PLAYERS, DEFAULT_TURNS, DEFAULT_DIFFICULTY);
    }

    @Override
    public void load() {
        view.setVisible(true);
    }

    @Override
    public Settings getSettings() {
        return settings;
    }

    @Override
    public void changeSettings(final Settings newSettings) {
        this.settings = newSettings;
    }
}
