package labioopint.model.Core.api;

import java.util.List;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Maze.impl.LabyrinthImpl;
import labioopint.model.Player.impl.PlayerImpl;

/**
 * The {@code Builder} interface defines methods for creating various game
 * components,
 * such as the labyrinth, players, and enemies.
 */
public interface Builder {
    /**
     * Creates a labyrinth for the game.
     *
     * @return a {@link LabyrinthImpl} instance representing the game labyrinth
     */
    LabyrinthImpl createMaze();

    /**
     * Creates a list of players for the game.
     *
     * @return a list of {@link PlayerImpl} instances representing the players
     */
    List<PlayerImpl> createPlayers();

    /**
     * Creates an enemy for the game.
     *
     * @return an {@link Enemy} instance representing the enemy
     */
    Enemy createEnemy();

    /**
     * Retrieves the dimension of the labyrinth.
     *
     * @param dim the dimension to retrieve
     * @return the dimension of the labyrinth
     */
    int getDimension(int dim);
}
