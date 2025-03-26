package labioopint.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.api.Coordinate;
import labioopint.model.api.DualMap;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Maze;
import labioopint.model.maze.impl.PowerUp;
import labioopint.model.player.impl.Player;

public class DrawPanel extends JPanel {
        private static Integer pixelSize;
        private Maze maze;
        private DualMap<Player> coorPlayers;
        private DualMap<Enemy> coorEnemies;
        private DualMap<PowerUp> coorPowerUps;
        private Block outsideBlock;
        private Image CORRIDOR_IMAGE;
        private Image CORNER_IMAGE;
        private Image CROSSING_IMAGE;

        public static Integer getBlockSize() {
                return pixelSize;
        }

        public DrawPanel(final Dimension size) {
                pixelSize = (int) size.getWidth() / 13;
                try {
                        CORRIDOR_IMAGE = ImageIO.read(new File("src/main/java/labioopint/resources/Tiles/Corridor.png"));
                        CORNER_IMAGE = ImageIO.read(new File("src/main/java/labioopint/resources/Tiles/Corner.png"));
                        CROSSING_IMAGE = ImageIO.read(new File("src/main/java/labioopint/resources/Tiles/Crossing.png"));
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void draw(final Maze m, final DualMap<Player> mapPlayers,
                final DualMap<Enemy> mapEnemies, final DualMap<PowerUp> mapPowerUps,
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
                Graphics2D g2 = (Graphics2D) g;
                AffineTransform old = g2.getTransform();
                if(maze!=null) {
                        Block b;
                        boolean end = false;
                        for (int i = 0; i <= maze.getSize() && end==false; i++) {
                                for (int j = 0; j < maze.getSize() && end==false; j++) {
                                        if(i == maze.getSize()) {
                                                b = outsideBlock;
                                                j = maze.getSize()+1;
                                                i = 0;
                                                end=true;
                                        } else {
                                                b = maze.GetBlock(new Coordinate(i, j));
                                        }
                                        switch (b.getType()) {
                                                case CORNER:
                                                        switch (b.getRotation()) {
                                                                case ZERO:
                                                                        g2.drawImage(CORNER_IMAGE, j*pixelSize, i*pixelSize,pixelSize,pixelSize, this);
                                                                        break;
                                                                case NINETY:
                                                                        g2.rotate(Math.toRadians(-90));
                                                                        g2.drawImage(CORNER_IMAGE, (-i*pixelSize)-pixelSize, j*pixelSize,pixelSize,pixelSize, this);   
                                                                        g2.setTransform(old);
                                                                        break;
                                                                case ONE_HUNDRED_EIGHTY:
                                                                        g2.rotate(Math.toRadians(-180));
                                                                        g2.drawImage(CORNER_IMAGE, (-j*pixelSize)-pixelSize, (-i*pixelSize)-pixelSize,pixelSize,pixelSize, this);   
                                                                        g2.setTransform(old);
                                                                        break;
                                                                case TWO_HUNDRED_SEVENTY:
                                                                        g2.rotate(Math.toRadians(-270));
                                                                        g2.drawImage(CORNER_IMAGE, i*pixelSize, (-j*pixelSize)-pixelSize,pixelSize,pixelSize, this);   
                                                                        g2.setTransform(old);
                                                                        break;
                                                        }
                                                        break;
                                                case CORRIDOR:
                                                        switch (b.getRotation()) {
                                                                case ZERO, ONE_HUNDRED_EIGHTY:
                                                                        g2.drawImage(CORRIDOR_IMAGE, j*pixelSize, i*pixelSize,pixelSize,pixelSize, this);
                                                                        break;
                                                                case NINETY, TWO_HUNDRED_SEVENTY:
                                                                        g2.rotate(Math.toRadians(-90));
                                                                        g2.drawImage(CORRIDOR_IMAGE, (-i*pixelSize)-pixelSize, j*pixelSize,pixelSize,pixelSize, this);   
                                                                        g2.setTransform(old);
                                                                        break;
                                                        }
                                                        break;
                                                case CROSSING:
                                                        switch (b.getRotation()) {
                                                                case ZERO:
                                                                        g2.drawImage(CROSSING_IMAGE, j*pixelSize, i*pixelSize,pixelSize,pixelSize, this);
                                                                        break;
                                                                case NINETY:
                                                                        g2.rotate(Math.toRadians(-90));
                                                                        g2.drawImage(CROSSING_IMAGE, (-i*pixelSize)-pixelSize, j*pixelSize,pixelSize,pixelSize, this);   
                                                                        g2.setTransform(old);
                                                                        break;
                                                                case ONE_HUNDRED_EIGHTY:
                                                                        g2.rotate(Math.toRadians(-180));
                                                                        g2.drawImage(CROSSING_IMAGE, (-j*pixelSize)-pixelSize, (-i*pixelSize)-pixelSize,pixelSize,pixelSize, this);   
                                                                        g2.setTransform(old);
                                                                        break;
                                                                case TWO_HUNDRED_SEVENTY:
                                                                        g2.rotate(Math.toRadians(-270));
                                                                        g2.drawImage(CROSSING_IMAGE, i*pixelSize, (-j*pixelSize)-pixelSize,pixelSize,pixelSize, this);   
                                                                        g2.setTransform(old);
                                                                        break;
                                                        }
                                                        break;
                                        }
                                        if(coorPlayers.isPresentByCoordinate(new Coordinate(i, j))) {
                                                g2.drawImage(coorPlayers.getElemFromCoordinate(new Coordinate(i, j)).getImage(), j*pixelSize+pixelSize/4, i*pixelSize+pixelSize/4,pixelSize*3/5,pixelSize*3/5, this);
                                        }
                                        if(coorEnemies.isPresentByCoordinate(new Coordinate(i, j))) {
                                                g2.drawImage(coorEnemies.getElemFromCoordinate(new Coordinate(i, j)).getImage(), j*pixelSize+pixelSize/4, i*pixelSize+pixelSize/4,pixelSize*3/5,pixelSize*3/5, this);
                                        }
                                }
                        }
                }
        }
}
