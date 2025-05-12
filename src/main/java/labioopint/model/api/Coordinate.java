package labioopint.model.api;

import java.io.Serializable;

/**
 * The Coordinate class represents a position in a two-dimensional grid
 * with row and column values.
 */
public class Coordinate implements Serializable {
    public static final long serialVersionUID = 1L;
    private final Integer row;
    private final Integer column;

    /**
     * Constructs a new Coordinate by copying the values from another Coordinate.
     *
     * @param coord the other Coordinate from which takes values
     */
    public Coordinate(final Coordinate coord) {
        this.row = coord.getRow();
        this.column = coord.getColumn();
    }

    /**
     * Constructs a new Coordinate with the specified row and column values.
     *
     * @param r the row value
     * @param c the column value
     */
    public Coordinate(final Integer r, final Integer c) {
        this.row = r;
        this.column = c;
    }

    /**
     * Retrieves the row value of this Coordinate.
     *
     * @return the row value
     */
    public Integer getRow() {
        return row;
    }

    /**
     * Retrieves the column value of this Coordinate.
     *
     * @return the column value
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * Computes the hash code for this {@code Coordinate}.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((row == null) ? 0 : row.hashCode());
        result = prime * result + ((column == null) ? 0 : column.hashCode());
        return result;
    }

    /**
     * Compares this {@code Coordinate} to the specified object. The result is
     * {@code true}
     * if and only if the argument is not {@code null}, is a {@code Coordinate}
     * object, and
     * has the same row and column values as this {@code Coordinate}.
     *
     * @param obj the object to compare this {@code Coordinate} against
     * @return {@code true} if the given object represents a {@code Coordinate}
     *         equivalent
     *         to this {@code Coordinate}, {@code false} otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (row == null) {
            if (other.row != null) {
                return false;
            }
        } else if (!row.equals(other.row)) {
            return false;
        }
        if (column == null) {
            if (other.column != null) {
                return false;
            }
        } else if (!column.equals(other.column)) {
            return false;
        }
        return true;
    }
}
