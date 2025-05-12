package labioopint.controller.api;

import labioopint.model.api.DualMap;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.impl.MazeImpl;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;

/**
 * The LabyrinthController class is responsible for managing the interaction
 * between the game model
 * and the game view. It updates the graphical representation of the game state.
 */
public interface LabyrinthController {

    /**
     * Updates the graphical representation of the game state
     *
     * @param grid the maze representing the current state of the labyrinth blocks
     * @param mapPlayers a DualMap containing the players and their positions
     * @param mapEnemy a DualMap containing the enemy and his position
     * @param mapPowerUps a DualMap containing the power-ups and their positions
     * @param outside the block outside the labyrinth
     */
    void updateGraphics(MazeImpl grid, DualMap<PlayerImpl> mapPlayers, DualMap<Enemy> mapEnemy,
            DualMap<PowerUp> mapPowerUps, BlockImpl outside);

    /**
     * This method start the game view of the game
     */
    void show();

}
