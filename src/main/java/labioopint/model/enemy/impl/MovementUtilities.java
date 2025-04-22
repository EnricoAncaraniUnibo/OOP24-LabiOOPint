package labioopint.model.enemy.impl;

import labioopint.model.api.Coordinate;
import labioopint.model.maze.api.Direction;

/**
 * Utility class for handling movement-related operations, such as determining
 * the next coordinate based on a direction or creating a direction from an
 * integer.
 */
public final class MovementUtilities {

    private MovementUtilities() {
    }

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
            default:
                return new Coordinate(c.getRow(), c.getColumn() - 1);
        }
    }

    /**
     * Creates a {@link Direction} based on an integer value.
     *
     * @param i the integer representing a direction (0: UP, 1: DOWN, 2: RIGHT, 3:
     *          LEFT)
     * @return the corresponding {@link Direction}, or {@code null} if the integer
     *         is invalid
     */
    public static Direction createDirection(final int i) {
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
