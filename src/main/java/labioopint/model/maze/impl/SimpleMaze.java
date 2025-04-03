package labioopint.model.maze.impl;

import labioopint.model.maze.api.BlockType;

public class SimpleMaze extends Maze {
    private final static Integer CORNER_BLOCKS = 16;
    private final static Integer CORRIDOR_BLOCKS = 12;
    private final static Integer CROSSING_BLOCKS = 18;

    public SimpleMaze(final Integer size) {
        super(size);
    }

    @Override
    public Block Generate() {
        this.fillDefaultBlocks();
        MazeGenerator mg = new MazeGenerator(getListofBlocks());
        setMaze(mg.fill(getSize()));
        return mg.getOutsideBlock();
    }

    private void fillDefaultBlocks() {
        for (int i = 0; i < CORNER_BLOCKS; i++) {
            getListofBlocks().add(new Block(BlockType.CORNER));
        }
        for (int i = 0; i < CORRIDOR_BLOCKS; i++) {
            getListofBlocks().add(new Block(BlockType.CORRIDOR));
        }
        for (int i = 0; i < CROSSING_BLOCKS; i++) {
            getListofBlocks().add(new Block(BlockType.CROSSING));
        }
    }
}
