package labioopint.controller.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;

import labioopint.controller.api.GameController;
import labioopint.controller.api.SaveController;
import labioopint.model.utilities.api.Settings;

/**
 * Implementation of the class SaveController used to save the current game
 * state in a file.
 */
public final class SaveControllerImpl implements SaveController {
    public static final long serialVersionUID = 1L;

    @Override
    public boolean saveGame(final GameController gameController) {
        try (FileOutputStream fos = new FileOutputStream(new File("src/main/java/labioopint/saving/lastGame.txt"),
                false); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameController);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean saveSettings(final Settings settings) {
        try (FileOutputStream fos = new FileOutputStream(new File("src/main/java/labioopint/saving/settings.txt"),
                false); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(settings);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
