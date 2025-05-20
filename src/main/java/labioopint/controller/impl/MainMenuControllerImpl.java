package labioopint.controller.impl;

import labioopint.controller.api.GameController;
import labioopint.controller.api.LoadController;
import labioopint.controller.api.MainMenuController;
import labioopint.model.utilities.api.Settings;
import labioopint.view.GameView;
import labioopint.view.SettingsMenu;
/**
 * Implementation of the Controller of the Main Menù.
 */
public final class MainMenuControllerImpl implements MainMenuController {

    private final LoadController loadController;
    private final SettingsMenu settingsMenu;
    public static final long serialVersionUID = 1L;
    /**
     * Construction of a {@code MainMenuControllerImpl}.
     */
    public MainMenuControllerImpl() {
        settingsMenu = new SettingsMenu();
        loadController = new LoadControllerImpl();
    }

    @Override
    public boolean startGame() {
        if (loadController.loadSettings()) {
            final Settings settings = loadController.getSettings();
            final GameController gameController = new GameControllerImpl(settings);
            final GameView gameView = new GameView(gameController);
            gameView.setVisible(true);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void loadGame() {
        if (loadController.loadLastGame()) {

            final GameController gc = loadController.getGameController();
            final GameView gv = new GameView(gc);
            gv.setVisible(true);
        }
    }

    @Override
    public boolean isLoaded() {
        return loadController.isLoadedGame();
    }

    @Override
    public void openSettings() {
        settingsMenu.open();
    }
}
