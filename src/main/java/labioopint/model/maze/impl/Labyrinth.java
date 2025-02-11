package labioopint.model.maze.impl;

import java.util.List;

import labioopint.controller.maze.LabyrinthController;
import labioopint.model.api.Coordinate;
import labioopint.model.api.CoordinateGenerator;
import labioopint.model.api.DualMap;
import labioopint.model.maze.api.Direction;
import labioopint.model.player.impl.Player;

public class Labyrinth {
    private final Maze grid;
    private Block outsideBlock;
    private DualMap<PowerUp> mapOfPowerUps;
    private DualMap<Player> mapOfPlayers;
    private DualMap<Enemy> mapOfEnemy;
    private LabyrinthController labyController;

    public void Default() {
        labyController = new LabyrinthController();
    }

    public Labyrinth(final Integer size) {
        this.Default();
        grid = new SimpleMaze(size);
        outsideBlock = grid.Generate();
        this.start();
    }

    public Labyrinth(final Integer size, final Integer corners, final Integer corridors, final Integer crossings) {
        this.Default();
        grid = new ComplexMaze(size, corners, corridors, crossings);
        outsideBlock = grid.Generate();
        this.start();
    }

    private void start() {
        CoordinateGenerator cg = new CoordinateGenerator(grid.getSize());
        List<PowerUp> powerList = setting.getPowerUps();
        for (PowerUp pu : powerList) {
            mapOfPowerUps.addElemWithCoordinate(pu, cg.getRandomCoordinate());
        }
        cg = new CoordinateGenerator(CoordinateGenerator.createBasicSpawnCoordinate(grid.getSize()));
        List<Player> playerList = setting.getPlayers();
        for (Player p : playerList) {
            mapOfPlayers.addElemWithCoordinate(p, cg.getRandomCoordinate());
        }
        List<Enemy> enemyList = setting.getEnemy();
        for (Enemy en : enemyList) {
            mapOfEnemy.addElemWithCoordinate(en, CoordinateGenerator.getCentralabyControlleroordinate(grid.getSize()));
        }
        labyController.updateGraphics();
    }

    private void addPowerUps() {
        CoordinateGenerator cg = new CoordinateGenerator(grid.getSize());
    }

    public Block getOutsideBlock() {
        return outsideBlock;
    }

    public boolean moveBlock(final Coordinate c, final Direction d) {
        Block b = grid.GetBlock(c);
        if (b.IsMovable() == true) {
            switch (d) {
                case UP:
                    outsideBlock = shiftColumn(c.getColumn(), d);
                    break;
                case DOWN:
                    outsideBlock = shiftColumn(c.getColumn(), d);
                    break;
                case RIGHT:
                    outsideBlock = shiftRow(c.getRow(), d);
                    break;
                case LEFT:
                    outsideBlock = shiftRow(c.getRow(), d);
                    break;
            }
            return true;
        } else {
            return false;
        }
    }

    private Block shiftRow(final Integer number, final Direction d) {
        if (d.equals(Direction.RIGHT)) {
            Block outside = grid.GetBlock(new Coordinate(number, grid.getSize() - 1));
            for (int i = grid.getSize() - 2; i >= 0; i--) {
                grid.ChangeCoordinate(new Coordinate(number, i), outside, new Coordinate(number, i + 1));
            }
            grid.ChangeCoordinate(outsideBlock, new Coordinate(number, 0));
            return outside;
        } else {
            Block outside = grid.GetBlock(new Coordinate(number, 0));
            for (int i = 1; i < grid.getSize(); i++) {
                grid.ChangeCoordinate(new Coordinate(number, i), outside, new Coordinate(number, i - 1));
            }
            grid.ChangeCoordinate(outsideBlock, new Coordinate(number, grid.getSize() - 1));
            return outside;
        }
        labyController.updateGraphics();
    }

    private Block shiftColumn(final Integer number, final Direction d) {
        if (d.equals(Direction.UP)) {
            Block outside = grid.GetBlock(new Coordinate(0, number));
            for (int i = 1; i < grid.getSize(); i++) {
                grid.ChangeCoordinate(new Coordinate(i, number), outside, new Coordinate(i - 1, number));
            }
            grid.ChangeCoordinate(outsideBlock, new Coordinate(grid.getSize() - 1, number));
            return outside;
        } else {
            Block outside = grid.GetBlock(new Coordinate(grid.getSize() - 1, number));
            for (int i = grid.getSize() - 2; i >= 0; i--) {
                grid.ChangeCoordinate(new Coordinate(i, number), outside, new Coordinate(i + 1, number));
            }
            grid.ChangeCoordinate(outsideBlock, new Coordinate(0, number));
            return outside;
        }
        labyController.updateGraphics();
    }

    public Coordinate getPlayerCoordinate(final Player p) {
        return mapOfPlayers.getCoordinateFromElement(p);
    }

    public Coordinate getPowerUp(final PowerUp p) {
        return mapOfPowerUps.getCoordinateFromElement(p);
    }

    public Coordinate getEnemyCoordinate(final Enemy e) {
        return mapOfEnemy.getCoordinateFromElement(e);
    }

    public void updateCoordinate(final Object o, final Coordinate c) {
        if (o instanceof Player) {
            mapOfPlayers.remove((Player) o);
            mapOfPlayers.addElemWithCoordinate((Player) o, c);
        }
        if (o instanceof Enemy) {
            mapOfEnemy.remove((Enemy) o);
            mapOfEnemy.addElemWithCoordinate((Enemy) o, c);
        }
        if (o instanceof PowerUp) {
            mapOfPowerUps.remove((PowerUp) o);
        }
        labyController.updateGraphics();
    }
}
