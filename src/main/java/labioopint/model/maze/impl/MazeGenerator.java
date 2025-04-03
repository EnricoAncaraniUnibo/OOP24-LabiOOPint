package labioopint.model.maze.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import labioopint.model.api.Coordinate;
import labioopint.model.maze.api.BlockType;
import labioopint.model.maze.api.Rotation;

public class MazeGenerator {
    private final List<Block> selectableBlocks;
    private final Random r;

    public MazeGenerator(final List<Block> ls) {
        selectableBlocks = new ArrayList<>();
        selectableBlocks.addAll(ls);
        r = new Random();
    }

    public Map<Coordinate, Block> fill(final Integer size) {
        Map<Coordinate, Block> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if((i==0 && j==0) || (i==size-1 && j==0) || (i==0 && j==size-1) || (i==size-1 && j==size-1)) {
                    
                } else {
                    Block b = selectableBlocks.get(r.nextInt(0, selectableBlocks.size()));
                    selectableBlocks.remove(b);
                    Coordinate c = new Coordinate(i, j);
                    if (j % 2 == 0 && i % 2 == 0) {
                        b.disable();
                    }
                    b.RandomRotation();
                    map.put(c, b);
                }   
            }
        }
        Block b = new Block(BlockType.CORNER);
        map.put(new Coordinate(0, 0), b);
        b = new Block(BlockType.CORNER);
        b.setRotation(Rotation.NINETY);
        map.put(new Coordinate(size-1, 0), b);
        b = new Block(BlockType.CORNER);
        b.setRotation(Rotation.TWO_HUNDRED_SEVENTY);
        map.put(new Coordinate(0, size-1), b);
        b = new Block(BlockType.CORNER);
        b.setRotation(Rotation.ONE_HUNDRED_EIGHTY);
        map.put(new Coordinate(size-1, size-1), b);
        return map;
    }

    public Block getOutsideBlock() {
        Block b = selectableBlocks.get(r.nextInt(0, selectableBlocks.size()));
        selectableBlocks.remove(b);
        return b;
    }

}
