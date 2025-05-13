package labioopint.model.utilities.impl;

import labioopint.model.utilities.api.Pair;

public final class PairImpl<X, Y> implements Pair<X, Y> {
    public static final long serialVersionUID = 1L;
    private final X first;
    private final Y second;

    public PairImpl(final X first, final Y second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public X getFirst() {
        return first;
    }

    @Override
    public Y getSecond() {
        return second;
    }
}
