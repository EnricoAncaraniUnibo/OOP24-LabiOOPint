package labioopint.view;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.api.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{

    private static Settings settings;

    public MainMenu() {

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
        startGameButton.addActionListener(e -> TurnManager.Init(SettingsMenu.getSettings()));
        buttonPanel.add(startGameButton);

        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(e -> loadGame());
        buttonPanel.add(loadGameButton);

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> new SettingsMenu().setVisible(true));
        buttonPanel.add(settingsButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
}

