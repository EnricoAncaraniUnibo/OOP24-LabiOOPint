package labioopint.model.maze.api;

import labioopint.model.block.impl.BlockImpl;

/**
 * The SimpleMaze interface defines the basic structure and generation of a
 * simple maze.
 * It provides a method to generate the maze and return the outside block.
 */
public interface SimpleMaze {
    /**
     * Generates the maze.
     *
     * @return the outside block of the maze.
     */
    BlockImpl generate();

}
