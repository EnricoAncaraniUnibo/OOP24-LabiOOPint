package labioopint.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

public class GameView extends JFrame {

    public GameView() {
        this.BuildBase();
        this.BuildPanels();
        this.Show();
    }

    private void BuildBase() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocationByPlatform(true);
        this.setSize((int) d.getWidth() * 4 / 5, (int) d.getHeight() * 4 / 5);
    }

    private void BuildPanels() {
        DrawPanel dp = new DrawPanel();
        dp.setBorder(new TitledBorder("Game"));
        this.getContentPane().add(dp, BorderLayout.CENTER);
    }

    private void Show() {
        this.setVisible(true);
    }

}
