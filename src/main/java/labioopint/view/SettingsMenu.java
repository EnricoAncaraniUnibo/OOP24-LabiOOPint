package labioopint.view;

import javax.swing.*;

import labioopint.controller.impl.SettingsController;
import labioopint.controller.impl.SettingsLogic;

import java.awt.*;

public class SettingsMenu extends JFrame {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int RATIO_NUMERATOR = 4;
    private static final int RATIO_DENOMINATOR = 5;
    private final SettingsLogic settingsLogic;

    public SettingsMenu(SettingsController controller) {
        settingsLogic = new SettingsLogic(controller);
        super.setTitle("Settings");
        //super.setDefaultCloseOperation();
        super.setLayout(new GridLayout(0,3,20,20));
        super.setSize((int) screenSize.getWidth() * RATIO_NUMERATOR / RATIO_DENOMINATOR,
                      (int) screenSize.getHeight() * RATIO_NUMERATOR / RATIO_DENOMINATOR);

        JPanel choosePanel = new JPanel();
        choosePanel.setLayout(new GridLayout(15,0,0,10));
        JComboBox<String> difficultyComboBox = new JComboBox<>(new String[]{"EASY", "MEDIUM", "HARD"});
        difficultyComboBox.setPreferredSize(new Dimension(40,10));
        JSpinner playersSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 4, 1));
        JSpinner powerUpSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        JSpinner enemySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1, 1));
        
        JButton saveButton = new JButton("Save");
        saveButton.setAlignmentX(CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> settingsLogic.saveNewSettings(
            (int) enemySpinner.getValue(),
            (int) playersSpinner.getValue(),
            (int) powerUpSpinner.getValue(),
            (String) difficultyComboBox.getSelectedItem()
        ));

        //JPanel settingsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        
        JLabel textLabel = new JLabel("Settings");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,25));

        choosePanel.add(textLabel);
        choosePanel.add(new JLabel("Scegli la difficolta' di gioco"));
        choosePanel.add(difficultyComboBox);
        //textLabel.setHorizontalAlignment(SwingConstants.LEFT);
        //textLabel.setText("Scegli il numero dei giocatori:");
        //textLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,15));
        choosePanel.add(new JLabel("Scegli il numero dei giocatori"));
        choosePanel.add(playersSpinner);
        //textLabel.setText("Scegli il numero di power");
        choosePanel.add(new JLabel("Scegli il numero di PowerUp"));
        choosePanel.add(powerUpSpinner);
        //textLabel.setText("Scegli il numero di nemici");
        choosePanel.add(new JLabel("Scegli il numero dei nemici"));
        choosePanel.add(enemySpinner);
        choosePanel.add(Box.createRigidArea(new Dimension(1,10)));
        choosePanel.add(Box.createRigidArea(new Dimension(1,100)));
        choosePanel.add(saveButton);

        choosePanel.setAlignmentX(CENTER_ALIGNMENT);
        super.add(Box.createRigidArea(new Dimension(10,10)));
        super.add(choosePanel);
    }
}