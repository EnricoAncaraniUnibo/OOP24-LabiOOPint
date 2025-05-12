package labioopint.controller.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.File;

import labioopint.controller.api.SaveController;
import labioopint.model.core.impl.TurnManager;

public final class SaveControllerImpl implements SaveController, Serializable {
    public static final long serialVersionUID = 1L;

    @Override
    public void save(final TurnManager turnManager) {
        try {
            final FileOutputStream fos = new FileOutputStream(new File("src/main/java/labioopint/saving/lastGame.txt"),
                    false);
            final ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(turnManager);
            oos.close();
            fos.close();
        } catch (IOException e) {
            //TODO
        }
    }
}
