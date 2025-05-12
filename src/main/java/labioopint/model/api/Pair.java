package labioopint.model.api;

import java.io.Serializable;

public final class Pair<X, Y> implements Serializable {
    public static final long serialVersionUID = 1L;
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
