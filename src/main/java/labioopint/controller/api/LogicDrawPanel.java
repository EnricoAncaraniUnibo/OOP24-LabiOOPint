package labioopint.controller.api;

import java.awt.Image;
import java.util.List;

import labioopint.model.api.DualMap;
import labioopint.model.api.Pair;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.impl.MazeImpl;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;

public interface LogicDrawPanel {

    /**
     * Gets the size of a block in the panel.
     *
     * @return the value as an Integer
     */
    Integer getPixelSize();

    /**
     * Updates the data required for drawing the panel, including the maze, players,
     * enemy, powerups, and the outside block.
     * 
     * 
     * @param m           the MazeImpl instance representing the maze
     * @param mapPlayers  the DualMap containing player coordinates
     * @param mapEnemies  the DualMap containing enemy coordinates
     * @param mapPowerUps the DualMap containing power-up coordinates
     * @param outside     the BlockImpl representing the outside block
     */
    void updateData(MazeImpl m, DualMap<PlayerImpl> mapPlayers,
            DualMap<Enemy> mapEnemies, DualMap<PowerUp> mapPowerUps,
            BlockImpl outside);

    /**
     * Checks if the panel is ready to be drawn.
     *
     * @return true if the maze is not null, false otherwise
     */
    Boolean canDraw();

    /**
     * Retrieves a list of images and their associated properties (rotation, position, size)
     * for all blocks, players, enemies, and power-ups to be drawn on the panel.
     *
     * @return a List of Pairs containing image, rotation, position, and size information
     */
    List<Pair<Pair<Image, Double>, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>> getImagesBlocks();

}
