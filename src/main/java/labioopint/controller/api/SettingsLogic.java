package labioopint.controller.api;

import java.io.Serializable;

/**
 * Represents the logic for managing game settings menù.
 * This interface provides a method to save new settings retrived from the menù.
 */
public interface SettingsLogic extends Serializable {
    /**
     * Saves new settings for the game.
     *
     * @param enemyNumber the number of enemies
     * @param playersNumber the number of players
     * @param powerUpNumber the number of power-ups
     * @param enemyDifficultyString the difficulty level of enemies as a string
     */
    void saveNewSettings(int enemyNumber, int playersNumber,
            int powerUpNumber, String enemyDifficultyString);

}
