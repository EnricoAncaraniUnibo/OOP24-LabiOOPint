package labioopint.model.api;

import java.util.HashMap;
import java.util.Map;

public class DualMap<X> {
    private Map<Coordinate, X> mapFromCoordinate;
    private Map<X, Coordinate> mapFromElement;

    public DualMap() {
        mapFromCoordinate = new HashMap<>();
        mapFromElement = new HashMap<>();
    }

    public void addElemWithCoordinate(final X elem, final Coordinate coor) {
        mapFromCoordinate.put(coor, elem);
        mapFromElement.put(elem, coor);
    }

    public Coordinate getCoordinateFromElement(final X elem) {
        return mapFromElement.get(elem);
    }

    public X getElemFromCoordinate(final Coordinate coor) {
        return mapFromCoordinate.get(coor);
    }

    public void remove(final X elem) {
        mapFromCoordinate.remove(mapFromElement.get(elem));
        mapFromElement.remove(elem);
    }

    public Map<Coordinate,X> getMapFromCoordinate() {
        return mapFromCoordinate;
    }
}