package labioopint.model.core.api;

import java.util.List;

import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.impl.LabyrinthImpl;
import labioopint.model.player.impl.PlayerImpl;

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
