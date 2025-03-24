package labioopint.model.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoordinateGenerator {
    private List<Coordinate> possibleCoordinate;

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

    public CoordinateGenerator(final List<Coordinate> coor) {
        possibleCoordinate = new ArrayList<>();
        possibleCoordinate.addAll(coor);
    }

    public Coordinate getRandomCoordinate() {
        Random r = new Random();
        Coordinate c = possibleCoordinate.get(r.nextInt(0, possibleCoordinate.size()));
        possibleCoordinate.remove(c);
        return c;
    }

    public static List<Coordinate> createBasicSpawnCoordinate(final Integer size) {
        List<Coordinate> ls = new ArrayList<>();
        ls.add(new Coordinate(0, 0));
        ls.add(new Coordinate(0, size - 1));
        ls.add(new Coordinate(size - 1, 0));
        ls.add(new Coordinate(size - 1, size - 1));
        return ls;
    }

    public static Coordinate getCentralCoordinate(final Integer size) {
        return new Coordinate((Integer) size / 2, (Integer) size / 2);
    }
}
