package labioopint.model.core.impl;

import labioopint.controller.api.MainMenuController;
import labioopint.controller.impl.MainMenuControllerImpl;

/**
 * This class serves as the entry point for the application.
 */
public final class Starter {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Starter() {
        // Prevent instantiation
    }

    /**
     * The main method to start the application.
     *
     * @param args the command-line arguments, must not be null
     */
    public static void main(final String[] args) {

        final MainMenuController controller = new MainMenuControllerImpl();
        controller.load();
    }
}
