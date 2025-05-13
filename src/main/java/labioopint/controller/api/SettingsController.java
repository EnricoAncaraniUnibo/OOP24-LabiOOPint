package labioopint.controller.api;

import labioopint.model.utilities.api.Settings;
/**
 * Represents the controller responsible for managing the game settings.
 * This interface provides methods to load settingsMenù, retrieve the current settings,
 * and change the settings.
 */
public interface SettingsController {

    /**
     * Loads the settings menù.
     */
    void load();

    /**
     * Retrieves the current settings.
     *
     * @return the {@link Settings} object representing the current settings
     */
    Settings getSettings();

    /**
     * Changes the current settings to the specified new settings.
     *
     * @param newSettings the {@link Settings} object containing the new settings
     */
    void changeSettings(Settings newSettings);

}
