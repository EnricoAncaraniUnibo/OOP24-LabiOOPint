package labioopint.view;

import javax.swing.*;

import labioopint.controller.impl.MainMenuController;
import labioopint.controller.impl.MainMenuLogic;

import java.awt.*;

public class MainMenu extends JFrame {

    private final MainMenuLogic logic;

    public MainMenu(MainMenuController controller) {
        logic = new MainMenuLogic(controller);
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) d.getWidth() * 4 / 5, (int) d.getHeight() * 4 / 5);
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
        loadGameButton.addActionListener(e -> logic.loadGame());
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

