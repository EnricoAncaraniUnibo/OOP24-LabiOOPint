package labioopint.model.maze.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import labioopint.model.api.Coordinate;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.maze.api.Maze;
/**
 * The MazeImpl class provides an abstract implementation of the Maze interface.
 * It manages the maze grid, block selection, and size, and provides methods for
 * interacting with the maze's blocks and coordinates.
 */
public abstract class MazeImpl implements Maze {
    private final Map<Coordinate, BlockImpl> grid;
    private final List<BlockImpl> blockSelection;
    private final Integer size;

     /**
     * Constructs a MazeImpl with the specified size.
     * Initializes the grid and block selection.
     *
     * @param size the size of the maze
     */
    public MazeImpl(final Integer size) {
        this.size = size;
        this.blockSelection = new ArrayList<>();
        this.grid = new HashMap<>();
    }

    @Override
    public final Optional<BlockImpl> getBlock(final Coordinate c) {
        for (Coordinate coor : grid.keySet()) {
            if (c.getRow().equals(coor.getRow()) && c.getColumn().equals(coor.getColumn())) {
                return Optional.of(grid.get(coor));
            }
        }
        return Optional.empty();
    }

    @Override
    public final Coordinate getCoordinate(final BlockImpl b) {
        for (Map.Entry<Coordinate, BlockImpl> iterableItem : grid.entrySet()) {
            if (iterableItem.getValue().equals(b)) {
                return iterableItem.getKey();
            }
        }
        return null;
    }

    @Override
    public final List<BlockImpl> getListofBlocks() {
        return blockSelection;
    }

    @Override
    public final Integer getSize() {
        return size;
    }

    @Override
    public final void setMaze(final Map<Coordinate, BlockImpl> maze) {
        grid.putAll(maze);
    }

    @Override
    public final void changeCoordinate(final Coordinate coor, final BlockImpl b) {
        Coordinate removable = new Coordinate(-1, -1);
        for (Coordinate test : grid.keySet()) {
            if (test.getRow() == coor.getRow() && test.getColumn() == coor.getColumn()) {
                removable = test;
            }
        }
        try {
            grid.remove(removable);
            grid.put(coor, b);
        } catch (Exception e) {
        }
    }
    @Override
    public abstract BlockImpl generate();
}
