package labioopint.model.maze.impl;

import labioopint.model.maze.api.BlockType;

public class SimpleMaze extends Maze{
    private final static Integer CORNER_BLOCKS = 20;
    private final static Integer CORRIDOR_BLOCKS = 12;
    private final static Integer CROSSING_BLOCKS = 18;

    public SimpleMaze(final Integer size){
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
        for(int i=0;i<CORNER_BLOCKS;i++) {
            getListofBlocks().add(new Block(BlockType.CORNER));
        }
        for(int i=0;i<CORRIDOR_BLOCKS;i++) {
            getListofBlocks().add(new Block(BlockType.CORRIDOR));
        }
        for(int i=0;i<CROSSING_BLOCKS;i++) {
            getListofBlocks().add(new Block(BlockType.CROSSING));
        }
    }
    /*
     * private Block fillMaze() {
        Random r = new Random();
        final List<Block> tmp = new ArrayList<>();
        tmp.addAll(getListofBlocks());
        for(int i=0;i<getSize();i++) {
            for(int j=0;j<getSize();j++) {
                Block b = tmp.get(r.nextInt(0,tmp.size()));
                tmp.remove(b);
                Coordinate c = new Coordinate(i, j);
                if(j%2==0 && i%2==0) {
                    b.DisableMovement();
                }
                b.RandomRotation();
                getMaze().put(c, b);
            }
        }
        Block b = tmp.get(r.nextInt(0,tmp.size()));
        tmp.remove(b);
        return b;
    }
    fillmaze potrebbe andare in una classe generator
     * 
     */
    
}
