package labioopint.model.api;
/**
 * The Coordinate class represents a position in a two-dimensional grid
 * with row and column values.
 */
public class Coordinate {
    private Integer row;
    private Integer column;
    /**
     * Constructs a new Coordinate by copying the values from another Coordinate.
     *
     * @param coord the other Coordinate from which takes values
     */
    public Coordinate(Coordinate coord){
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
}