package labioopint.model.maze.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return grid.get(c);
    }

    public List<Block> getListofBlocks() {
        return blockSelection;
    }

    public Integer getSize() {
        return size;
    }

    public Map<Coordinate, Block> getMaze() {
        return grid;
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
