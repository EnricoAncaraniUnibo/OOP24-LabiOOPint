package labioopint.model.maze.api;

import java.io.Serializable;

import labioopint.model.block.api.Block;
/**
 * Represents a maze in the game.
 * This interface provides methods to generate the maze.
 */
public interface SimpleMaze extends Serializable {
    /**
     * Generates the maze.
     *
     * @return the block remained outside of the generation {@link Block}
     */
    Block generate();

}
