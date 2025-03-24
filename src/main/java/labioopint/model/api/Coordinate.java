package labioopint.model.api;

public class Coordinate {
    private Integer row;
    private Integer column;

    public Coordinate(final Integer r, final Integer c) {
        this.row = r;
        this.column = c;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }
}