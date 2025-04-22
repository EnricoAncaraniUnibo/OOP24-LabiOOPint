package labioopint.model.api;

public class Pair<X,Y> {
    private final X first;
    private final Y second;

    public Pair(final X first, final Y second) {
        this.first = first;
        this.second = second;
    }

    public X getFirst() {
        return first;
    }

    public Y getSecond() {
        return second;
    }
}
