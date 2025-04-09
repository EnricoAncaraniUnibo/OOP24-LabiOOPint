package labioopint.model.Maze.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Maze.api.Maze;
import labioopint.model.api.Coordinate;

public abstract class MazeImpl implements Maze {
    private final Map<Coordinate, BlockImpl> grid;
    private final List<BlockImpl> blockSelection;
    private final Integer size;

    public MazeImpl(final Integer size) {
        this.size = size;
        this.blockSelection = new ArrayList<>();
        this.grid = new HashMap<>();
    }

    @Override
    public Optional<BlockImpl> GetBlock(final Coordinate c) {
        for (Coordinate coor : grid.keySet()) {
            if(c.getRow().equals(coor.getRow()) && c.getColumn().equals(coor.getColumn())) {
                return Optional.of(grid.get(coor));
            }
        }
        return Optional.empty();
    }

    @Override
    public Coordinate getCoordinate(BlockImpl b){
        for (Map.Entry<Coordinate,BlockImpl> iterableItem : grid.entrySet()) {
            if(iterableItem.getValue().equals(b)){
                return iterableItem.getKey();
            }
        }
        return null;
    }

    @Override
    public List<BlockImpl> getListofBlocks() {
        return blockSelection;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public void setMaze(final Map<Coordinate, BlockImpl> maze) {
        grid.putAll(maze);
    }

    @Override
    public void ChangeCoordinate(final Coordinate coor, final BlockImpl b) {
        Coordinate removable=new Coordinate(-1, -1);
        for (Coordinate test : grid.keySet()) {
            if(test.getRow()==coor.getRow() && test.getColumn()==coor.getColumn()) {
                removable=test;
            }
        }
        try {
            grid.remove(removable);
            grid.put(coor, b);
        } catch (Exception e) {
            
        }
    }
    @Override
    public abstract BlockImpl Generate();
}
