package labioopint.model.maze.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.block.api.Block;

public interface Maze extends Serializable{

    Optional<Block> getBlock(Coordinate c);

    Coordinate getCoordinate(Block b);

    List<Block> getListofBlocks();

    Integer getSize();

    void setMaze(Map<Coordinate, Block> maze);

    void changeCoordinate(Coordinate coor, Block b);

    Block generate();

}