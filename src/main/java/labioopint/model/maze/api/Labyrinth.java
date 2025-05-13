package labioopint.model.maze.api;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.api.Pair;
import labioopint.model.block.api.Block;
import labioopint.model.block.api.Rotation;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;
/**
 * Represents the labyrinth in the game.
 * This interface provides methods to manage the labyrinth's structure, 
 * player and enemy movements, power-ups, and game state.
 */
public interface Labyrinth extends Serializable {

    /**
     * Initializes the labyrinth, setting up its initial state.
     */
    void initialize();

    /**
     * Retrieves the block currently outside the labyrinth.
     *
     * @return the {@link Block} outside the labyrinth
     */
    Block getOutsideBlock();

    /**
     * Moves a block within the labyrinth in the specified direction.
     *
     * @param c the {@link Coordinate} of the block to move
     * @param d the {@link Direction} in which to move the block
     * @return {@code true} if the block was successfully moved, {@code false} otherwise
     */
    boolean moveBlock(Coordinate c, Direction d);

    /**
     * Sets a block at the specified coordinate in the labyrinth.
     *
     * @param b the {@link Block} to set
     * @param coor the {@link Coordinate} where the block will be placed
     */
    void setBlock(Block b, Coordinate coor);

    /**
     * Retrieves the coordinate of the specified player.
     *
     * @param p the {@link Player} whose coordinate is to be retrieved
     * @return the {@link Coordinate} of the player
     */
    Coordinate getPlayerCoordinate(Player p);

    /**
     * Retrieves the coordinate of the specified power-up.
     *
     * @param p the {@link PowerUp} whose coordinate is to be retrieved
     * @return the {@link Coordinate} of the power-up
     */
    Coordinate getPowerUpCoordinate(PowerUp p);

    /**
     * Retrieves a list of power-ups that have not been collected.
     *
     * @return a list of {@link PowerUp} instances not yet collected
     */
    List<PowerUp> getPowerUpsNotCollected();

    /**
     * Retrieves the coordinate of the specified enemy.
     *
     * @param e the {@link Enemy} whose coordinate is to be retrieved
     * @return the {@link Coordinate} of the enemy
     */
    Coordinate getEnemyCoordinate(Enemy e);

    /**
     * Moves the specified player in the given direction.
     *
     * @param p the {@link Player} to move
     * @param dir the {@link Direction} in which to move the player
     */
    void movePlayer(Player p, Direction dir);

    /**
     * Retrieves the grid representing the labyrinth.
     *
     * @return the {@link Maze} representing the labyrinth's grid
     */
    Maze getGrid();

    /**
     * Rotates the block currently outside the labyrinth.
     *
     * @param blockRotation the {@link Rotation} to apply to the block
     */
    void rotateOutsideBlock(Rotation blockRotation);

    /**
     * Adds a power-up to the labyrinth.
     *
     * @param p the {@link PowerUp} to add
     */
    void addPowerUp(PowerUp p);

    /**
     * Updates the coordinate of the specified player.
     *
     * @param p the {@link Player} whose coordinate is to be updated
     * @param coor the new {@link Coordinate} of the player
     */
    void playerUpdateCoordinate(Player p, Coordinate coor);

    /**
     * Updates the coordinates of the specified enemy.
     *
     * @param e the {@link Enemy} whose coordinates are to be updated
     * @param coor a list of {@link Coordinate} instances representing the enemy's new positions
     */
    void enemyUpdateCoordinate(Enemy e, List<Coordinate> coor);

    /**
     * Updates the coordinate of the specified power-up.
     *
     * @param p the {@link PowerUp} whose coordinate is to be updated
     * @param coor the new {@link Coordinate} of the power-up
     */
    void powerUpUpdateCoordinate(PowerUp p, Coordinate coor);

    /**
     * Retrieves the list of players in the labyrinth.
     *
     * @return a list of {@link Player} instances in the labyrinth
     */
    List<Player> getPlayers();

    /**
     * Retrieves information about the presence of an enemy in the labyrinth.
     *
     * @return a {@link Pair} containing a boolean indicating the presence of an enemy
     *         and the {@link Enemy} instance if present
     */
    Pair<Boolean, Enemy> getEnemy();

    /**
     * Retrieves the list of power-ups in the game.
     *
     * @return a list of {@link PowerUp} instances that are objectives
     */
    List<PowerUp> getObjectives();

    /**
     * Removes a power-up collected by a player from the player.
     *
     * @param p the {@link Player} who collected the power-up
     * @param pou the {@link PowerUp} to remove
     */
    void removePlayerObject(Player p, PowerUp pou);

    /**
     * Checks if there is a winner in the game.
     *
     * @return an {@link Optional} containing the winning {@link Player}, if any
     */
    Optional<Player> checkWinner();

}
