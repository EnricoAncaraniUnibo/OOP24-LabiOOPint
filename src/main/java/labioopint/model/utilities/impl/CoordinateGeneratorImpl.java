package labioopint.model.utilities.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.api.CoordinateGenerator;

public final class CoordinateGeneratorImpl implements CoordinateGenerator {
    private final List<Coordinate> possibleCoordinate;

    public CoordinateGeneratorImpl(final Integer size) {
        possibleCoordinate = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i != 0 || j != 0) && (i != size - 1 || j != size - 1)
                        && (i != 0 || j != size - 1) && (i != size - 1 || j != 0)) {
                    final Coordinate c = new CoordinateImpl(i, j);
                    possibleCoordinate.add(c);
                }
            }
        }
    }

    public CoordinateGeneratorImpl(final List<Coordinate> coor) {
        possibleCoordinate = new ArrayList<>();
        possibleCoordinate.addAll(coor);
    }

    @Override
    public Coordinate getRandomCoordinate() {
        final Random r = new Random();
        final Coordinate c = possibleCoordinate.get(r.nextInt(0, possibleCoordinate.size()));
        possibleCoordinate.remove(c);
        return c;
    }

    public static List<Coordinate> createBasicSpawnCoordinate(final Integer size) {
        final List<Coordinate> ls = new ArrayList<>();
        ls.add(new CoordinateImpl(0, 0));
        ls.add(new CoordinateImpl(0, size - 1));
        ls.add(new CoordinateImpl(size - 1, 0));
        ls.add(new CoordinateImpl(size - 1, size - 1));
        return ls;
    }

    public static Coordinate getCentralCoordinate(final Integer size) {
        return new CoordinateImpl(size / 2, size / 2);
    }
}
