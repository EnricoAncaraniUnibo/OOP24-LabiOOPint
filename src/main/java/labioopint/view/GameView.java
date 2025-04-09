package labioopint.view;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import labioopint.controller.impl.GameController;
import labioopint.controller.impl.InformationMessenger;
import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Maze.impl.MazeImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.ActionType;
import labioopint.model.api.Coordinate;
import labioopint.model.api.DualMap;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GameView extends JFrame {

    private JLabel turnLabel;
    private DrawPanel labirintPanel;
    //private PlayerImpl currentPlayer = TurnManager.GetCurrentPlayer();
    JButton upButton; 
    JButton leftButton;
    JButton rightButton;
    JButton downButton;
    JLabel actionLabel;
    JButton endTurnButton;
    JComboBox<String> comboBox;

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

        turnLabel = new JLabel(InformationMessenger.getTurn(), SwingConstants.CENTER);
        Font newFont = new Font("Arial", Font.BOLD, 18);
        turnLabel.setFont(newFont);
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(turnLabel);

        actionLabel = new JLabel(InformationMessenger.getAction(), SwingConstants.CENTER);
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

        String[] options = InformationMessenger.getPowerUpsList();
        comboBox = new JComboBox<>(options);
        controlPanel.add(comboBox);

        // Aggiunta un ActionListener alla ComboBox per gestire l'evento di selezione
        //comboBox.addActionListener(
        //        e -> JOptionPane.showMessageDialog(this, "Hai selezionato: " + (String) comboBox.getSelectedItem()));

        JButton powerUpButton = new JButton("Use PowerUp");
        powerUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(powerUpButton);

        endTurnButton = new JButton("End Turn");
        endTurnButton.setVisible(false);
        endTurnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(endTurnButton);

        powerUpButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String)comboBox.getSelectedItem();
                for (PowerUp pu : TurnManager.GetPowerUps()) {
                    if(pu.getName().equals(name)) {
                        pu.activate(TurnManager.GetCurrentPlayer());
                    }
                }
                updateComboBox();
            }
        });

        endTurnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameController.action(endTurnButton.getText());
                turnLabel.setText(InformationMessenger.getTurn());
                String[] options = InformationMessenger.getPowerUpsList();
                comboBox.removeAllItems();
                for (String option : options) {
                    comboBox.addItem(option);
                }
                endTurnButton.setVisible(false);
                upButton.setVisible(false);
                downButton.setVisible(false);
                actionLabel.setText(InformationMessenger.getAction());
            }

        });

        
        this.addMouseListener(new MouseInputListener() {
        
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int X = e.getX();
                int Y = e.getY();
                int blockSize = DrawPanel.getBlockSize();
                Coordinate newCoordinate = new Coordinate((Y % blockSize < blockSize/2) ? Y/blockSize-1 : Y/blockSize,
                                                          X/blockSize);
                GameController.action(newCoordinate);
                actionLabel.setText(InformationMessenger.getAction());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        });

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
         
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameController.action(text);
                updateComboBox();
            }
        });
        return button;
    }
    
    private void updateComboBox() {
    	String[] options = InformationMessenger.getPowerUpsList();
        comboBox.removeAllItems();
        for (String option : options) {
            comboBox.addItem(option);
        }
    }

    public void update(final MazeImpl grid, final DualMap<PlayerImpl> mapPlayers, final DualMap<Enemy> mapEnemies, final DualMap<PowerUp> mapPowerUps,BlockImpl outside) {
        if(TurnManager.GetCurrentAction() == ActionType.BLOCK_PLACEMENT) {
        	endTurnButton.setVisible(false);
            upButton.setVisible(false);
            downButton.setVisible(false);
        } else {
        	endTurnButton.setVisible(true);
            upButton.setVisible(true);
            downButton.setVisible(true);
        }
        labirintPanel.draw(grid,mapPlayers,mapEnemies,mapPowerUps,outside);
    }
}
