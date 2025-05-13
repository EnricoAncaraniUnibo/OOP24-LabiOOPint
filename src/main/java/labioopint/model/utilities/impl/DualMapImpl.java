package labioopint.model.utilities.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.api.DualMap;
/**
 * The class that map a element to a coordinate and viceversa.
 * @param <X> the type of element to save with his coordinates
 */
public final class DualMapImpl<X> implements DualMap<X> {
    public static final long serialVersionUID = 1L;
    private final Map<Coordinate, X> mapFromCoordinate;
    private final Map<X, Coordinate> mapFromElement;
    /**
     * Create a empty DualMap.
     */
    public DualMapImpl() {
        mapFromCoordinate = new HashMap<>();
        mapFromElement = new HashMap<>();
    }

    @Override
    public void addElemWithCoordinate(final X elem, final Coordinate coor) {
        mapFromCoordinate.put(coor, elem);
        mapFromElement.put(elem, coor);
    }

    @Override
    public Coordinate getCoordinateFromElement(final X elem) {
        return mapFromElement.get(elem);
    }

    @Override
    public X getElemFromCoordinate(final Coordinate coor) {
        for (final Coordinate obj : mapFromCoordinate.keySet()) {
            if (obj.equals(coor)) {
                return mapFromCoordinate.get(obj);
            }
        }
        return null;
    }

    @Override
    public void remove(final X elem) {
        mapFromCoordinate.remove(mapFromElement.get(elem));
        mapFromElement.remove(elem);
    }

    @Override
    public boolean isPresentByCoordinate(final Coordinate coor) {
        for (final Coordinate checkCoordinate : mapFromCoordinate.keySet()) {
            if (coor.equals(checkCoordinate)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isPresentByObject(final X elem) {
        return mapFromElement.containsKey(elem);
    }

    @Override
    public Set<X> getElements() {
        return mapFromElement.keySet();
    }
}
