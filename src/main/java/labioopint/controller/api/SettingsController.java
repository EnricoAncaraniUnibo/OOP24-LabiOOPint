package labioopint.controller.api;

import java.io.Serializable;

/**
 * Represents the controller responsible for managing the game settings.
 * This interface provides methods to load settingsMenù, retrieve the current settings,
 * and change the settings.
 */
public interface SettingsController extends Serializable {

    boolean saveNewSettings(int numberOfEnemy, int numberOfPlayers, int numberOfPowerUps, String enemyDifficulty);
}
