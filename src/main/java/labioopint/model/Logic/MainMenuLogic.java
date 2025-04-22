package labioopint.logic;

import labioopint.model.api.Settings;
import labioopint.model.core.impl.TurnManager;
import labioopint.view.SettingsMenu;

public class MainMenuLogic {

    private Settings settings;

    public MainMenuLogic(Settings settings) {
        this.settings = settings;
    }

    public void startGame() {
        if (this.settings == null) {
            new TurnManager(new Settings(1,4,6,EnemyDifficulty.NORMAL));
        }
        new TurnManager(settings);
    }

    public void loadGame() {
        // logica per caricare una partita salvata
    }

    public void openSettings(SettingsController settingsController) {
        new SettingsMenu(settingsController).setVisible(true);
    }

    public void quitGame() {
        System.exit(0);
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return this.settings;
    }
}