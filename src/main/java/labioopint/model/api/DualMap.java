package labioopint.model.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The DualMap class provides a bidirectional mapping between coordinates and
 * elements.
 * It allows efficient retrieval of elements by coordinates and vice versa.
 *
 * @param <X> the type of elements stored in the map
 */
public final class DualMap<X> {
    private Map<Coordinate, X> mapFromCoordinate;
    private Map<X, Coordinate> mapFromElement;

    /**
     * Constructs a new DualMap with empty mappings.
     */
    public DualMap() {
        mapFromCoordinate = new HashMap<>();
        mapFromElement = new HashMap<>();
    }

    /**
     * Adds an element with its associated coordinate to the map.
     *
     * @param elem the element to add
     * @param coor the coordinate associated with the element
     */
    public void addElemWithCoordinate(final X elem, final Coordinate coor) {
        mapFromCoordinate.put(coor, elem);
        mapFromElement.put(elem, coor);
    }

    /**
     * Retrieves the coordinate associated with the specified element.
     *
     * @param elem the element to look up
     * @return the coordinate associated with the element, or null if not found
     */
    public Coordinate getCoordinateFromElement(final X elem) {
        return mapFromElement.get(elem);
    }

    /**
     * Retrieves the element associated with the specified coordinate.
     *
     * @param coor the coordinate to look up
     * @return the element associated with the coordinate, or null if not found
     */
    public X getElemFromCoordinate(final Coordinate coor) {
        for (Coordinate obj : mapFromCoordinate.keySet()) {
            if (obj.getRow() == coor.getRow() && obj.getColumn() == coor.getColumn()) {
                return mapFromCoordinate.get(obj);
            }
        }
        return null;
    }

    /**
     * Removes the specified element and its associated coordinate from the map.
     *
     * @param elem the element to remove
     */
    public void remove(final X elem) {
        mapFromCoordinate.remove(mapFromElement.get(elem));
        mapFromElement.remove(elem);
    }

    /**
     * Checks if a coordinate is present in the map.
     *
     * @param coor the coordinate to check
     * @return true if the coordinate is present, false otherwise
     */
    public boolean isPresentByCoordinate(final Coordinate coor) {
        for (Coordinate checkCoordinate : mapFromCoordinate.keySet()) {
            if (coor.getRow() == checkCoordinate.getRow() && coor.getColumn() == checkCoordinate.getColumn()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if an element is present in the map.
     *
     * @param elem the element to check
     * @return true if the element is present, false otherwise
     */
    public boolean isPresentByObject(final X elem) {
        if (mapFromElement.containsKey(elem)) {
            return true;
        }
        return false;
    }

    public Set<X> getElemets() {
        return mapFromElement.keySet();
    }
}
