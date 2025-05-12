package labioopint.controller.impl;

/**
 * This class handles the main menu logic, including starting a game, loading a game,
 * opening settings, and quitting the application.
 */
public final class MainMenuLogic {

    private final SettingsController settingsController;
    private final MainMenuController controller;

    /**
     * Constructs a new MainMenuLogic instance.
     *
     * @param controller the main menu controller, must not be null
     */
    public MainMenuLogic(final MainMenuController controller) {
        this.settingsController = new SettingsController();
        this.controller = controller;
    }

    /**
     * Starts a new game using the current settings.
     */
    public void startGame() {
        controller.startGame(settingsController.getSettings());
    }

    /**
     * Loads a previously saved game.
     */
    public void loadGame() {
        controller.loadGame();
    }

    public boolean isLoaded() {
        return controller.isLoaded();
    }

    /**
     * Opens the settings menu.
     */
    public void openSettings() {
        settingsController.load();
    }

    /**
     * Quits the application.
     */
    public void quitGame() {
        System.exit(0);
    }
}
