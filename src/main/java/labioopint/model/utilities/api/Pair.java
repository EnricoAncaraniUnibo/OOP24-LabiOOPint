package labioopint.model.utilities.api;

import java.io.Serializable;
/**
 * Represents a generic pair of two elements.
 * This interface provides methods to retrieve the first and second elements of the pair.
 *
 * @param <X> the type of the first element in the pair
 * @param <Y> the type of the second element in the pair
 */
public interface Pair<X, Y> extends Serializable {
    /**
     * Retrieves the first element of the pair.
     *
     * @return the first element of type {@code X}
     */
    X getFirst();

    /**
     * Retrieves the second element of the pair.
     *
     * @return the second element of type {@code Y}
     */
    Y getSecond();

}
