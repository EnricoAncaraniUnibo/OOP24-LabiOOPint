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
    public void save(final GameController gc) {
        File file = new File("src/main/java/labioopint/saving/lastGame.txt");
        try (FileOutputStream fos = new FileOutputStream(file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(gc);

        } catch (IOException e) {
            // TODO
        }
    }
}
