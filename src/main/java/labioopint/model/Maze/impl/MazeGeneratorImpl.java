package labioopint.model.Maze.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import labioopint.model.Block.api.BlockType;
import labioopint.model.Block.api.Rotation;
import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Maze.api.MazeGenerator;
import labioopint.model.api.Coordinate;
/**
 * The MazeGeneratorImpl class implements the MazeGenerator interface and provides
 * the logic for generating a maze with blocks and their respective coordinates.
 */
public final class MazeGeneratorImpl implements MazeGenerator {
    private final List<BlockImpl> selectableBlocks;
    private final Random r;

    /**
     * Constructs a MazeGeneratorImpl with a list of selectable blocks.
     *
     * @param ls the list of BlockImpl objects to be used for maze generation
     */
    public MazeGeneratorImpl(final List<BlockImpl> ls) {
        selectableBlocks = new ArrayList<>();
        selectableBlocks.addAll(ls);
        r = new Random();
    }

    @Override
    public Map<Coordinate, BlockImpl> fill(final Integer size) {
        Map<Coordinate, BlockImpl> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!((i == 0 && j == 0) || (i == size - 1 && j == 0) || (i == 0 && j == size - 1) || (i == size - 1 && j == size - 1))) {
                    BlockImpl b = selectableBlocks.get(r.nextInt(0, selectableBlocks.size()));
                    selectableBlocks.remove(b);
                    Coordinate c = new Coordinate(i, j);
                    b.RandomRotation();
                    map.put(c, b);
                }   
            }
        }
        BlockImpl b = new BlockImpl(BlockType.CORNER);
        b.disable();
        map.put(new Coordinate(0, 0), b);
        b = new BlockImpl(BlockType.CORNER);
        b.disable();
        b.setRotation(Rotation.NINETY);
        map.put(new Coordinate(size - 1, 0), b);
        b = new BlockImpl(BlockType.CORNER);
        b.disable();
        b.setRotation(Rotation.TWO_HUNDRED_SEVENTY);
        map.put(new Coordinate(0, size - 1), b);
        b = new BlockImpl(BlockType.CORNER);
        b.disable();
        b.setRotation(Rotation.ONE_HUNDRED_EIGHTY);
        map.put(new Coordinate(size - 1, size - 1), b);
        return map;
    }

    @Override
    public BlockImpl getOutsideBlock() {
        BlockImpl b = selectableBlocks.get(r.nextInt(0, selectableBlocks.size()));
        selectableBlocks.remove(b);
        return b;
    }

}
