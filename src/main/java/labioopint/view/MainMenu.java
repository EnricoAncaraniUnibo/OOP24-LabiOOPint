package labioopint.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import labioopint.controller.api.MainMenuController;
import labioopint.controller.api.MainMenuLogic;
import labioopint.controller.impl.MainMenuLogicImpl;

/**
 * This class represents the main menu of the application.
 * It provides buttons to start a game, load a game, open settings, or quit the
 * application.
 */
public class MainMenu extends JFrame {
    public static final long serialVersionUID = 1L;
    private static final int WIDTH_RATIO = 4;
    private static final int HEIGHT_RATIO = 5;

    private final MainMenuLogic logic;

    /**
     * Constructs a new MainMenu instance.
     *
     * @param controller the main menu controller, must not be null
     */
    public MainMenu(final MainMenuController controller) {
        logic = new MainMenuLogicImpl(controller);
        super.setTitle("Main Menu");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        super.setSize((int) d.getWidth() * WIDTH_RATIO / HEIGHT_RATIO,
                (int) d.getHeight() * WIDTH_RATIO / HEIGHT_RATIO);
        super.setLayout(new BorderLayout());
        super.setResizable(false);
        super.setLocationByPlatform(true);
        super.setTitle("Main Menu");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));

        final JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> {
            logic.startGame();
            this.setVisible(false);
        });
        buttonPanel.add(startGameButton);

        final JButton loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(e -> {
            logic.loadGame();
            if (logic.isLoaded()) {
                this.setVisible(false);
            } else {
                showNoFileFound();
            }
        });
        buttonPanel.add(loadGameButton);

        final JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> logic.openSettings());
        buttonPanel.add(settingsButton);

        final JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> super.dispose());
        buttonPanel.add(quitButton);

        super.add(buttonPanel, BorderLayout.CENTER);
    }

    private void showNoFileFound() {
        JOptionPane.showMessageDialog(null, "No file found, it's not possible to load the game");
    }
}
