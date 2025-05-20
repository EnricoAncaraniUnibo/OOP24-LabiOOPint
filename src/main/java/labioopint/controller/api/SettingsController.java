package labioopint.controller.api;

import java.io.Serializable;

/**
 * Represents the controller responsible for managing the game settings.
 * This interface provides methods to load settingsMenù, retrieve the current settings,
 * and change the settings.
 */
public interface SettingsController extends Serializable {

    /**
     * Save the settings passed.
     * 
     * @param numberOfEnemy if enemy is present or not
     * @param numberOfPlayers the number of players
     * @param numberOfPowerUps the number of powerUps
     * @param enemyDifficulty the difficulty of the enemy
     * @return {@code true} if the saving has been successful, {@code false} otherwise
     */
    boolean saveNewSettings(int numberOfEnemy, int numberOfPlayers, int numberOfPowerUps, String enemyDifficulty);
}
