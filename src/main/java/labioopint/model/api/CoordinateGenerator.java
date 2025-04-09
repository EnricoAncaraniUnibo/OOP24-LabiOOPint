package labioopint.model.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * The CoordinateGenerator class is responsible for generating and managing a list of possible
 * coordinates in a two-dimensional grid. It provides methods to retrieve random coordinates
 * and create specific coordinate patterns.
 */
public class CoordinateGenerator {
    private List<Coordinate> possibleCoordinate;
    /**
     * Constructs a CoordinateGenerator with a grid of the specified size.
     * Excludes the corners of the grid from the list of possible coordinates.
     *
     * @param size the size of the grid
     */
    public CoordinateGenerator(final Integer size) {
        possibleCoordinate = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i != 0 || j != 0) && (i != size - 1 || j != size - 1)) {
                    if ((i != 0 || j != size - 1) && (i != size - 1 || j != 0)) {
                        Coordinate c = new Coordinate(i, j);
                        possibleCoordinate.add(c);
                    }
                }
            }
        }
    }
    /**
     * Constructs a CoordinateGenerator with a predefined list of coordinates.
     *
     * @param coor the list of coordinates to initialize
     */
    public CoordinateGenerator(final List<Coordinate> coor) {
        possibleCoordinate = new ArrayList<>();
        possibleCoordinate.addAll(coor);
    }
    /**
     * Retrieves a random coordinate from the list of possible coordinates
     * and removes it from the list.
     *
     * @return a random Coordinate
     */
    public Coordinate getRandomCoordinate() {
        Random r = new Random();
        Coordinate c = possibleCoordinate.get(r.nextInt(0, possibleCoordinate.size()));
        possibleCoordinate.remove(c);
        return c;
    }
    /**
     * Creates a list of basic spawn coordinates for a grid of the specified size.
     * The spawn coordinates are the four corners of the grid.
     *
     * @param size the size of the grid
     * @return a list of spawn coordinates
     */
    public static List<Coordinate> createBasicSpawnCoordinate(final Integer size) {
        List<Coordinate> ls = new ArrayList<>();
        ls.add(new Coordinate(0, 0));
        ls.add(new Coordinate(0, size - 1));
        ls.add(new Coordinate(size - 1, 0));
        ls.add(new Coordinate(size - 1, size - 1));
        return ls;
    }
    /**
     * Retrieves the central coordinate of a grid with the specified size.
     *
     * @param size the size of the grid
     * @return the central Coordinate
     */
    public static Coordinate getCentralCoordinate(final Integer size) {
        return new Coordinate((Integer) size / 2, (Integer) size / 2);
    }
}
