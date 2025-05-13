package labioopint.controller.api;

import java.io.Serializable;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.maze.api.Labyrinth;

/**
 * Represents a set of methods to check the validity of entrances in different
 * directions.
*/
public interface DirectionCheck extends Serializable {
    /**
     * Checks if the right entrance at the specified coordinate in the labyrinth is
     * valid.
     *
     * @param coord     the coordinate to check
     * @param labyrinth the labyrinth in which the check is performed
     * @return {@code true} if the right entrance is valid, {@code false} otherwise
     */
    boolean checkRightEntrance(Coordinate coord, Labyrinth labyrinth);

    /**
     * Checks if the left entrance at the specified coordinate in the labyrinth is
     * valid.
     *
     * @param coord     the coordinate to check
     * @param labyrinth the labyrinth in which the check is performed
     * @return {@code true} if the left entrance is valid, {@code false} otherwise
     */
    boolean checkLeftEntrance(Coordinate coord, Labyrinth labyrinth);

    /**
     * Checks if the bottom entrance at the specified coordinate in the labyrinth is
     * valid.
     *
     * @param coord     the coordinate to check
     * @param labyrinth the labyrinth in which the check is performed
     * @return {@code true} if the bottom entrance is valid, {@code false} otherwise
     */
    boolean checkBottomEntrance(Coordinate coord, Labyrinth labyrinth);

    /**
     * Checks if the upper entrance at the specified coordinate in the labyrinth is
     * valid.
     *
     * @param coord     the coordinate to check
     * @param labyrinth the labyrinth in which the check is performed
     * @return {@code true} if the upper entrance is valid, {@code false} otherwise
     */
    boolean checkUpperEntrance(Coordinate coord, Labyrinth labyrinth);
}
