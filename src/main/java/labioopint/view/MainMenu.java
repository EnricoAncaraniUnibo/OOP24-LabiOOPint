package labioopint.view;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import labioopint.controller.api.MainMenuController;
import labioopint.controller.api.MainMenuLogic;
import labioopint.controller.impl.MainMenuLogicImpl;

public class MainMenu extends JFrame {
    private static final long serialVersionUID = 1L;

    private final MainMenuLogic logic;

    public MainMenu(final MainMenuController controller) {
        logic = new MainMenuLogicImpl(controller);
        super.setTitle("Main Menu");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        
        int width = (int) (d.getWidth() * 0.9);
        int height = (int) (d.getHeight() * 0.9);
        super.setSize(width, height);
        super.setResizable(false);
        super.setLocationRelativeTo(null);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension((int)(width * 0.6), (int)(height * 0.8)));
        mainPanel.setMaximumSize(new Dimension((int)(width * 0.8), (int)(height * 0.9)));
        mainPanel.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel titleLabel = new JLabel("LABIOOPINT", JLabel.CENTER);
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        final Dimension buttonSize = new Dimension((int)(width * 0.3), 80);

        final JButton startGameButton = new JButton("Start Game");
        startGameButton.setPreferredSize(buttonSize);
        startGameButton.setMaximumSize(buttonSize);
        startGameButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));
        startGameButton.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(startGameButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        startGameButton.addActionListener(e -> {
            logic.startGame();
            this.setVisible(false);
        });

        final JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setPreferredSize(buttonSize);
        loadGameButton.setMaximumSize(buttonSize);
        loadGameButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));
        loadGameButton.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(loadGameButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loadGameButton.addActionListener(e -> {
            logic.loadGame();
            if (logic.isLoaded()) {
                this.setVisible(false);
            } else {
                showNoFileFound();
            }
        });

        final JButton settingsButton = new JButton("Settings");
        settingsButton.setPreferredSize(buttonSize);
        settingsButton.setMaximumSize(buttonSize);
        settingsButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));
        settingsButton.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(settingsButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        settingsButton.addActionListener(e -> logic.openSettings());

        final JButton quitButton = new JButton("Quit");
        quitButton.setPreferredSize(buttonSize);
        quitButton.setMaximumSize(buttonSize);
        quitButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));
        quitButton.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(quitButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        quitButton.addActionListener(e -> super.dispose());

        super.getContentPane().setLayout(new BoxLayout(super.getContentPane(), BoxLayout.X_AXIS));
        super.add(Box.createHorizontalGlue());
        super.add(mainPanel);
        super.add(Box.createHorizontalGlue());
    }

    private void showNoFileFound() {
        JOptionPane.showMessageDialog(null, "No file found, it's not possible to load the game");
    }
}
