package labioopint.controller.impl;

import labioopint.controller.api.LoadController;
import labioopint.model.api.Settings;
import labioopint.model.core.impl.TurnManager;
import labioopint.view.MainMenu;

public class MainMenuController {

    private final MainMenu view;
    private final LoadController loadController;

    public MainMenuController() {
        view = new MainMenu(this);
        loadController = new LoadControllerImpl();
    }

    public void load(){
        view.setVisible(true);
    }

    public void startGame(Settings settings) {
        new TurnManager(settings);
    }

    public void loadGame() {
        loadController.loadLastGame();
        if(loadController.isLoaded()){new TurnManager(loadController.getTurnManager());}
    }
}