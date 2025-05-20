package labioopint;

import labioopint.view.MainMenu;

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

        final MainMenu menu = new MainMenu();
        menu.open();
    }
}
