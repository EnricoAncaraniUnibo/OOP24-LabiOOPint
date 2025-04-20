package labioopint.view;

import javax.swing.*;

import labioopint.controller.SettingsController;
import java.awt.*;

public class SettingsMenu extends JFrame {

    public SettingsMenu(SettingsController controller) {
        setTitle("Settings");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JComboBox<String> difficultyComboBox = new JComboBox<>(new String[]{"EASY", "MEDIUM", "HARD"});
        JSpinner playersSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 4, 1));
        JSpinner powerUpSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        JSpinner enemySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1, 1))

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> controller.saveSettings(
            (Integer) enemySpinner.getValue(),
            (Integer) playersSpinner.getValue(),
            (Integer) powerUpSpinner.getValue(),
            (String) difficultyComboBox.getSelectedItem()
        ));

        JPanel settingsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        settingsPanel.add(new JLabel("Enemy difficulty:"));
        settingsPanel.add(difficultyComboBox);
        settingsPanel.add(new JLabel("Number of players:"));
        settingsPanel.add(playersSpinner);
        settingsPanel.add(new JLabel("Number of power-ups:"));
        settingsPanel.add(powerUpSpinner);
        settingsPanel.add(new JLabel("Enemy:"));
        settingsPanel.add(enemySpinner);

        add(settingsPanel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }
}