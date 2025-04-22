package labioopint.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputListener;

import labioopint.model.api.DualMap;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.impl.MazeImpl;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * The GameView class represents the graphical user interface for the game.
 * It extends JFrame and provides components for interacting with the game,
 * such as buttons, labels, and panels.
 */
public class GameView extends JFrame {

    private JLabel turnLabel;
    private DrawPanel labirintPanel;
    private LogicGameView lgv;
    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
    private JLabel actionLabel;
    private JButton endTurnButton;
    private JComboBox<String> comboBox;
    private static final int RATIO_NUMERATOR = 4;
    private static final int RATIO_DENOMINATOR = 5;
    private static final int CONTROL_PANEL_WIDTH = 200;
    private static final int CONTROL_PANEL_HEIGHT = 600;
    private static final int TEXT_SIZE = 18;

    /**
     * Constructs a new GameView instance.
     *
     * @param tu the TurnManager instance used to manage the game's turns.
     */
    public GameView(final TurnManager tu) {
        setTitle("LabiOPPint");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) d.getWidth() * RATIO_NUMERATOR / RATIO_DENOMINATOR,
                (int) d.getHeight() * RATIO_NUMERATOR / RATIO_DENOMINATOR);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationByPlatform(true);
        lgv = new LogicGameView(tu);
        labirintPanel = new DrawPanel(this.getSize(), tu);
        add(labirintPanel, BorderLayout.CENTER);
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setPreferredSize(new Dimension(CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT));
        controlPanel.setBackground(Color.GRAY);
        add(controlPanel, BorderLayout.EAST);

        turnLabel = new JLabel(lgv.getTurn(), SwingConstants.CENTER);
        Font newFont = new Font("Arial", Font.BOLD, TEXT_SIZE);
        turnLabel.setFont(newFont);
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(turnLabel);

        actionLabel = new JLabel(lgv.getAction(), SwingConstants.CENTER);
        actionLabel.setFont(newFont);
        actionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(actionLabel);

        upButton = createButton("↑");
        leftButton = createButton("←");
        rightButton = createButton("→");
        downButton = createButton("↓");
        upButton.setVisible(false);
        downButton.setVisible(false);

        JPanel movementPanel = new JPanel(new GridBagLayout());
        movementPanel.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        movementPanel.add(upButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        movementPanel.add(leftButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        movementPanel.add(rightButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        movementPanel.add(downButton, gbc);

        controlPanel.add(movementPanel);

        comboBox = new JComboBox<>(lgv.getPowerUps());
        controlPanel.add(comboBox);

        // Aggiunta un ActionListener alla ComboBox per gestire l'evento di selezione
        // comboBox.addActionListener(
        // e -> JOptionPane.showMessageDialog(this, "Hai selezionato: " + (String)
        // comboBox.getSelectedItem()));

        JButton powerUpButton = new JButton("Use PowerUp");
        powerUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(powerUpButton);

        endTurnButton = new JButton("End Turn");
        endTurnButton.setVisible(false);
        endTurnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(endTurnButton);

        powerUpButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                String name = (String) comboBox.getSelectedItem();
                lgv.activatePowerUps(name);
                updateComboBox();
            }
        });

        endTurnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                String action = endTurnButton.getText();
                lgv.useAction(action);
                turnLabel.setText(lgv.getTurn());
                String[] options = lgv.getPowerUps();
                comboBox.removeAllItems();
                for (String option : options) {
                    comboBox.addItem(option);
                }
                endTurnButton.setVisible(false);
                upButton.setVisible(false);
                downButton.setVisible(false);
                actionLabel.setText(lgv.getAction());
            }

        });

        this.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(final MouseEvent e) {
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                lgv.mouseAction(e.getX(), e.getY(), labirintPanel.getBlockSize());
                actionLabel.setText(lgv.getAction());
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
            }

            @Override
            public void mouseExited(final MouseEvent e) {
            }

            @Override
            public void mouseDragged(final MouseEvent e) {
            }

            @Override
            public void mouseMoved(final MouseEvent e) {
            }

        });

        setVisible(true);
    }

    /**
     * Creates a JButton with the specified text and attaches an ActionListener
     * to handle button actions.
     *
     * @param text the text to display on the button.
     * @return the created JButton.
     */
    private JButton createButton(final String text) {
        JButton button = new JButton(text);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                lgv.useAction(text);
                updateComboBox();
                showWinner();
            }
        });
        return button;
    }

    /**
     * Updates the ComboBox with the available power-ups from the LogicGameView.
     */
    private void updateComboBox() {
        String[] options = lgv.getPowerUps();
        comboBox.removeAllItems();
        for (String option : options) {
            comboBox.addItem(option);
        }
    }

    /**
     * Updates the game view with the current state of the maze, players, enemies,
     * power-ups, and the outside block.
     *
     * @param grid        the current maze grid.
     * @param mapPlayers  the map of players in the game.
     * @param mapEnemies  the map of enemies in the game.
     * @param mapPowerUps the map of power-ups in the game.
     * @param outside     the block representing the outside of the maze.
     */
    public void update(final MazeImpl grid, final DualMap<PlayerImpl> mapPlayers, final DualMap<Enemy> mapEnemies,
            final DualMap<PowerUp> mapPowerUps, final BlockImpl outside) {
        if (lgv.isBlockPlacement()) {
            endTurnButton.setVisible(false);
            upButton.setVisible(false);
            downButton.setVisible(false);
        } else {
            endTurnButton.setVisible(true);
            upButton.setVisible(true);
            downButton.setVisible(true);
        }
        labirintPanel.draw(grid, mapPlayers, mapEnemies, mapPowerUps, outside);
    }

    /**
     * Displays a dialog box showing the winner of the game if a winner is present.
     */
    private void showWinner() {
        if (lgv.isWinnerPresent()) {
            JOptionPane.showMessageDialog(null, lgv.getWinner());
            lgv.close();
        }
    }
}
