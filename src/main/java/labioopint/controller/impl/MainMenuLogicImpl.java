package labioopint.controller.impl;

import labioopint.controller.api.MainMenuController;
import labioopint.controller.api.MainMenuLogic;
import labioopint.controller.api.SettingsController;

/**
 * This class handles the main menu logic, including starting a game, loading a game,
 * opening settings, and quitting the application.
 */
public final class MainMenuLogicImpl implements MainMenuLogic {

    private final SettingsController settingsController;
    private final MainMenuController controller;
    public static final long serialVersionUID = 1L;
    /**
     * Construction of the {@code MainMenuLogicImpl} with the menù associated controller.
     * 
     * @param controller the controller of the MainMenù
     */
    public MainMenuLogicImpl(final MainMenuController controller) {
        this.settingsController = new SettingsControllerImpl();
        this.controller = controller;
    }

    @Override
    public void startGame() {
        controller.startGame(settingsController.getSettings());
    }

    @Override
    public void loadGame() {
        controller.loadGame();
    }

    @Override
    public boolean isLoaded() {
        return controller.isLoaded();
    }

    @Override
    public void openSettings() {
        settingsController.load();
    }
}
