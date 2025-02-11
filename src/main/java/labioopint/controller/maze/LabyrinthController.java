package labioopint.controller.maze;

import labioopint.model.api.DualMap;
import labioopint.model.maze.impl.Maze;
import labioopint.model.player.impl.Player;
import labioopint.view.GameTestView;

public class LabyrinthController {

    private final GameTestView gtv;

    public LabyrinthController() {
        gtv = new GameTestView();
    }

    public void updateGraphics(final Maze grid, final DualMap<Player> mapPlayers, final DualMap<Enemy> mapEnemy, final DualMap<PowerUps> mapPowerUps ) {
        gtv.getGraphicsArea().draw(grid,mapPlayers.getMapFromCoordinate() ,mapEnemy.getMapFromCoordinate(),mapPowerUps.getMapFromCoordinate());
    }

}
