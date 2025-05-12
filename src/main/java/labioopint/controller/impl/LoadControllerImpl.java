package labioopint.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import labioopint.controller.api.LoadController;
import labioopint.model.core.impl.TurnManager;

public final class LoadControllerImpl implements LoadController {

    private TurnManager loadedTurnManager;
    private boolean isLoaded;

    public LoadControllerImpl() {
        isLoaded = false;
    }

    @Override
    public void loadLastGame() {
        try {
            final FileInputStream fis = new FileInputStream(new File("src/main/java/labioopint/saving/lastGame.txt"));
            final ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                loadedTurnManager = (TurnManager) ois.readObject();
            } catch (ClassNotFoundException e) {
                //TODO
            }
            ois.close();
            fis.close();
            isLoaded = true;
        } catch (IOException e) {
            //TODO
        }
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public TurnManager getTurnManager() {
        return loadedTurnManager;
    }
}
