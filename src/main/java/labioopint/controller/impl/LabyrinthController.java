package labioopint.controller.impl;

import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Maze.impl.MazeImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.DualMap;
import labioopint.view.GameView;
/**
 * The LabyrinthController class is responsible for managing the interaction between the game model
 * and the game view. It updates the graphical representation of the game state.
 */
public class LabyrinthController {

    private final GameView gtv;
    /**
     * Constructs a new LabyrinthController and initializes the game view.
     */
    public LabyrinthController(final TurnManager tu) {
        gtv = new GameView(tu);
    }
    /**
     * Updates the graphical representation of the game state.
     *
     * @param grid the maze representing the current state of the labyrinth blocks
     * @param mapPlayers a DualMap containing the players and their positions
     * @param mapEnemy a DualMap containing the enemy and his position
     * @param mapPowerUps a DualMap containing the power-ups and their positions
     * @param outside the block outside the labyrinth
     */
    public void updateGraphics(final MazeImpl grid, final DualMap<PlayerImpl> mapPlayers, final DualMap<Enemy> mapEnemy, final DualMap<PowerUp> mapPowerUps, final BlockImpl outside) {
        gtv.update(grid,mapPlayers ,mapEnemy,mapPowerUps,outside);
    }
}
