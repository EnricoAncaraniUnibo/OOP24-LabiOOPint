package labioopint.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import labioopint.model.api.Coordinate;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Maze;
import labioopint.model.player.impl.Player;

public class DrawPanel extends JPanel {
        private final Integer pixelSize;
        private Maze maze;
        private Map<Coordinate, Player> coorPlayers;
        private Map<Coordinate, Enemy> coorEnemies;
        private Map<Coordinate, PowerUp> coorPowerUps;
        private Block outsideBlock;
        private final static Color NO_STREET_COLOR = new Color(88, 57, 39);
        private final static Color STREET_COLOR = new Color(255, 200, 0);

        public DrawPanel(final Dimension size) {
                pixelSize = (int) size.getWidth() / 17;
        }

        public void draw(final Maze m, final Map<Coordinate, Player> mapPlayers,
                final Map<Coordinate, Enemy> mapEnemies, final Map<Coordinate, PowerUp> mapPowerUps,
                final Block outside) {
                this.maze = m;
                this.coorPlayers = mapPlayers;
                this.coorEnemies = mapEnemies;
                this.coorPowerUps = mapPowerUps;
                this.outsideBlock = outside;
                repaint();
        }

        protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Block b;
                boolean end = false;
                for (int i = 0; i <= maze.getSize(); i++) {
                        for (int j = 0; j < maze.getSize() && end==false; j++) {
                                if(i == maze.getSize()) {
                                        b = outsideBlock;
                                        i = pixelSize*(maze.getSize()+2);
                                        end=true;
                                } else {
                                        b = maze.GetBlock(new Coordinate(i, j));
                                }
                                switch (b.getType()) {
                                        case CORNER:
                                                switch (b.getRotation()) {
                                                        case ZERO:
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize, pixelSize,
                                                                                (pixelSize / 3));
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize,
                                                                                (pixelSize / 3),
                                                                                pixelSize);
                                                                g.setColor(STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3,
                                                                                j * pixelSize + pixelSize
                                                                                                + pixelSize / 3,
                                                                                (pixelSize - pixelSize / 3),
                                                                                (pixelSize / 3));
                                                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3,
                                                                                j * pixelSize + pixelSize
                                                                                                + pixelSize / 3,
                                                                                (pixelSize / 3),
                                                                                (pixelSize - pixelSize / 3));
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize
                                                                                + (pixelSize - pixelSize / 3),
                                                                                j * pixelSize + pixelSize
                                                                                                + (pixelSize - pixelSize
                                                                                                                / 3),
                                                                                (pixelSize / 3),
                                                                                (pixelSize / 3));
                                                                break;
                                                        case NINETY:
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize, pixelSize,
                                                                                (pixelSize / 3));
                                                                g.fillRect(i * pixelSize + pixelSize
                                                                                + (pixelSize - pixelSize / 3),
                                                                                j * pixelSize + pixelSize,
                                                                                (pixelSize / 3),
                                                                                pixelSize);
                                                                g.setColor(STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize
                                                                                                + pixelSize / 3,
                                                                                pixelSize - (pixelSize / 3),
                                                                                (pixelSize / 3));
                                                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3,
                                                                                j * pixelSize + pixelSize
                                                                                                + pixelSize / 3,
                                                                                (pixelSize / 3),
                                                                                (pixelSize - pixelSize / 3));
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize
                                                                                                + (pixelSize - pixelSize
                                                                                                                / 3),
                                                                                (pixelSize / 3),
                                                                                (pixelSize / 3));
                                                                break;
                                                        case ONE_HUNDRED_EIGHTY:
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize
                                                                                + (pixelSize - pixelSize / 3),
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize / 3,
                                                                                pixelSize);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize
                                                                                                + (pixelSize - pixelSize
                                                                                                                / 3),
                                                                                pixelSize,
                                                                                pixelSize / 3);
                                                                g.setColor(STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize + (pixelSize
                                                                                                - (pixelSize / 3 + pixelSize
                                                                                                                / 3)),
                                                                                pixelSize - (pixelSize / 3),
                                                                                pixelSize / 3);
                                                                g.fillRect(i * pixelSize + pixelSize + (pixelSize
                                                                                - (pixelSize / 3 + pixelSize / 3)),
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize / 3,
                                                                                pixelSize - (pixelSize / 3));
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize - (pixelSize / 3
                                                                                                + pixelSize / 3),
                                                                                pixelSize - (pixelSize / 3
                                                                                                + pixelSize / 3));
                                                                break;
                                                        case TWO_HUNDRED_SEVENTY:
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize
                                                                                                + (pixelSize - pixelSize
                                                                                                                / 3),
                                                                                pixelSize,
                                                                                pixelSize / 3);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize / 3,
                                                                                pixelSize);
                                                                g.setColor(STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3,
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize / 3,
                                                                                pixelSize - (pixelSize / 3));
                                                                g.fillRect(i * pixelSize + pixelSize + (pixelSize / 3),
                                                                                j * pixelSize + pixelSize + (pixelSize
                                                                                                - (pixelSize / 3 + pixelSize
                                                                                                                / 3)),
                                                                                pixelSize - (pixelSize / 3),
                                                                                pixelSize / 3);
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize
                                                                                + (pixelSize / 3 + pixelSize / 3),
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize - (pixelSize / 3
                                                                                                + pixelSize / 3),
                                                                                pixelSize - (pixelSize / 3
                                                                                                + pixelSize / 3));
                                                                break;
                                                }
                                                break;
                                        case CORRIDOR:
                                                switch (b.getRotation()) {
                                                        case ZERO, ONE_HUNDRED_EIGHTY:
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize, pixelSize,
                                                                                pixelSize / 3);
                                                                g.setColor(STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize
                                                                                                + pixelSize / 3,
                                                                                pixelSize, pixelSize / 3);
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize + (pixelSize
                                                                                                / 3 + pixelSize / 3),
                                                                                pixelSize,
                                                                                (pixelSize / 3));
                                                                break;
                                                        case NINETY, TWO_HUNDRED_SEVENTY:
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize / 3,
                                                                                pixelSize);
                                                                g.setColor(STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3,
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize / 3, pixelSize);
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize
                                                                                + (pixelSize / 3 + pixelSize / 3),
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize / 3, pixelSize);
                                                                break;
                                                }
                                                break;
                                        case CROSSING:
                                                switch (b.getRotation()) {
                                                        case ZERO:
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize, pixelSize,
                                                                                (pixelSize / 3));
                                                                g.setColor(STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize
                                                                                                + pixelSize / 3,
                                                                                pixelSize, pixelSize / 3);
                                                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3,
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize / 3, pixelSize / 3);
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize + (pixelSize
                                                                                                / 3 + pixelSize / 3),
                                                                                pixelSize,
                                                                                (pixelSize / 3));
                                                                break;
                                                        case NINETY:
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize / 3,
                                                                                pixelSize);
                                                                g.fillRect(i * pixelSize + pixelSize
                                                                                + (pixelSize / 3 + pixelSize / 3),
                                                                                j * pixelSize + pixelSize,
                                                                                (pixelSize / 3), pixelSize);
                                                                g.setColor(STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3,
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize / 3, pixelSize);
                                                                g.fillRect(i * pixelSize + pixelSize
                                                                                + (pixelSize - pixelSize / 3),
                                                                                j * pixelSize + pixelSize
                                                                                                + pixelSize / 3,
                                                                                (pixelSize / 3),
                                                                                pixelSize / 3);
                                                                break;
                                                        case ONE_HUNDRED_EIGHTY:
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize, pixelSize,
                                                                                pixelSize / 3);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize + (pixelSize
                                                                                                / 3 + pixelSize / 3),
                                                                                pixelSize,
                                                                                (pixelSize / 3));
                                                                g.setColor(STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize
                                                                                                + pixelSize / 3,
                                                                                pixelSize, pixelSize / 3);
                                                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3,
                                                                                j * pixelSize + pixelSize
                                                                                                + (pixelSize - pixelSize
                                                                                                                / 3),
                                                                                pixelSize / 3,
                                                                                pixelSize / 3);
                                                                break;
                                                        case TWO_HUNDRED_SEVENTY:
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize / 3,
                                                                                pixelSize);
                                                                g.setColor(STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3,
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize / 3, pixelSize);
                                                                g.fillRect(i * pixelSize + pixelSize,
                                                                                j * pixelSize + pixelSize
                                                                                                + pixelSize / 3,
                                                                                pixelSize / 3, pixelSize / 3);
                                                                g.setColor(NO_STREET_COLOR);
                                                                g.fillRect(i * pixelSize + pixelSize
                                                                                + (pixelSize / 3 + pixelSize / 3),
                                                                                j * pixelSize + pixelSize,
                                                                                pixelSize / 3, pixelSize);
                                                                break;
                                                }
                                                break;
                                }

                                if (coorPlayers.containsKey(new Coordinate(i, j))) {
                                        Image img = this.loadImage(
                                                        "" + coorPlayers.get(new Coordinate(i, j)).getID() + ".jpg");
                                        g.drawImage(img, i + pixelSize / 4, j + pixelSize / 4, this);
                                }
                        }
                }
        }

        private Image loadImage(String name) {
                String path = "resources/" + name;
                File f = new File(path);
                return ImageIO.read(f);
        }

}
