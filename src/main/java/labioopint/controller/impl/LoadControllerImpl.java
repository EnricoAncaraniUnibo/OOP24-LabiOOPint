package labioopint.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import labioopint.controller.api.GameController;
import labioopint.controller.api.LoadController;
import labioopint.model.utilities.api.Settings;

/**
 * Implementation of the loadController class which is used to load a previus
 * game played.
 */
public final class LoadControllerImpl implements LoadController {

    private GameController loadedGameController;
    private Settings loadedSettings;
    private boolean isGameLoaded;
    private boolean isSettingsLoaded;
    public static final long serialVersionUID = 1L;

    /**
     * Construction of the LoadController.
     *
     */
    public LoadControllerImpl() {
        isGameLoaded = false;
        isSettingsLoaded = false;
    }

    @Override
    public boolean loadLastGame() {
        try (FileInputStream fis = new FileInputStream(new File("src/main/java/labioopint/saving/lastGame.txt"));
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            loadedGameController = (GameController) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            isGameLoaded = false;
            return false;
        }
        isGameLoaded = true;
        return true;
    }

    @Override
    public boolean isLoadedGame() {
        return isGameLoaded;
    }

    @Override
    public boolean isLoadedSettings() {
        return isSettingsLoaded;
    }

    @Override
    public GameController getGameController() {
        return loadedGameController;
    }

    @Override
    public boolean loadSettings() {
        try (FileInputStream fis = new FileInputStream(new File("src/main/java/labioopint/saving/settings.txt"));
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            loadedSettings = (Settings) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            isSettingsLoaded = false;
            return false;
        }
        isSettingsLoaded = true;
        return true;
    }

    @Override
    public Settings getSettings() {
        return loadedSettings;
    }
}
