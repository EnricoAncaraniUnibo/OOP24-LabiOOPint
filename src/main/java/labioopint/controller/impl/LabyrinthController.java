package labioopint.controller.impl;

import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Maze.impl.MazeImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.DualMap;
import labioopint.view.GameView;

public class LabyrinthController {

    private final GameView gtv;

    public LabyrinthController() {
        gtv = new GameView();
    }

    public void updateGraphics(final MazeImpl grid, final DualMap<PlayerImpl> mapPlayers, final DualMap<Enemy> mapEnemy, final DualMap<PowerUp> mapPowerUps, BlockImpl outside) {
        gtv.update(grid,mapPlayers ,mapEnemy,mapPowerUps,outside);
    }
}
