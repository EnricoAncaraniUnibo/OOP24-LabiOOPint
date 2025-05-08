package labioopint.controller.impl;

import labioopint.controller.api.LoadController;
import labioopint.model.api.Settings;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.view.SettingsMenu;

public class SettingsController {

    private Settings settings;
    private final SettingsMenu view;
    private final LoadController loadController;

    public SettingsController() {
        view = new SettingsMenu(this);
        settings = new Settings(1,4,6,EnemyDifficulty.MEDIUM);
        loadController = new LoadControllerImpl();
    }

    public void load(){
        view.setVisible(true);
    }

    public Settings getSettings() {
        return settings;
    }

    public Settings getLoadedSettings() {
        loadController.loadLastGame();
        if(loadController.isLoaded()){
            
        }
        return null;
    }

    public void changeSettings(Settings newSettings){
        this.settings = newSettings;
    }
}