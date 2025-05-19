package labioopint.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import labioopint.controller.api.MainMenuController;
import labioopint.controller.api.MainMenuLogic;
import labioopint.controller.impl.MainMenuLogicImpl;

/**
 * Main menu window for the application.
 */
public class MainMenu extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final double PARAMETER_WIDTH = 0.9;
    private static final double PARAMETER_HEIGHT = 0.9;
    private static final double PREFERRED_SIZE = 0.6;
    private static final double MAXIMUM_SIZE = 0.8;
    private static final double BUTTON_WIDTH_RATIO = 0.3;
    private static final double MAIN_PANEL_HEIGHT_RATIO = 0.8;
    private static final double MAIN_PANEL_MAX_HEIGHT_RATIO = 0.9;
    private static final int BUTTON_HEIGHT = 80;
    private static final int TITLE_FONT_SIZE = 50;
    private static final int BUTTON_FONT_SIZE = 28;
    private static final int TITLE_BOTTOM_SPACING = 40;
    private static final int BUTTON_SPACING = 20;

    private final MainMenuLogic logic;

    /**
     * Constructs the main menu.
     * @param controller the main menu controller
     */
    public MainMenu(final MainMenuController controller) {
        this.logic = new MainMenuLogicImpl(controller);
        super.setTitle("Main Menu");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) (d.getWidth() * PARAMETER_WIDTH);
        final int height = (int) (d.getHeight() * PARAMETER_HEIGHT);
        super.setSize(width, height);
        super.setResizable(false);
        super.setLocationRelativeTo(null);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(
            new Dimension((int) (width * PREFERRED_SIZE), (int) (height * MAIN_PANEL_HEIGHT_RATIO))
        );
        mainPanel.setMaximumSize(
            new Dimension((int) (width * MAXIMUM_SIZE), (int) (height * MAIN_PANEL_MAX_HEIGHT_RATIO))
        );
        mainPanel.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel titleLabel = new JLabel("LABIOOPINT", JLabel.CENTER);
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, TITLE_BOTTOM_SPACING)));

        final Dimension buttonSize = new Dimension((int) (width * BUTTON_WIDTH_RATIO), BUTTON_HEIGHT);

        final JButton startGameButton = new JButton("Start Game");
        startGameButton.setPreferredSize(buttonSize);
        startGameButton.setMaximumSize(buttonSize);
        startGameButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, BUTTON_FONT_SIZE));
        startGameButton.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(startGameButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, BUTTON_SPACING)));
        startGameButton.addActionListener(e -> {
            this.logic.startGame();
            this.setVisible(false);
        });

        final JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setPreferredSize(buttonSize);
        loadGameButton.setMaximumSize(buttonSize);
        loadGameButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, BUTTON_FONT_SIZE));
        loadGameButton.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(loadGameButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, BUTTON_SPACING)));
        loadGameButton.addActionListener(e -> {
            this.logic.loadGame();
            if (this.logic.isLoaded()) {
                this.setVisible(false);
            } else {
                showNoFileFound();
            }
        });

        final JButton settingsButton = new JButton("Settings");
        settingsButton.setPreferredSize(buttonSize);
        settingsButton.setMaximumSize(buttonSize);
        settingsButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, BUTTON_FONT_SIZE));
        settingsButton.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(settingsButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, BUTTON_SPACING)));
        settingsButton.addActionListener(e -> this.logic.openSettings());

        final JButton quitButton = new JButton("Quit");
        quitButton.setPreferredSize(buttonSize);
        quitButton.setMaximumSize(buttonSize);
        quitButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, BUTTON_FONT_SIZE));
        quitButton.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(quitButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, BUTTON_SPACING)));
        quitButton.addActionListener(e -> super.dispose());

        super.getContentPane().setLayout(new BoxLayout(super.getContentPane(), BoxLayout.X_AXIS));
        super.add(Box.createHorizontalGlue());
        super.add(mainPanel);
        super.add(Box.createHorizontalGlue());
    }

    /**
     * Shows a dialog when no file is found to load.
     */
    private void showNoFileFound() {
        JOptionPane.showMessageDialog(
            null,
            "No file found, it's not possible to load the game"
        );
    }
}
