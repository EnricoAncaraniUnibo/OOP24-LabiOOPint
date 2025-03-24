package labioopint.model.maze.impl;

import labioopint.controller.maze.LabyrinthController;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.Coordinate;
import labioopint.model.api.DualMap;
import labioopint.model.maze.impl.Maze;
import labioopint.model.api.Default;

public class Labyrinth {
    private DualMap<Player> mapOfPlayers;
    private DualMap<Enemy> mapOfEnemy;
    private DualMap<PowerUp> mapOfPowerUps;
    LabyrinthController labyController;
    private final Maze grid;
    private Block outsideBlock;
    
    public void Default() {
        labyController = new LabyrinthController();
        mapOfPowerUps = new DualMap<>();
        mapOfPlayers = new DualMap<>();
        mapOfEnemy = new DualMap<>();
    }
    
    public Labyrinth(final Integer size, final Default set) {
        this.Default();
        grid = new SimpleMaze(size);
        outsideBlock = grid.Generate();
    }

    public Coordinate getPlayerCoordinate(final Player p) {
        return mapOfPlayers.getCoordinateFromElement(p);
    }

    public Coordinate getEnemyCoordinate(final Enemy e) {
        return mapOfEnemy.getCoordinateFromElement(e);
    }

    public Maze getGrid(){
        return grid;
    }
    
}