package labioopint.controller.impl;

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
        try {
            MainMenuController controller = new MainMenuController();
            controller.load();
        } catch (Exception e) {
            // Handle exception (optional: log or rethrow)
        }
    }
}
