package labioopint.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import labioopint.controller.api.GameController;
import labioopint.controller.api.LoadController;
/**
 * Implementation of the loadController class which is used to load a previus game played.
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
    public void loadLastGame() {
        File file = new File("src/main/java/labioopint/saving/lastGame.txt");
        try (FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            try {
                loadedGameController = (GameController) ois.readObject();
                isLoaded = true;
            } catch (ClassNotFoundException e) {

            }
        } catch (IOException e) {

        }
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
