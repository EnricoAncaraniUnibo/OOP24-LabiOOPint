package labioopint.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame {

    private JLabel turnLabel;
    private JPanel labirintPanel;
    // BISOGNA MODIFICARLI APPENA ARRIVANO GLI ID DEI PLAYER DAL
    // PlayerInformationController
    private int currentPlayer = 1;
    private int totalPlayers = 2;

    public GameView() {
        setTitle("LabiOPPint");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        setResizable(false);
        labirintPanel = new JPanel();
        labirintPanel.setBackground(Color.BLACK);
        add(labirintPanel, BorderLayout.CENTER);
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setPreferredSize(new Dimension(200, 600));
        controlPanel.setBackground(Color.GRAY);
        add(controlPanel, BorderLayout.EAST);

        turnLabel = new JLabel("It's turn of Player 1", SwingConstants.CENTER);
        Font newFont = new Font("Arial", Font.BOLD, 18);
        turnLabel.setFont(newFont);
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(turnLabel);

        JPanel movementPanel = new JPanel(new GridBagLayout());
        movementPanel.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        movementPanel.add(createButton("↑"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        movementPanel.add(createButton("←"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        movementPanel.add(createButton("→"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        movementPanel.add(createButton("↓"), gbc);

        controlPanel.add(movementPanel);

        String[] options = { "Name_PowerUp1", "Name_PowerUp2", "Name_PowerUp3", "Name_PowerUp4", "Name_PowerUp5",
                "Name_PowerUp6" };
        JComboBox<String> comboBox = new JComboBox<>(options);
        controlPanel.add(comboBox);
        comboBox.addActionListener(
                e -> JOptionPane.showMessageDialog(this, "Hai selezionato: " + (String) comboBox.getSelectedItem()));

        JButton powerUpButton = new JButton("Use PowerUp");
        powerUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(powerUpButton);
        powerUpButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "PowerUp usato!"));

        JButton endTurnButton = new JButton("End Turn");
        endTurnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(endTurnButton);
        endTurnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentPlayer = (currentPlayer % totalPlayers) + 1; // Incrementa e resetta a 1 dopo Player 4
                turnLabel.setText("It's turn of Player " + currentPlayer);
            }

        });

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hai premuto " + text);
            }
        });
        return button;
    }

}
