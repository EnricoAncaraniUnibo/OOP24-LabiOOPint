package labioopint.model.Labyrinth.api;

import java.util.Map;

import labioopint.commons.Coordinate;

public interface Labyrinth {
    
    Map<Coordinate, Boolean> getLabyrinth();
}
