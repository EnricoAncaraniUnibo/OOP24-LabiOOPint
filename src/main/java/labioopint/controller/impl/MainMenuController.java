package labioopint.controller.impl;

import labioopint.model.api.Settings;
import labioopint.model.core.impl.TurnManager;
import labioopint.view.MainMenu;

public class MainMenuController {

    private final MainMenu view;

    public MainMenuController() {
        view = new MainMenu(this);
    }

    public void load(){
        view.setVisible(true);
    }

    public void startGame(Settings settings) {
        new TurnManager(settings);
    }

    public void loadGame(Settings settings) {
        new TurnManager(settings);
    }
}