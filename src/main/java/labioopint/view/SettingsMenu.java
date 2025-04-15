package labioopint.view;

import javax.swing.*;

import labioopint.model.Enemy.api.EnemyDifficulty;
import labioopint.model.api.Settings;
import java.awt.*;

public class SettingsMenu extends JFrame {

    private static Settings settings;

    public SettingsMenu() {

        setTitle("Settings");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) d.getWidth() * 4 / 5, (int) d.getHeight() * 4 / 5);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationByPlatform(true);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(3, 2, 10, 10));

        settingsPanel.add(new JLabel("Enemy difficulty:"));
        JComboBox<String> difficultyComboBox = new JComboBox<>(new String[]{"EASY", "MEDIUM", "HARD"});
        settingsPanel.add(difficultyComboBox);

        settingsPanel.add(new JLabel("Number of players:"));
        JSpinner playersSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 4, 1));
        settingsPanel.add(playersSpinner);

        settingsPanel.add(new JLabel("Number of power-ups:"));
        JSpinner powerUpSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        settingsPanel.add(powerUpSpinner);

        settingsPanel.add(new JLabel("Enemy:"));
        JSpinner enemySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1, 1));
        settingsPanel.add(enemySpinner);

        add(settingsPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            settings = new Settings(
                (Integer) enemySpinner.getValue(),
                (Integer) playersSpinner.getValue(),
                (Integer) powerUpSpinner.getValue(),
                EnemyDifficulty.valueOf((String) difficultyComboBox.getSelectedItem())
            );
            dispose();
        });
        add(saveButton, BorderLayout.SOUTH);
    }

    public static Settings getSettings() {
        return settings;
    }
}