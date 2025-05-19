package labioopint.controller.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;

import labioopint.controller.api.GameController;
import labioopint.controller.api.SaveController;

/**
 * Implementation of the class SaveController used to save the current game
 * state in a file.
 */
public final class SaveControllerImpl implements SaveController {
    public static final long serialVersionUID = 1L;

    @Override
    public boolean save(final GameController gc) {
        try {
            final FileOutputStream fos = new FileOutputStream(new File("src/main/java/labioopint/saving/lastGame.txt"),
                    false);
            final ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gc);
            oos.close();
            fos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
