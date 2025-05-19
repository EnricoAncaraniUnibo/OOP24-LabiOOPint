package labioopint.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import labioopint.controller.api.GameController;
import labioopint.controller.api.LoadController;

/**
 * Implementation of the loadController class which is used to load a previus
 * game played.
 */
public final class LoadControllerImpl implements LoadController {

    private GameController loadedGameController;
    private boolean isLoaded;
    public static final long serialVersionUID = 1L;

    /**
     * Construction of the LoadController.
     *
     */
    public LoadControllerImpl() {
        isLoaded = false;
    }

    @Override
    public boolean loadLastGame() {
        try (FileInputStream fis = new FileInputStream(new File("src/main/java/labioopint/saving/lastGame.txt"));
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            loadedGameController = (GameController) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            isLoaded = false;
            return false;
        }
        return true;
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public GameController getGameController() {
        return loadedGameController;
    }
}
