package labioopint.model.Maze.api;

import java.util.Map;

import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.api.Coordinate;

public interface MazeGenerator {

    Map<Coordinate, BlockImpl> fill(Integer size);

    BlockImpl getOutsideBlock();

}