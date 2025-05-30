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

import labioopint.controller.api.GameController;
import labioopint.controller.api.LogicGameView;
import labioopint.controller.impl.LogicGameViewImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Represents the main game view for the application.
 * The `GameView` class is responsible for rendering the game interface, including the game board,
 * control panel, and handling user interactions such as button clicks and mouse events.
 */
public class GameView extends JFrame {

    public static final long serialVersionUID = 1L;
    private final JLabel turnLabel;
    private DrawPanel labirintPanel;
    private final LogicGameView lgv;
    private final JButton upButton;
    private final JButton downButton;
    private final JLabel actionLabel;
    private final JLabel scoreLabel;
    private final JButton endTurnButton;
    private final JComboBox<String> comboBox;
    private static final int RATIO_NUMERATOR = 4;
    private static final int RATIO_DENOMINATOR = 5;
    private static final int CONTROL_PANEL_WIDTH = 200;
    private static final int CONTROL_PANEL_HEIGHT = 600;
    private static final int TEXT_SIZE = 18;

    /**
     * Constructs a new `GameView` with the specified game controller.
     * Initializes the game interface, including the game board and control panel.
     *
     * @param gc the {@link GameController} used to manage game logic and interactions
     */
    public GameView(final GameController gc) {
        super.setTitle("LabiOPPint");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        super.setSize((int) d.getWidth() * RATIO_NUMERATOR / RATIO_DENOMINATOR,
                (int) d.getHeight() * RATIO_NUMERATOR / RATIO_DENOMINATOR);
        super.setLayout(new BorderLayout());
        super.setResizable(false);
        super.setLocationByPlatform(true);
        lgv = new LogicGameViewImpl(gc);
        try {
            labirintPanel = new DrawPanel(super.getSize(), gc);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error Loading");
        }
        super.add(labirintPanel, BorderLayout.CENTER);
        final JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setPreferredSize(new Dimension(CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT));
        controlPanel.setBackground(Color.GRAY);
        super.add(controlPanel, BorderLayout.EAST);

        turnLabel = new JLabel(lgv.getTurn(), SwingConstants.CENTER);
        final Font newFont = new Font("Arial", Font.BOLD, TEXT_SIZE);
        turnLabel.setFont(newFont);
        turnLabel.setAlignmentX(CENTER_ALIGNMENT);
        controlPanel.add(turnLabel);

        actionLabel = new JLabel(lgv.getAction(), SwingConstants.CENTER);
        actionLabel.setFont(newFont);
        actionLabel.setAlignmentX(CENTER_ALIGNMENT);
        controlPanel.add(actionLabel);

        scoreLabel = new JLabel("<html>" + lgv.getScores() + "</html>", SwingConstants.CENTER);
        scoreLabel.setFont(newFont);
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        controlPanel.add(scoreLabel);

        upButton = createButton("↑");
        final JButton leftButton = createButton("←");
        final JButton rightButton = createButton("→");
        downButton = createButton("↓");
        upButton.setVisible(false);
        downButton.setVisible(false);

        final JPanel movementPanel = new JPanel(new GridBagLayout());
        movementPanel.setBackground(Color.GRAY);
        final GridBagConstraints gbc = new GridBagConstraints();
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

        final JButton powerUpButton = new JButton("Use PowerUp");
        powerUpButton.setAlignmentX(CENTER_ALIGNMENT);
        controlPanel.add(powerUpButton);

        endTurnButton = new JButton("End Turn");
        endTurnButton.setVisible(false);
        endTurnButton.setAlignmentX(CENTER_ALIGNMENT);
        controlPanel.add(endTurnButton);

        powerUpButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final String name = (String) comboBox.getSelectedItem();
                lgv.useAction(name);
                updateComboBox();
                updateScoreLabel();
                update();
            }
        });

        endTurnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final String action = endTurnButton.getText();
                lgv.useAction(action);
                turnLabel.setText(lgv.getTurn());
                final String[] options = lgv.getPowerUps();
                comboBox.removeAllItems();
                for (final String option : options) {
                    comboBox.addItem(option);
                }
                updateScoreLabel();
                endTurnButton.setVisible(false);
                upButton.setVisible(false);
                downButton.setVisible(false);
                actionLabel.setText(lgv.getAction());
                updateScoreLabel();
                update();
            }

        });

        super.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(final MouseEvent e) {
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                lgv.mouseAction(e.getX(), e.getY(), labirintPanel.getBlockSize());
                update();
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
        update();
        super.setVisible(true);
    }

    /**
     * Creates a JButton with the specified text and attaches an ActionListener
     * to handle button actions.
     *
     * @param text the text to display on the button.
     * @return the created JButton.
     */
    private JButton createButton(final String text) {
        final JButton button = new JButton(text);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                lgv.useAction(text);
                updateComboBox();
                updateScoreLabel();
                showWinner();
                update();
            }
        });
        return button;
    }

    /**
     * Updates the ComboBox with the available power-ups from the LogicGameView.
     */
    private void updateComboBox() {
        final String[] options = lgv.getPowerUps();
        comboBox.removeAllItems();
        for (final String option : options) {
            comboBox.addItem(option);
        }
    }
    /**
     * Updates the score label with the current scores from the {@link LogicGameView}.
     */
    private void updateScoreLabel() {
        scoreLabel.setText("<html>" + lgv.getScores() + "</html>");
    }
    /**
     * Updates the game view based on the current game state.
     * This includes showing or hiding buttons and triggering a repaint of the game board.
     */
    public final void update() {
        if (lgv.isBlockPlacement()) {
            endTurnButton.setVisible(false);
            upButton.setVisible(false);
            downButton.setVisible(false);
        } else {
            endTurnButton.setVisible(true);
            upButton.setVisible(true);
            downButton.setVisible(true);
        }
        labirintPanel.draw();
    }

    /**
     * Displays a dialog box showing the winner of the game if a winner is present.
     */
    private void showWinner() {
        if (lgv.isWinnerPresent()) {
            JOptionPane.showMessageDialog(null, lgv.getWinner());
            super.dispose();
        }
    }
}
