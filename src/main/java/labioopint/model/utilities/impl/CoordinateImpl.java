package labioopint.model.utilities.impl;

import labioopint.model.utilities.api.Coordinate;

public final class CoordinateImpl implements Coordinate {
    public static final long serialVersionUID = 1L;
    private final Integer row;
    private final Integer column;

    public CoordinateImpl(final Coordinate coord) {
        this.row = coord.getRow();
        this.column = coord.getColumn();
    }

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
