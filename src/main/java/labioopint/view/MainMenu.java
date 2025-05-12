package labioopint.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import labioopint.controller.impl.MainMenuController;
import labioopint.controller.impl.MainMenuLogic;

/**
 * This class represents the main menu of the application.
 * It provides buttons to start a game, load a game, open settings, or quit the application.
 */
public class MainMenu extends JFrame {

    private static final int WIDTH_RATIO = 4;
    private static final int HEIGHT_RATIO = 5;

    private final MainMenuLogic logic;

    /**
     * Constructs a new MainMenu instance.
     *
     * @param controller the main menu controller, must not be null
     */
    public MainMenu(final MainMenuController controller) {
        logic = new MainMenuLogic(controller);
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) d.getWidth() * WIDTH_RATIO / HEIGHT_RATIO, (int) d.getHeight() * WIDTH_RATIO / HEIGHT_RATIO);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationByPlatform(true);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> {
            logic.startGame();
            this.setVisible(false);
        });
        buttonPanel.add(startGameButton);

        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(e -> {
            logic.loadGame();
            this.setVisible(false);
        });
        buttonPanel.add(loadGameButton);

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> logic.openSettings());
        buttonPanel.add(settingsButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> logic.quitGame());
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
}
