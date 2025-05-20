package labioopint.model.utilities.api;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
/**
 * Represents a bidirectional mapping between elements and coordinates in a two-dimensional grid.
 * This interface provides methods to add, retrieve, and remove elements and their associated coordinates.
 *
 * @param <X> the type of elements stored in the mapping
 */
public interface DualMap<X> extends Serializable {

    /**
     * Adds an element with its associated coordinate to the mapping.
     *
     * @param elem the element to add
     * @param coor the {@link Coordinate} associated with the element
     */
    void addElemWithCoordinate(X elem, Coordinate coor);

    /**
     * Retrieves the coordinate associated with a given element.
     *
     * @param elem the element whose coordinate is to be retrieved
     * @return the {@link Coordinate} associated with the element, or {@code null} if not present
     */
    Coordinate getCoordinateFromElement(X elem);

    /**
     * Retrieves the element associated with a given coordinate.
     *
     * @param coor the {@link Coordinate} whose associated element is to be retrieved
     * @return the element associated with the coordinate, or {@code null} if not present
     */
    List<X> getElemFromCoordinate(Coordinate coor);

    /**
     * Removes an element and its associated coordinate from the mapping.
     *
     * @param elem the element to remove
     */
    void remove(X elem);

    /**
     * Checks if a coordinate is present in the mapping.
     *
     * @param coor the {@link Coordinate} to check
     * @return {@code true} if the coordinate is present, {@code false} otherwise
     */
    boolean isPresentByCoordinate(Coordinate coor);

    /**
     * Checks if an element is present in the mapping.
     *
     * @param elem the element to check
     * @return {@code true} if the element is present, {@code false} otherwise
     */
    boolean isPresentByObject(X elem);

    /**
     * Retrieves all elements stored in the mapping.
     *
     * @return a {@link Set} of all elements in the mapping
     */
    Set<X> getElements();
}
