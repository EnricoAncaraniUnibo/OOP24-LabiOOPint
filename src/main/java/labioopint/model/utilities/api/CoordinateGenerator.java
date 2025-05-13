package labioopint.model.utilities.api;
/**
 * Represents a generator for coordinates in a two-dimensional grid.
 * This interface provides a method to generate a random coordinate.
 */
public interface CoordinateGenerator {
    /**
     * Generates a random coordinate.
     *
     * @return a {@link Coordinate} instance representing the random coordinate
     */
    Coordinate getRandomCoordinate();
}
