package labioopint.view;

import javax.swing.*;

import labioopint.controller.impl.MainMenuController;

import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu(MainMenuController controller) {
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
            controller.startGame();
            this.setVisible(false);
        });
        buttonPanel.add(startGameButton);

        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(e -> controller.loadGame());
        buttonPanel.add(loadGameButton);

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> controller.openSettings());
        buttonPanel.add(settingsButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> controller.quitGame());
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
}

