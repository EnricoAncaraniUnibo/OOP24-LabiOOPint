package labioopint.model.maze.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import labioopint.model.api.Coordinate;
import labioopint.model.block.impl.BlockImpl;

/**
 * The Maze interface defines the structure and behavior of a maze.
 * It provides methods for managing blocks, their coordinates, and the overall
 * configuration of the maze.
 */
public interface Maze {
    /**
     * Retrieves the block located at the specified coordinate.
     *
     * @param c the coordinate of the block to retrieve
     * @return an Optional containing the block if found, or an empty Optional if
     *         not found
     */
    Optional<BlockImpl> getBlock(Coordinate c);

    /**
     * Retrieves the coordinate of the specified block.
     *
     * @param b the block whose coordinate is to be retrieved
     * @return the coordinate of the block
     */
    Coordinate getCoordinate(BlockImpl b);

    /**
     * Retrieves the list of all blocks in the maze.
     *
     * @return a list of all blocks in the maze
     */
    List<BlockImpl> getListofBlocks();

    /**
     * Retrieves the size of the maze.
     *
     * @return the size of the maze as an Integer
     */
    Integer getSize();

    /**
     * Sets the maze configuration using a map of coordinates to blocks.
     *
     * @param maze a map representing the maze configuration
     */
    void setMaze(Map<Coordinate, BlockImpl> maze);

    /**
     * Changes the coordinate of a block in the maze.
     *
     * @param coor the new coordinate to assign to the block
     * @param b    the block whose coordinate is to be changed
     */
    void changeCoordinate(Coordinate coor, BlockImpl b);

    /**
     * Generates a new block for the maze.
     *
     * @return the newly generated block as a BlockImpl
     */
    BlockImpl generate();

}
