package labioopint.model.Maze.impl;

import labioopint.model.Block.api.BlockType;
import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Maze.api.SimpleMaze;

public class SimpleMazeImpl extends MazeImpl implements SimpleMaze {
    private final static Integer CORNER_BLOCKS = 16;
    private final static Integer CORRIDOR_BLOCKS = 12;
    private final static Integer CROSSING_BLOCKS = 18;

    public SimpleMazeImpl(final Integer size) {
        super(size);
    }

    @Override
    public BlockImpl Generate() {
        this.fillDefaultBlocks();
        MazeGeneratorImpl mg = new MazeGeneratorImpl(getListofBlocks());
        setMaze(mg.fill(getSize()));
        return mg.getOutsideBlock();
    }

    private void fillDefaultBlocks() {
        for (int i = 0; i < CORNER_BLOCKS; i++) {
            getListofBlocks().add(new BlockImpl(BlockType.CORNER));
        }
        for (int i = 0; i < CORRIDOR_BLOCKS; i++) {
            getListofBlocks().add(new BlockImpl(BlockType.CORRIDOR));
        }
        for (int i = 0; i < CROSSING_BLOCKS; i++) {
            getListofBlocks().add(new BlockImpl(BlockType.CROSSING));
        }
    }
}
