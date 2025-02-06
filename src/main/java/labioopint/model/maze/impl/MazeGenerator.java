package labioopint.model.maze.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MazeGenerator {
    private final List<Block> selectableBlocks;
    private final Random r;

    public MazeGenerator(final List<Block> ls) {
        selectableBlocks = new ArrayList<>();
        selectableBlocks.addAll(ls);
        r = new Random();
    }

    public Map<Coordinate,Block> fill(final Integer size) {
        Map<Coordinate,Block> map = new HashMap<>();
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                Block b = selectableBlocks.get(r.nextInt(0,selectableBlocks.size()));
                selectableBlocks.remove(b);
                Coordinate c = new Coordinate(i, j);
                if(j%2==0 && i%2==0) {
                    b.DisableMovement();
                }
                b.RandomRotation();
                map.put(c, b);
            }
        }
        return map;
        
    }

    public Block getOutsideBlock() {
        Block b = selectableBlocks.get(r.nextInt(0,selectableBlocks.size()));
        selectableBlocks.remove(b);
        return b;
    }
    
}
