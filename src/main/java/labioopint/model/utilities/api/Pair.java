package labioopint.model.utilities.api;

import java.io.Serializable;

public interface Pair<X, Y> extends Serializable {

    X getFirst();

    Y getSecond();

}
