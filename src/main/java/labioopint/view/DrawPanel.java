package labioopint.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Maze.impl.MazeImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.DualMap;
import labioopint.model.api.Pair;
/**
 * The DrawPanel class is responsible for rendering the labyrinth (maze) and its elements,
 * such as blocks, players, enemies, and power-ups, on a graphical panel.
 * It extends the JPanel class and uses the Graphics2D API for drawing.
 */
public class DrawPanel extends JPanel {
        private LogicDrawPanel ldp;
        
        public DrawPanel(final Dimension size, TurnManager tu) {
                ldp = new LogicDrawPanel(tu, size);
        }

        public void draw(final MazeImpl m, final DualMap<PlayerImpl> mapPlayers,
                final DualMap<Enemy> mapEnemies, final DualMap<PowerUp> mapPowerUps,
                final BlockImpl outside) {
                ldp.updateData(m, mapPlayers, mapEnemies, mapPowerUps, outside);
                repaint();
        }

        protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                AffineTransform old = g2.getTransform();
                for (Pair<Pair<Image,Double>,Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>> element : ldp.getImagesBlocks()) {
                        g2.rotate(element.getFirst().getSecond());
                        g2.drawImage(element.getFirst().getFirst(), element.getSecond().getFirst().getFirst(), element.getSecond().getFirst().getSecond(),element.getSecond().getSecond().getFirst(),element.getSecond().getSecond().getSecond(), this);   
                        g2.setTransform(old);       
                }
        }

        public Integer getBlockSize(){
                return ldp.getPixelSize();
        }
}
