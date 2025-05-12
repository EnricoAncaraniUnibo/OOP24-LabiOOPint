package labioopint.controller.impl;

import labioopint.controller.api.LoadController;
import labioopint.model.api.Settings;
import labioopint.model.core.impl.TurnManager;
import labioopint.view.MainMenu;

/**
 * This class manages the main menu, including loading the menu, starting a game,
 * and loading a saved game.
 */
public final class MainMenuController {

    private final MainMenu view;
    private final LoadController loadController;

    /**
     * Constructs a new MainMenuController instance.
     */
    public MainMenuController() {
        view = new MainMenu(this);
        loadController = new LoadControllerImpl();
    }

    /**
     * Loads and displays the main menu.
     */
    public void load() {
        view.setVisible(true);
    }

    /**
     * Starts a new game with the specified settings.
     *
     * @param settings the settings to use for the new game, must not be null
     */
    public void startGame(final Settings settings) {
        new TurnManager(settings);
    }

    /**
     * Loads the last saved game, if available.
     */
    public void loadGame() {
        loadController.loadLastGame();
        if (loadController.isLoaded()) {
            new TurnManager(loadController.getTurnManager());
        }
    }

    public boolean isLoaded() {
        return loadController.isLoaded();
    }
}
