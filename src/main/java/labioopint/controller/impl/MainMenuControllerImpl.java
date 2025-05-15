package labioopint.controller.impl;

import labioopint.controller.api.GameController;
import labioopint.controller.api.LoadController;
import labioopint.controller.api.MainMenuController;
import labioopint.model.utilities.api.Settings;
import labioopint.view.GameView;
import labioopint.view.MainMenu;
/**
 * Implementation of the Controller of the Main Menù.
 */
public final class MainMenuControllerImpl implements MainMenuController {

    private final MainMenu view;
    private final LoadController loadController;
    public static final long serialVersionUID = 1L;
    /**
     * Construction of a {@code MainMenuControllerImpl}.
     */
    public MainMenuControllerImpl() {
        view = new MainMenu(this);
        loadController = new LoadControllerImpl();
    }

    @Override
    public void load() {
        view.setVisible(true);
    }

    @Override
    public void startGame(final Settings settings) {
        final GameController gc = new GameControllerImpl(settings);
        final GameView gv = new GameView(gc);
        gv.setVisible(true);
    }

    @Override
    public void loadGame() {
        loadController.loadLastGame();
        if (loadController.isLoaded()) {
            final GameController gc = loadController.getGameController();
            final GameView gv = new GameView(gc);
            gv.setVisible(true);
        }
    }

    @Override
    public boolean isLoaded() {
        return loadController.isLoaded();
    }
}
