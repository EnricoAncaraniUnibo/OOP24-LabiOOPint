package labioopint.view;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import labioopint.controller.api.SettingsController;
import labioopint.controller.impl.SettingsControllerImpl;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

/**
 * The SettingsMenu class represents the settings menu of the application.
 * It allows users to configure game settings such as difficulty, number of
 * players, power-ups, and enemies.
 */
public class SettingsMenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int RATIO_NUMERATOR = 4;       //It's a ratio number for the dimension
    private static final int RATIO_DENOMINATOR = 5;     //of the screen
    private static final int GRID_ROWS = 15;
    private static final int GRID_COLUMNS = 0;
    private static final int GRID_HGAP = 0;
    private static final int GRID_VGAP = 10;
    private static final int TITLE_FONT_SIZE = 25;
    private static final int H_GAP = 20;
    private static final int V_GAP = 20;

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final SettingsController settingsController;

    /**
     * Constructs a new SettingsMenu.
     *
     * @param controller the SettingsController used to manage the settings logic
     */
    public SettingsMenu() {
        settingsController = new SettingsControllerImpl();
        super.setTitle("Settings");
        super.setLayout(new GridLayout(0, 3, H_GAP, V_GAP));
        super.setSize((int) screenSize.getWidth() * RATIO_NUMERATOR / RATIO_DENOMINATOR,
                (int) screenSize.getHeight() * RATIO_NUMERATOR / RATIO_DENOMINATOR);

        final JPanel choosePanel = new JPanel();
        choosePanel.setLayout(new GridLayout(GRID_ROWS, GRID_COLUMNS, GRID_HGAP, GRID_VGAP));

        final JComboBox<String> difficultyComboBox = new JComboBox<>(new String[] { "EASY", "MEDIUM", "HARD" });
        final JSpinner playersSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 4, 1));
        final JSpinner powerUpSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        final JSpinner enemySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1, 1));

        final JButton saveButton = new JButton("Save");
        saveButton.setAlignmentX(CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> {
            if(settingsController.saveNewSettings(
                    (int) enemySpinner.getValue(),
                    (int) playersSpinner.getValue(),
                    (int) powerUpSpinner.getValue(),
                    (String) difficultyComboBox.getSelectedItem())){
                        this.setVisible(false);
                    }
            else{
                JOptionPane.showMessageDialog(this,"Error in saving settings\ntry again");
                this.setVisible(false);
            }
        });

        final JLabel textLabel = new JLabel("Settings");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, TITLE_FONT_SIZE));

        choosePanel.add(textLabel);
        choosePanel.add(new JLabel("Scegli la difficolta' di gioco"));
        choosePanel.add(difficultyComboBox);
        choosePanel.add(new JLabel("Scegli il numero dei giocatori"));
        choosePanel.add(playersSpinner);
        choosePanel.add(new JLabel("Scegli il numero di PowerUp"));
        choosePanel.add(powerUpSpinner);
        choosePanel.add(new JLabel("Scegli il numero dei nemici"));
        choosePanel.add(enemySpinner);
        choosePanel.add(Box.createRigidArea(new Dimension(1, 10)));
        choosePanel.add(Box.createRigidArea(new Dimension(1, 100)));
        choosePanel.add(saveButton);

        choosePanel.setAlignmentX(CENTER_ALIGNMENT);
        super.add(Box.createRigidArea(new Dimension(10, 10)));
        super.add(choosePanel);
    }

    public void open(){
        super.setVisible(true);
    }
}
