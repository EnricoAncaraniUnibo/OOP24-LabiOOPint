package labioopint.model.Maze.api;

import java.util.List;
import java.util.Map;

import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.api.Coordinate;

public interface Maze {

    BlockImpl GetBlock(Coordinate c);

    Coordinate getCoordinate(BlockImpl b);

    List<BlockImpl> getListofBlocks();

    Integer getSize();

    void setMaze(Map<Coordinate, BlockImpl> maze);

    void ChangeCoordinate(Coordinate coor, BlockImpl b);

    BlockImpl Generate();

}