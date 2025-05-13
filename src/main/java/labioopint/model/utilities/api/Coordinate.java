package labioopint.model.utilities.api;

import java.io.Serializable;
/**
 * Represents a coordinate in a two-dimensional grid.
 * This interface provides methods to retrieve the row and column values of the coordinate.
 */
public interface Coordinate extends Serializable {

    /**
     * Retrieves the row value of the coordinate.
     *
     * @return the row value as an {@link Integer}
     */
    Integer getRow();

    /**
     * Retrieves the column value of the coordinate.
     *
     * @return the column value as an {@link Integer}
     */
    Integer getColumn();

    /**
     * Compares this coordinate to another object for equality.
     *
     * @param obj the object to compare with
     * @return {@code true} if the object is equal to this coordinate, {@code false} otherwise
     */
    boolean equals(Object obj);
}
