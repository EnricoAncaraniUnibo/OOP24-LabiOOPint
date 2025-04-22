package labioopint.model.maze.api;

import java.util.Map;

import labioopint.model.api.Coordinate;
import labioopint.model.block.impl.BlockImpl;
/**
 * The MazeGenerator interface defines the methods required for generating
 * and managing the structure of a maze. It provides functionality to fill
 * the maze with blocks and retrieve the block outside the maze.
 */
public interface MazeGenerator {
	/**
     * Fills the maze with blocks based on the specified size.
     *
     * @param size the size of the maze to generate
     * @return a map representing the maze configuration, where the keys are
     *         coordinates and the values are blocks
     */
    Map<Coordinate, BlockImpl> fill(Integer size);
    /**
     * Retrieves the block currently outside the maze.
     *
     * @return the block outside the maze as a BlockImpl
     */
    BlockImpl getOutsideBlock();

}