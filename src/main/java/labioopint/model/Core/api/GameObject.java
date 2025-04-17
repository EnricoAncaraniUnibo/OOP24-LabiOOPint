package labioopint.model.Core.api;

import labioopint.model.api.Coordinate;

/**
 * The {@code GameObject} interface represents an object in the game that has a
 * position defined by a {@link Coordinate}.
 */
public interface GameObject {

    /**
     * Retrieves the current coordinate of the game object.
     *
     * @return the {@link Coordinate} of the game object
     */
    Coordinate getCoordinate();

    /**
     * Sets the coordinate of the game object.
     *
     * @param coordinate the new {@link Coordinate} to set
     */
    void setCoordinate(Coordinate coordinate);

}
