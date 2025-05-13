package labioopint.model.maze.api;

import java.util.Map;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.block.api.Block;
/**
 * Represents a generator for creating the maze structure in the game.
 * This interface provides methods to fill the maze with blocks and retrieve the block outside the maze.
 */
public interface MazeGenerator {
    /**
     * Fills the maze with blocks based on the specified size.
     *
     * @param size the size of the maze to generate
     * @return a {@link Map} where keys are {@link Coordinate} instances and values are {@link Block} instances
     */
    Map<Coordinate, Block> fill(Integer size);

    /**
     * Retrieves the block currently outside the maze.
     *
     * @return the {@link Block} outside the maze
     */
    Block getOutsideBlock();

}
