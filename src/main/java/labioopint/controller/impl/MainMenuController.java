package labioopint.controller.impl;

import labioopint.model.logic.MainMenuLogic;

public class MainMenuController {

    private final MainMenuLogic logic;
    private final SettingsController settingsController;

    public MainMenuController(MainMenuLogic logic, SettingsController settingsController) {
        this.logic = logic;
        this.settingsController = settingsController;
    }

    public void startGame() {
        logic.startGame();
    }

    public void loadGame() {
        logic.loadGame();
    }

    public void openSettings() {
        logic.openSettings(settingsController);
    }

    public void quitGame() {
        logic.quitGame();
    }
}