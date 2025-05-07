package labioopint.controller.impl;

import labioopint.view.MainMenu;

public class Starter {
    public static void main(String[] args) {
        try {
            MainMenu menu = new MainMenu(new MainMenuController(new MainMenuLogic(), new SettingsController(new SettingsLogic())));
            menu.setVisible(true);
        } catch (Exception e) {
            
        }
    }
}
