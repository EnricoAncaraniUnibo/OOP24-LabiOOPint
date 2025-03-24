package labioopint.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

import labioopint.TempClass.Enemy;
import labioopint.TempClass.PowerUp;
import labioopint.model.api.Coordinate;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Maze;
import labioopint.model.player.impl.Player;

public class GameTestView extends JFrame {
    private DrawPanel dp;

    public GameTestView() {
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
        dp = new DrawPanel(this.getSize());
        dp.setBorder(new TitledBorder("Game"));
        this.getContentPane().add(dp, BorderLayout.CENTER);
    }

    public void update(final Maze grid, final Map<Coordinate,Player> mapPlayers, final Map<Coordinate,Enemy> mapEnemies, final Map<Coordinate,PowerUp> mapPowerUps,Block outside) {
        dp.draw(grid,mapPlayers,mapEnemies,mapPowerUps,outside);
    }
    

    public DrawPanel getGraphicsArea() {
        return dp;
    }

    private void Show() {
        this.setVisible(true);
    }

}
