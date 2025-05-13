package labioopint.model.maze.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.block.api.Block;
import labioopint.model.maze.api.Maze;

/**
 * The abstract class that define the behavior of the maze.
 */
public abstract class MazeImpl implements Maze {
    public static final long serialVersionUID = 1L;
    private final Map<Coordinate, Block> grid;
    private final List<Block> blockSelection;
    private final Integer size;

    /**
     * Initialize the maze without building it yet with a size.
     * 
     * @param size the size of the maze.
     */
    public MazeImpl(final Integer size) {
        this.size = size;
        this.blockSelection = new ArrayList<>();
        this.grid = new HashMap<>();
    }

    @Override
    public final Optional<Block> getBlock(final Coordinate c) {
        for (final Coordinate coor : grid.keySet()) {
            if (c.getRow().equals(coor.getRow()) && c.getColumn().equals(coor.getColumn())) {
                return Optional.of(grid.get(coor));
            }
        }
        return Optional.empty();
    }

    @Override
    public final Coordinate getCoordinate(final Block b) {
        for (final Map.Entry<Coordinate, Block> iterableItem : grid.entrySet()) {
            if (iterableItem.getValue().equals(b)) {
                return iterableItem.getKey();
            }
        }
        return null;
    }

    @Override
    public final List<Block> getListofBlocks() {
        return blockSelection;
    }

    @Override
    public final Integer getSize() {
        return size;
    }

    @Override
    public final void setMaze(final Map<Coordinate, Block> maze) {
        grid.putAll(maze);
    }

    @Override
    public final void changeCoordinate(final Coordinate coor, final Block b) {
        Coordinate removable = new CoordinateImpl(-1, -1);
        for (final Coordinate test : grid.keySet()) {
            if (test.equals(coor)) {
                removable = test;
            }
        }
        grid.remove(removable);
        grid.put(coor, b);
    }

    @Override
    public abstract Block generate();
}
