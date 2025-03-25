package labioopint.view;

import javax.swing.*;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.api.DualMap;
import labioopint.model.maze.impl.Block;import labioopint.model.maze.impl.PowerUp;
import labioopint.model.maze.impl.Maze;
import labioopint.model.player.impl.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame {

    private JLabel turnLabel;
    private DrawPanel labirintPanel;
    private int currentPlayer = 1;
    private final int totalPlayers = 4;

    public GameView() {
        setTitle("LabiOPPint");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) d.getWidth() * 4 / 5, (int) d.getHeight() * 4 / 5);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationByPlatform(true);

        labirintPanel = new DrawPanel(this.getSize());
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

        JButton rotation = new JButton("Rotation");
        rotation.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(rotation);

        String[] options = { "Name_PowerUp1", "Name_PowerUp2", "Name_PowerUp3", "Name_PowerUp4", "Name_PowerUp5",
                "Name_PowerUp6" };
        JComboBox<String> comboBox = new JComboBox<>(options);
        controlPanel.add(comboBox);

        // Aggiunta un ActionListener alla ComboBox per gestire l'evento di selezione
        comboBox.addActionListener(
                e -> JOptionPane.showMessageDialog(this, "Hai selezionato: " + (String) comboBox.getSelectedItem()));

        JButton powerUpButton = new JButton("Use PowerUp");
        powerUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(powerUpButton);

        JButton endTurnButton = new JButton("End Turn");
        endTurnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(endTurnButton);

        powerUpButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "PowerUp usato!"));

        endTurnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentPlayer = (currentPlayer % totalPlayers) + 1; // Incrementa e resetta a 1 dopo Player 4
                // currentPlayer = PlayerInformationController.getId();
                turnLabel.setText("It's turn of Player " + currentPlayer);
            }

        });

        rotation.addActionListener(e -> JOptionPane.showMessageDialog(this, "Rotation"));

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hai premuto " + text);
                if(text.equals("←")){
                    //GameController.MoveBlock(Direction.LEFT, blocco, TurnMenager.GetLab());
                }
            }
        });
        return button;
    }

    public void update(final Maze grid, final DualMap<Player> mapPlayers, final DualMap<Enemy> mapEnemies, final DualMap<PowerUp> mapPowerUps,Block outside) {
        labirintPanel.draw(grid,mapPlayers,mapEnemies,mapPowerUps,outside);
    }
}
