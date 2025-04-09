package labioopint.model.Maze.api;

import labioopint.model.Block.impl.BlockImpl;
/**
 * The SimpleMaze interface defines the basic structure and generation of a simple maze.
 * It provides a method to generate the maze and return the outside block.
 */
public interface SimpleMaze {
	/**
     * Generates the maze
     *
     * @return the outside block of the maze
     */
    BlockImpl Generate();

}