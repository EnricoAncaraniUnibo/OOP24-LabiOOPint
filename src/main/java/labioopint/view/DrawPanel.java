package labioopint.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

import labioopint.model.api.DualMap;
import labioopint.model.api.Pair;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.impl.MazeImpl;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;

/**
 * The DrawPanel class is responsible for rendering the labyrinth (maze) and its
 * elements,
 * such as blocks, players, enemies, and power-ups, on a graphical panel.
 * It extends the JPanel class and uses the Graphics2D API for drawing.
 */
public final class DrawPanel extends JPanel {
        private LogicDrawPanel ldp;

        /**
         * Constructs a DrawPanel with the specified panel size and TurnManager.
         *
         * @param size the Dimension of the panel
         * @param tu   the TurnManager instance to manage turns
         */
        public DrawPanel(final Dimension size, final TurnManager tu) {
                ldp = new LogicDrawPanel(tu, size);
        }

        /**
         * Updates the data required for drawing the panel and triggers a repaint.
         *
         * @param m           the MazeImpl instance representing the maze
         * @param mapPlayers  the DualMap containing player coordinates
         * @param mapEnemies  the DualMap containing enemy coordinates
         * @param mapPowerUps the DualMap containing power-up coordinates
         * @param outside     the BlockImpl representing the outside block
         */
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
                for (Pair<Pair<Image, Double>, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> element : ldp
                                .getImagesBlocks()) {
                        g2.rotate(element.getFirst().getSecond());
                        g2.drawImage(element.getFirst().getFirst(), element.getSecond().getFirst().getFirst(),
                                        element.getSecond().getFirst().getSecond(),
                                        element.getSecond().getSecond().getFirst(),
                                        element.getSecond().getSecond().getSecond(), this);
                        g2.setTransform(old);
                }
        }

        public Integer getBlockSize() {
                return ldp.getPixelSize();
        }
}
