package labioopint.model.maze.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.block.api.Block;

/**
 * Represents the maze structure in the game.
 * This interface provides methods to manage the blocks, coordinates, and layout
 * of the maze.
 */
public interface Maze extends Serializable {

    /**
     * Retrieves the block at the specified coordinate in the maze.
     *
     * @param c the {@link Coordinate} of the block to retrieve
     * @return an {@link Optional} containing the {@link Block} if present, or empty
     *         if no block exists at the coordinate
     */
    Optional<Block> getBlock(Coordinate c);

    /**
     * Retrieves the coordinate of the specified block in the maze.
     *
     * @param b the {@link Block} whose coordinate is to be retrieved
     * @return the {@link Coordinate} of the block
     */
    Coordinate getCoordinate(Block b);

    /**
     * Retrieves a list of all blocks in the maze.
     *
     * @return a list of {@link Block} instances representing the blocks in the maze
     */
    List<Block> getListofBlocks();

    /**
     * Retrieves the size of the maze.
     *
     * @return the size of the maze as an {@link Integer}
     */
    Integer getSize();

    /**
     * Sets the layout of the maze using a map of coordinates to blocks.
     *
     * @param maze a {@link Map} where keys are {@link Coordinate} instances and
     *             values are {@link Block} instances
     */
    void setMaze(Map<Coordinate, Block> maze);

    /**
     * Changes the coordinate of a block in the maze.
     *
     * @param coor the new {@link Coordinate} for the block
     * @param b    the {@link Block} to move
     */
    void changeCoordinate(Coordinate coor, Block b);

    /**
     * Generates a new maze and give back the last block.
     *
     * @return the block remained outside during the generation {@link Block}
     */
    Block generate();

    /**
     * Add a block to the list of selectable blocks.
     *
     * @param b the block to be added
     */
    void addBlockToTheList(Block b);

    /**
     * Give the complete maze in the format of coordinate --> block.
     * 
     * @return the map of the coordinates and the blocks
     */
    Map<Coordinate, Block> getMap();

}
