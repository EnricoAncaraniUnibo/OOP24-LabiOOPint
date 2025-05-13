package labioopint.model.utilities.api;

import java.io.Serializable;
import java.util.Set;

public interface DualMap<X> extends Serializable {

    void addElemWithCoordinate(X elem, Coordinate coor);

    Coordinate getCoordinateFromElement(X elem);

    X getElemFromCoordinate(Coordinate coor);

    void remove(X elem);

    boolean isPresentByCoordinate(Coordinate coor);

    boolean isPresentByObject(X elem);

    Set<X> getElements();
}
