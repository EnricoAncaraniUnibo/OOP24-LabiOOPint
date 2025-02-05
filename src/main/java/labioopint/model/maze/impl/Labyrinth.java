package labioopint.model.maze.impl;

import labioopint.model.maze.api.Direction;

public class Labyrinth {
    private final SimpleMaze grid;
    private Block outsideBlock;

    public Labyrinth(final Integer size) {
        grid = new SimpleMaze(size);
        outsideBlock=grid.Generate();
    }

    public Block getOutsideBlock() {
        return outsideBlock;
    }

    public boolean moveBlock(final Coordinate c, final Direction d) {
        Block b = grid.GetBlock(c);
        if(b.IsMovable()==true) {
            switch (d) {
                case UP:
                    outsideBlock=shiftColumn(c.getColumn(),d);
                    break;
                case DOWN:
                    outsideBlock=shiftColumn(c.getColumn(),d);
                    break;
                case RIGHT:
                    outsideBlock=shiftRow(c.getRow(), d);
                    break;
                case LEFT:
                    outsideBlock=shiftRow(c.getRow(), d);
                    break;
            }
            return true;
        } else {
            return false;
        }
    }

    private Block shiftRow(final Integer number, final Direction d) {
        if(d.equals(Direction.RIGHT)) {
            Block outside = grid.GetBlock(new Coordinate(number, grid.getSize()-1));
            for(int i=grid.getSize()-2;i>=0;i--) {
                grid.ChangeCoordinate(new Coordinate(number, i), outside, new Coordinate(number, i+1));
            }
            grid.ChangeCoordinate(outsideBlock, new Coordinate(number, 0));
            return outside;
        } else {
            Block outside = grid.GetBlock(new Coordinate(number, 0));
            for(int i=1;i<grid.getSize();i++) {
                grid.ChangeCoordinate(new Coordinate(number, i), outside, new Coordinate(number, i-1));
            }
            grid.ChangeCoordinate(outsideBlock, new Coordinate(number, grid.getSize()-1));
            return outside;
        }
    }

    private Block shiftColumn(final Integer number, final Direction d) {
        if(d.equals(Direction.UP)) {
            Block outside = grid.GetBlock(new Coordinate(0, number));
            for(int i=1;i<grid.getSize();i++) {
                grid.ChangeCoordinate(new Coordinate(i, number), outside, new Coordinate(i-1, number));
            }
            grid.ChangeCoordinate(outsideBlock, new Coordinate(grid.getSize()-1, number));
            return outside;
        } else {
            Block outside = grid.GetBlock(new Coordinate(grid.getSize()-1, number));
            for(int i=grid.getSize()-2;i>=0;i--) {
                grid.ChangeCoordinate(new Coordinate(i, number), outside, new Coordinate(i+1, number));
            }
            grid.ChangeCoordinate(outsideBlock, new Coordinate(0, number));
            return outside;
        }
    }
}
