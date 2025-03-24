package labioopint.model.maze.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import labioopint.model.api.Coordinate;

public abstract class Maze {
    private final Map<Coordinate, Block> grid;
    private final List<Block> blockSelection;
    private final Integer size;

    public Maze(final Integer size) {
        this.size = size;
        this.blockSelection = new ArrayList<>();
        this.grid = new HashMap<>();
    }

    public Block GetBlock(final Coordinate c) {
        for (Coordinate coor : grid.keySet()) {
            if(c.getRow().equals(coor.getRow()) && c.getColumn().equals(coor.getColumn())) {
                return grid.get(coor);
            }
        }
        return null;
    }

    public Coordinate getCoordinate(Block b){
        for (Map.Entry<Coordinate,Block> iterableItem : grid.entrySet()) {
            if(iterableItem.getValue().equals(b)){
                return iterableItem.getKey();
            }
        }
        return null;
    }

    public List<Block> getListofBlocks() {
        return blockSelection;
    }

    public Integer getSize() {
        return size;
    }

    public void setMaze(final Map<Coordinate, Block> maze) {
        grid.putAll(maze);
    }

    public void ChangeCoordinate(final Coordinate oldC, final Block b, final Coordinate newC) {
        grid.remove(oldC);
        this.ChangeCoordinate(b, newC);
    }

    public void ChangeCoordinate(final Block b, final Coordinate newC) {
        grid.remove(newC);
        grid.put(newC, b);
    }

    public abstract Block Generate();
}
