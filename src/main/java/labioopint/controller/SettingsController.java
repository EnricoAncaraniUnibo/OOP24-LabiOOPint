package labioopint.controller;

import labioopint.logic.SettingsLogic;

public class SettingsController {

    private final SettingsLogic logic;

    public SettingsController(SettingsLogic logic) {
        this.logic = logic;
    }

    public void saveSettings(int enemy, int players, int powerUps, String difficulty) {
        logic.applySettings(enemy, players, powerUps, difficulty);
    }

    public Settings getSettings() {
        return logic.getSettings();
    }
}