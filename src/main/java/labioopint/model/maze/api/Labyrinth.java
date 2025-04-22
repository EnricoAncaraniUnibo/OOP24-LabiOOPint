package labioopint.model.maze.api;

import java.util.List;

import labioopint.model.api.Coordinate;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.impl.MazeImpl;
import labioopint.model.player.api.Player;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;
/**
 * The Labyrinth interface defines the structure and behavior of a labyrinth.
 * It provides methods for managing blocks, players, enemies, power-ups, and
 * their interactions within the labyrinth.
 */
public interface Labyrinth {
	/**
     * Initializes the labyrinth attributes.
     */
    void initialize();
    /**
     * Retrieves the block currently outside the labyrinth.
     *
     * @return the block outside the labyrinth as a BlockImpl
     */
    BlockImpl getOutsideBlock();
    /**
     * Moves a block within the labyrinth in the specified direction.
     *
     * @param c the coordinate of the block to move
     * @param d the direction in which to move the block
     * @return true if the block was successfully moved, false otherwise
     */
    boolean moveBlock(Coordinate c, Direction d);
    /**
     * Retrieves the coordinate of the specified player.
     *
     * @param p the player whose coordinate is to be retrieved
     * @return the coordinate of the player
     */
    Coordinate getPlayerCoordinate(PlayerImpl p);
    /**
     * Retrieves the coordinate of the specified power-up.
     *
     * @param p the power-up whose coordinate is to be retrieved
     * @return the coordinate of the power-up
     */
    Coordinate getPowerUpCoordinate(PowerUp p);
    /**
     * Retrieves the list of all power-ups in the labyrinth.
     *
     * @return a list of power-ups
     */
    List<PowerUp> getListOfPowerUps();
    /**
     * Retrieves the coordinate of the specified enemy.
     *
     * @param e the enemy whose coordinate is to be retrieved
     * @return the coordinate of the enemy
     */
    Coordinate getEnemyCoordinate(Enemy e);
    /**
     * Moves the specified player in the given direction.
     *
     * @param p the player to move
     * @param dir the direction in which to move the player
     */
    void movePlayer(PlayerImpl p, Direction dir);
    /**
     * Updates the absolute coordinate of a player in the labyrinth.
     *
     * @param p the player whose coordinate is to be updated
     * @param coor the new coordinate to assign to the object
     */
    void playerUpdateCoordinate(Player p, Coordinate coor);
    /**
     * Updates the absolute coordinate of an enemy in the labyrinth.
     *
     * @param e the enemy whose coordinate is to be updated
     * @param coor the new coordinate to assign to the object
     */
    void enemyUpdateCoordinate(Enemy e, List<Coordinate> coor);
    /**
     * Retrieves the grid representation of the labyrinth.
     *
     * @return the grid as a MazeImpl
     */
    MazeImpl getGrid();
    /**
     * Rotates the block currently outside the labyrinth to the specified rotation.
     *
     * @param blockRotation the new rotation to apply to the outside block
     */
    void rotateOutsideBlock(Rotation blockRotation);
    /**
     * Adds a power-up to the labyrinth.
     *
     * @param p the power-up to add
     */
    void addPowerUp(PowerUp p);

    void powerUpUpdateCoordinate(PowerUp p, Coordinate coor);

}