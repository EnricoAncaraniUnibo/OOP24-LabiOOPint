package labioopint.controller.impl;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.api.DualMap;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Maze;
import labioopint.model.maze.impl.PowerUp;
import labioopint.model.player.impl.Player;
import labioopint.view.GameView;

public class LabyrinthController {

    private final GameView gtv;

    public LabyrinthController() {
        gtv = new GameView();
    }

    public void updateGraphics(final Maze grid, final DualMap<Player> mapPlayers, final DualMap<Enemy> mapEnemy, final DualMap<PowerUp> mapPowerUps, Block outside) {
        gtv.update(grid,mapPlayers ,mapEnemy,mapPowerUps,outside);
    }
}
