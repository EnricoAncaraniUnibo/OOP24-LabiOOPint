package labioopint.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import labioopint.controller.api.LoadController;
import labioopint.model.core.impl.TurnManager;

public class LoadControllerImpl implements LoadController {

    private TurnManager loadedTurnManager;
    private boolean isLoaded;

    public LoadControllerImpl() {
        isLoaded = false;
    }

    @Override
    public void loadLastGame() {
        try {
            FileInputStream fis = new FileInputStream(new File("src/main/java/labioopint/saving/lastGame.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            loadedTurnManager = (TurnManager) ois.readObject();
            ois.close();
            fis.close();
            isLoaded = true;
            System.out.println("Loaded success");
        } catch (Exception e) {
            System.out.println(e);
            loadingNotPossible();
        }
    }

    @Override
    public boolean isLoaded() {
        if (isLoaded) {
            return true;
        }
        return false;
    }

    @Override
    public TurnManager getTurnManager() {
        return loadedTurnManager;
    }

    private void loadingNotPossible() {
        System.out.println("No such file found");

    }
}
