package labioopint.controller.impl;

import labioopint.model.api.Settings;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.view.SettingsMenu;

public class SettingsController {

    private Settings settings;
    private final SettingsMenu view;
    //private final SettingsLogic logic;

    public SettingsController() {
        view = new SettingsMenu(this);
        settings = new Settings(1,4,6,EnemyDifficulty.MEDIUM);
        //this.logic = logic;
    }

    public void load(){
        view.setVisible(true);
    }

    public Settings getSettings() {
        return settings;
    }

    public Settings getLoadedSettings() {
        //Take the settings from LoadGame class
        return null;
    }

    public void changeSettings(Settings newSettings){
        this.settings = newSettings;
    }
}