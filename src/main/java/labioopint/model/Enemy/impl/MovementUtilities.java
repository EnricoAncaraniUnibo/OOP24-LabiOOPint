package labioopint.model.Enemy.impl;

import labioopint.model.Maze.api.Direction;
import labioopint.model.api.Coordinate;

public class MovementUtilities {
    /**
     * Determines the next coordinate based on the current position and direction.
     * 
     * @param c         the current coordinate.
     * @param direction the direction to move (0: up, 1: down, 2: right, 3: left).
     * @return the next coordinate after moving in the specified direction.
     */
    public static Coordinate getNextCoordinate(final Coordinate c, final Direction direction) {
        switch (direction) {
            case Direction.UP:
                return new Coordinate(c.getRow() - 1, c.getColumn());
            case Direction.DOWN:
                return new Coordinate(c.getRow() + 1, c.getColumn());
            case Direction.RIGHT:
                return new Coordinate(c.getRow(), c.getColumn() + 1);
            case Direction.LEFT:
                return new Coordinate(c.getRow(), c.getColumn() - 1);
            default:
                return c;
        }
    }

    public static Direction createDirection(int i) {
        switch (i) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.DOWN;
            case 2:
                return Direction.RIGHT;
            case 3:
                return Direction.LEFT;
            default:
                return null;
        }
    }
}
