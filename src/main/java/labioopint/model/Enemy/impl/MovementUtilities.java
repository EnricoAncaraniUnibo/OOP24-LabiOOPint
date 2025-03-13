package labioopint.model.Enemy.impl;

import labioopint.commons.Coordinate;

public class MovementUtilities {
    /**
     * Determines the next coordinate based on the current position and direction.
     * 
     * @param c         the current coordinate.
     * @param direction the direction to move (0: up, 1: down, 2: right, 3: left).
     * @return the next coordinate after moving in the specified direction.
     */
    public static Coordinate getNextCoordinate(Coordinate c, int direction) {
        switch (direction) {
            case 0:
                return new Coordinate(c.getRow() - 1, c.getColumn());
            case 1:
                return new Coordinate(c.getRow() + 1, c.getColumn());
            case 2:
                return new Coordinate(c.getRow(), c.getColumn() + 1);
            case 3:
                return new Coordinate(c.getRow(), c.getColumn() - 1);
            default:
                return c;
        }
    }
}
