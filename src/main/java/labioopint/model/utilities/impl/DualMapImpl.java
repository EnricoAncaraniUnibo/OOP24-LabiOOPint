package labioopint.model.utilities.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.api.DualMap;

/**
 * The class that map a element to a coordinate and viceversa.
 * 
 * @param <X> the type of element to save with his coordinates
 */
public final class DualMapImpl<X> implements DualMap<X> {
    public static final long serialVersionUID = 1L;
    private final Map<X, Coordinate> mapFromElement;

    /**
     * Create a empty Map.
     */
    public DualMapImpl() {
        mapFromElement = new HashMap<>();
    }

    @Override
    public void addElemWithCoordinate(final X elem, final Coordinate coor) {
        mapFromElement.put(elem, coor);
    }

    @Override
    public Coordinate getCoordinateFromElement(final X elem) {
        return mapFromElement.get(elem);
    }

    @Override
    public List<X> getElemFromCoordinate(final Coordinate coor) {
        final List<X> elems = new ArrayList<>();
        for (final Map.Entry<X, Coordinate> valuEntry : mapFromElement.entrySet()) {
            if (valuEntry.getValue().equals(coor)) {
                elems.add(valuEntry.getKey());
            }
        }
        return elems;
    }

    @Override
    public void remove(final X elem) {
        mapFromElement.remove(elem);
    }

    @Override
    public boolean isPresentByCoordinate(final Coordinate coor) {
        for (final Map.Entry<X, Coordinate> checkCoordinate : mapFromElement.entrySet()) {
            if (coor.equals(checkCoordinate.getValue())) {
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
