package labioopint.controller.impl;

public class MainMenuLogic {

    private SettingsController settingsController;
    private MainMenuController controller;

    public MainMenuLogic(MainMenuController controller){
        settingsController = new SettingsController();
        this.controller = controller;
    }

    public void startGame() {
        controller.startGame(settingsController.getSettings());
    }

    public void loadGame() {
        controller.loadGame(settingsController.getLoadedSettings());
    }

    public void openSettings() {
        settingsController.load();
    }

    public void quitGame() {
        System.exit(0);
    }
}