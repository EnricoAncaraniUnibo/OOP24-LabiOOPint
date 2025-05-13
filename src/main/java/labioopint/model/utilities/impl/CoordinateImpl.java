package labioopint.model.utilities.impl;

import labioopint.model.utilities.api.Coordinate;
/**
 * The class that implements the coordinate interface type.
 * Used to indicate a position in a 2-dimesional grid.
 */
public final class CoordinateImpl implements Coordinate {
    public static final long serialVersionUID = 1L;
    private final Integer row;
    private final Integer column;
    /**
     * Create a coordinate coping the values of another one.
     * @param coord the other coordinate
     */
    public CoordinateImpl(final Coordinate coord) {
        this.row = coord.getRow();
        this.column = coord.getColumn();
    }
    /**
     * Create a coordinate by passing the row and column values.
     * 
     * @param r the row value
     * @param c the column value
     */
    public CoordinateImpl(final Integer r, final Integer c) {
        this.row = r;
        this.column = c;
    }

    @Override
    public Integer getRow() {
        return row;
    }

    @Override
    public Integer getColumn() {
        return column;
    }

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
            if (other.getRow() != null) {
                return false;
            }
        } else if (!row.equals(other.getRow())) {
            return false;
        }
        if (column == null) {
            if (other.getColumn() != null) {
                return false;
            }
        } else if (!column.equals(other.getColumn())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((row == null) ? 0 : row.hashCode());
        result = prime * result + ((column == null) ? 0 : column.hashCode());
        return result;
    }
}
