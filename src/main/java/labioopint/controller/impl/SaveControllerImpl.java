package labioopint.controller.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.File;

import labioopint.controller.api.SaveController;
import labioopint.model.core.impl.TurnManager;

public class SaveControllerImpl implements SaveController, Serializable {

    public SaveControllerImpl(TurnManager tManager) {

    }

    @Override
    public void save(TurnManager turnManager) {
        try {
            FileOutputStream fos = new FileOutputStream(new File("src/main/java/labioopint/saving/lastGame.txt"),
                    false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(turnManager);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }
}
