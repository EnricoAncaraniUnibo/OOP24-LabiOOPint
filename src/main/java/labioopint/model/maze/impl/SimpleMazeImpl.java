package labioopint.model.maze.impl;

import labioopint.model.block.api.Block;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.maze.api.MazeGenerator;
import labioopint.model.maze.api.SimpleMaze;
/**
 * The Implementation of a simple maze.
 */
public class SimpleMazeImpl extends MazeImpl implements SimpleMaze {
    private static final Integer CORNER_BLOCKS = 16;
    private static final Integer CORRIDOR_BLOCKS = 12;
    private static final Integer CROSSING_BLOCKS = 18;
    /**
     * Construct the maze by using the constructor of a general mazeImpl.
     * @param size the size of the maze
     */
    public SimpleMazeImpl(final Integer size) {
        super(size);
    }

    @Override
    public final Block generate() {
        this.fillDefaultBlocks();
        final MazeGenerator mg = new MazeGeneratorImpl(getListofBlocks());
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
