package labioopint.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Core.impl.ImageLoader;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Maze.impl.MazeImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.Coordinate;
import labioopint.model.api.DualMap;
/**
 * The DrawPanel class is responsible for rendering the labyrinth (maze) and its elements,
 * such as blocks, players, enemies, and power-ups, on a graphical panel.
 * It extends the JPanel class and uses the Graphics2D API for drawing.
 */
public class DrawPanel extends JPanel {
		/**
	     * The size of each block in pixels.
	     */
        private static Integer pixelSize;
        private MazeImpl maze;
        //private DualMap<PlayerImpl> coorPlayers;
        private DualMap<Enemy> coorEnemies;
        private DualMap<PowerUp> coorPowerUps;
        private BlockImpl outsideBlock;
        private Image CORRIDOR_IMAGE;
        private Image CORNER_IMAGE;
        private Image CROSSING_IMAGE;
        
        /**
         * Retrieves the size of each block in pixels.
         *
         * @return the block size in pixels
         */
        public static Integer getBlockSize() {
                return pixelSize;
        }
        
        /**
         * Constructs a DrawPanel with the specified size.
         * Initializes the block size and loads the required images.
         *
         * @param size the dimensions of the panel
         */
        public DrawPanel(final Dimension size) {
                pixelSize = (int) size.getWidth() / 13;
                ImageLoader.load();
                CORRIDOR_IMAGE = ImageLoader.getImage("Corridor").get();
                CORNER_IMAGE = ImageLoader.getImage("Corner").get();
                CROSSING_IMAGE = ImageLoader.getImage("Crossing").get();
        }
        /**
         * Updates the panel with the current state of the maze, players, enemies, power-ups,
         * and the block outside the maze, and triggers a repaint.
         *
         * @param m the maze to be drawn
         * @param mapPlayers the mapping of players to their coordinates
         * @param mapEnemies the mapping of enemies to their coordinates
         * @param mapPowerUps the mapping of power-ups to their coordinates
         * @param outside the block currently outside the maze
         */
        public void draw(final MazeImpl m, final DualMap<PlayerImpl> mapPlayers,
                final DualMap<Enemy> mapEnemies, final DualMap<PowerUp> mapPowerUps,
                final BlockImpl outside) {
                this.maze = m;
                //this.coorPlayers = mapPlayers;
                this.coorEnemies = mapEnemies;
                this.coorPowerUps = mapPowerUps;
                this.outsideBlock = outside;
                repaint();
        }
        /**
         * Paints the components of the panel, including the maze, blocks, players, enemies,
         * and power-ups, using the Graphics2D API.
         *
         * @param g the Graphics object used for drawing
         */
        protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                AffineTransform old = g2.getTransform();
                if(maze!=null) {
                        BlockImpl b;
                        boolean end = false;
                        for (int i = 0; i <= maze.getSize() && end==false; i++) {
                                for (int j = 0; j < maze.getSize() && end==false; j++) {
                                        if(i == maze.getSize()) {
                                                b = outsideBlock;
                                                j = maze.getSize()+1;
                                                i = 0;
                                                end=true;
                                        } else {
                                                b = maze.GetBlock(new Coordinate(i, j)).get();
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
                                        if(coorPowerUps.isPresentByCoordinate(new Coordinate(i,j))) {
                                                g2.drawImage(ImageLoader.getImage(coorPowerUps.getElemFromCoordinate(new Coordinate(i, j)).getName()).get(), j*pixelSize+pixelSize/4, i*pixelSize+pixelSize/4,pixelSize*3/5,pixelSize*3/5, this);
                                        }
                                        if(coorEnemies.isPresentByCoordinate(new Coordinate(i, j))) {
                                                g2.drawImage(ImageLoader.getImage("Monster").get(), j*pixelSize+pixelSize/4, i*pixelSize+pixelSize/4,pixelSize*3/5,pixelSize*3/5, this);
                                        }
                                }
                        }
                        for (PlayerImpl p : TurnManager.GetPlayers()) {
                                Coordinate c = TurnManager.GetLab().getPlayerCoordinate(p);
                                if(p == TurnManager.GetCurrentPlayer()) {
                                        g2.drawImage(ImageLoader.getImage(""+p.getID()+"Turn").get(), c.getColumn()*pixelSize+pixelSize/4, c.getRow()*pixelSize+pixelSize/4,pixelSize*3/5,pixelSize*3/5, this);
                                }else {
                                        g2.drawImage(ImageLoader.getImage(""+p.getID()+"").get(), c.getColumn()*pixelSize+pixelSize/4, c.getRow()*pixelSize+pixelSize/4,pixelSize*3/5,pixelSize*3/5, this);
                                }

                        }
                }
        }
}
