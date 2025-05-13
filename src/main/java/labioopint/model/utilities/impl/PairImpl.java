package labioopint.model.utilities.impl;

import labioopint.model.utilities.api.Pair;
/**
 * The pair class that contains 2 values.
 * @param <X> the type of the first element
 * @param <Y> the type of the second element
 */
public final class PairImpl<X, Y> implements Pair<X, Y> {
    public static final long serialVersionUID = 1L;
    private final X first;
    private final Y second;
    /**
     * Create a new pair by passing it the values to be saved.
     * 
     * @param first the first value
     * @param second the second value
     */
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
