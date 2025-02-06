package labioopint.model.Enemy.impl;

public class Point {
    private Integer row;
    private Integer column;

    public Point(final Integer r, final Integer c) {
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
