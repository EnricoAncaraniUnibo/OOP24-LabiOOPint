package labioopint.controller.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.io.File;

import labioopint.controller.api.GameController;
import labioopint.controller.api.SaveController;
import labioopint.model.utilities.api.Settings;

/**
 * Implementation of the class SaveController used to save the current game
 * state and settings in a file.
 */
public final class SaveControllerImpl implements SaveController {
    public static final long serialVersionUID = 1L;

    @Override
    public boolean saveGame(final GameController gameController) {
        final File jarDir;
        try {
            jarDir = new File(
                    getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
        } catch (URISyntaxException e) {
            return false;
        }
        final File gameFile = new File(jarDir, "lastGame.txt");
        try (FileOutputStream fos = new FileOutputStream(gameFile, false);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameController);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean saveSettings(final Settings settings) {
        final File jarDir;
        try {
            jarDir = new File(
                    getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
        } catch (URISyntaxException e) {
            return false;
        }
        final File settingsFile = new File(jarDir, "settings.txt");
        try (FileOutputStream fos = new FileOutputStream(settingsFile, false);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(settings);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
