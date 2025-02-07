package labioopint.model.maze.impl;

import labioopint.model.maze.api.BlockType;

public class ComplexMaze extends Maze {
    private final Integer corners;
    private final Integer corridors;
    private final Integer crossings;

    public ComplexMaze(final Integer size, final Integer corners, final Integer corridors, final Integer crossings) {
        super(size);
        this.corners = corners;
        this.corridors = corridors;
        this.crossings = crossings;
    }

    @Override
    public Block Generate() {
        this.fillBlocks();
        MazeGenerator mg = new MazeGenerator(getListofBlocks());
        setMaze(mg.fill(getSize()));
        return mg.getOutsideBlock();
    }

    private void fillBlocks() {
        for (int i = 0; i < corners; i++) {
            getListofBlocks().add(new Block(BlockType.CORNER));
        }
        for (int i = 0; i < corridors; i++) {
            getListofBlocks().add(new Block(BlockType.CORRIDOR));
        }
        for (int i = 0; i < crossings; i++) {
            getListofBlocks().add(new Block(BlockType.CROSSING));
        }
    }
}
