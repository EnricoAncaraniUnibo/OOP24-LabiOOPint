package labioopint.model.enemy.api;

import java.io.Serializable;
import java.util.List;

import labioopint.controller.api.ActionPredicate;
import labioopint.model.utilities.api.Coordinate;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;

/**
 * Represents an enemy in the game.
 * This interface provides methods to define the enemy's behavior, movement, and
 * interactions
 * with players and the labyrinth.
 */
public interface Enemy extends Serializable {

    /**
     * Retrieves the AI logic associated with the enemy.
     *
     * @return the {@link EnemyAI} instance controlling the enemy's behavior
     */
    EnemyAI getEnemyAI();

    /**
     * Moves the enemy based on the provided players, action predicate, and
     * labyrinth.
     *
     * @param players         the list of {@link Player} instances in the game
     * @param actionPredicate the {@link ActionPredicate} to determine valid actions
     * @param labyrinth       the {@link Labyrinth} representing the game maze
     * @return a list of {@link Coordinate} instances representing the enemy's new
     *         position
     */
    List<Coordinate> move(List<Player> players, ActionPredicate actionPredicate, Labyrinth labyrinth);

    /**
     * Handles the event when a player is hit by the enemy.
     *
     * @param players   the list of {@link Player} instances in the game
     * @param labyrinth the {@link Labyrinth} representing the game maze
     */
    void playerHit(List<Player> players, Labyrinth labyrinth);

    /**
     * Retrieves the last player hit by the enemy ID.
     *
     * @return the ID of the last player hit
     */
    String getLastHitID();

    /**
     * Clears the record of the last player hit by the enemy.
     */
    void clearLastHit();

    /**
     * Checks if there is a record of the last player hit by the enemy.
     *
     * @return {@code true} if a last hit is recorded, {@code false} otherwise
     */
    boolean isPresentLastHit();

}
