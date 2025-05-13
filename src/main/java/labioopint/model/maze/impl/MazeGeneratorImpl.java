package labioopint.model.maze.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.block.api.Block;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.maze.api.MazeGenerator;

public final class MazeGeneratorImpl implements MazeGenerator {
    private final List<Block> selectableBlocks;
    private final Random r;

    public MazeGeneratorImpl(final List<Block> ls) {
        selectableBlocks = new ArrayList<>();
        selectableBlocks.addAll(ls);
        r = new Random();
    }

    @Override
    public Map<Coordinate, Block> fill(final Integer size) {
        final Map<Coordinate, Block> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!((i == 0 && j == 0) || (i == size - 1 && j == 0)
                        || (i == 0 && j == size - 1) || (i == size - 1 && j == size - 1))) {
                    final Block b = selectableBlocks.get(r.nextInt(0, selectableBlocks.size()));
                    selectableBlocks.remove(b);
                    final Coordinate c = new CoordinateImpl(i, j);
                    b.randomRotation();
                    map.put(c, b);
                }
            }
        }
        BlockImpl b = new BlockImpl(BlockType.CORNER);
        b.disable();
        map.put(new CoordinateImpl(0, 0), b);
        b = new BlockImpl(BlockType.CORNER);
        b.disable();
        b.setRotation(Rotation.NINETY);
        map.put(new CoordinateImpl(size - 1, 0), b);
        b = new BlockImpl(BlockType.CORNER);
        b.disable();
        b.setRotation(Rotation.TWO_HUNDRED_SEVENTY);
        map.put(new CoordinateImpl(0, size - 1), b);
        b = new BlockImpl(BlockType.CORNER);
        b.disable();
        b.setRotation(Rotation.ONE_HUNDRED_EIGHTY);
        map.put(new CoordinateImpl(size - 1, size - 1), b);
        return map;
    }

    @Override
    public Block getOutsideBlock() {
        final Block b = selectableBlocks.get(r.nextInt(0, selectableBlocks.size()));
        selectableBlocks.remove(b);
        return b;
    }

}
