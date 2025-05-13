package labioopint.model.maze.api;

import java.util.Map;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.block.api.Block;

public interface MazeGenerator {

    Map<Coordinate, Block> fill(Integer size);

    Block getOutsideBlock();

}