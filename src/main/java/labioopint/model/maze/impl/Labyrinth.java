package labioopint.model.maze.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import labioopint.controller.impl.LabyrinthController;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.Coordinate;
import labioopint.model.api.CoordinateGenerator;
import labioopint.model.api.DualMap;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Rotation;
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
        mapOfPowerUps = new DualMap<>();
        mapOfPlayers = new DualMap<>();
        mapOfEnemy = new DualMap<>();
    }

    public Labyrinth(final Integer size) {
        this.Default();
        grid = new SimpleMaze(size);
        outsideBlock = grid.Generate();
        this.start();
    }

    private void start() {
        CoordinateGenerator cg = new CoordinateGenerator(grid.getSize()); 
        for (PowerUp pu : TurnManager.GetPowerUps()) {
            mapOfPowerUps.addElemWithCoordinate(pu, cg.getRandomCoordinate());
        }
        cg = new CoordinateGenerator(CoordinateGenerator.createBasicSpawnCoordinate(grid.getSize()));
        for (Player p : TurnManager.GetPlayers()) {
            mapOfPlayers.addElemWithCoordinate(p, cg.getRandomCoordinate());
        }
        if(TurnManager.GetEnemy().isPresent()) {
            mapOfEnemy.addElemWithCoordinate(TurnManager.GetEnemy().get(), CoordinateGenerator.getCentralCoordinate(grid.getSize()));
        }
        labyController.updateGraphics(grid,mapOfPlayers,mapOfEnemy,mapOfPowerUps,outsideBlock);
    }

    public Block getOutsideBlock() {
        return outsideBlock;
    }

    public boolean moveBlock(final Coordinate c, final Direction d) {
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
        labyController.updateGraphics(grid,mapOfPlayers,mapOfEnemy,mapOfPowerUps,outsideBlock);
        return true;
    }

    private Block shiftRow(final Integer number, final Direction d) {
        if (d.equals(Direction.RIGHT)) {
            Block last = grid.GetBlock(new Coordinate(number, 0));
            moveObjectBlock(new Coordinate(number, 0), d);
            Block Saved;
            grid.ChangeCoordinate(new Coordinate(number,0), outsideBlock);
            for (int i = 1; i < grid.getSize(); i++) {
                Saved = grid.GetBlock(new Coordinate(number,i));
                grid.ChangeCoordinate(new Coordinate(number, i), last);
                last=Saved;
            }
            return last;
        } else {
            Block last = grid.GetBlock(new Coordinate(number, grid.getSize()-1));
            moveObjectBlock(new Coordinate(number, grid.getSize()-1), d);
            Block Saved;
            grid.ChangeCoordinate(new Coordinate(number,grid.getSize()-1), outsideBlock);
            for (int i = grid.getSize()-2; i >= 0; i--) {
                Saved = grid.GetBlock(new Coordinate(number,i));
                grid.ChangeCoordinate(new Coordinate(number, i), last);
                last=Saved;
            }
            return last;
        }
    }

    private Block shiftColumn(final Integer number, final Direction d) {
        if (d.equals(Direction.UP)) {
            Block last = grid.GetBlock(new Coordinate(grid.getSize()-1, number));
            moveObjectBlock(new Coordinate(grid.getSize()-1, number), d);
            Block Saved;
            grid.ChangeCoordinate(new Coordinate(grid.getSize()-1, number), outsideBlock);
            for (int i = grid.getSize()-2; i >= 0; i--) {
                Saved = grid.GetBlock(new Coordinate(i,number));
                grid.ChangeCoordinate(new Coordinate(i, number), last);
                last=Saved;
            }
            return last;
        } else {
            Block last = grid.GetBlock(new Coordinate(0, number));
            moveObjectBlock(new Coordinate(0, number), d);
            Block Saved;
            grid.ChangeCoordinate(new Coordinate(0,number), outsideBlock);
            for (int i = 1; i < grid.getSize(); i++) {
                Saved = grid.GetBlock(new Coordinate(i,number));
                grid.ChangeCoordinate(new Coordinate(i,number), last);
                last=Saved;
            }
            return last;
        }
    }

    private void moveObjectBlock(Coordinate c, Direction d) {
        List<Player> lp = new ArrayList<>();
        List<Enemy> le = new ArrayList<>();
        List<PowerUp> lpu = new ArrayList<>();
        Optional<Player> p;
        Optional<Enemy> e;
        Optional<PowerUp> pu;
        Coordinate coor = new Coordinate(c);
        for(int j=0;j<grid.getSize();j++) {
            p = Optional.ofNullable(mapOfPlayers.getElemFromCoordinate(coor));
            if(p.isPresent()) {
                if(!lp.contains(p.get())) {
                    lp.add(p.get());
                    mapOfPlayers.remove(p.get());
                    mapOfPlayers.addElemWithCoordinate(p.get(), calculateNewCoordinate(coor, d));
                }
            }

            e = Optional.ofNullable(mapOfEnemy.getElemFromCoordinate(coor));
            if(e.isPresent()) {
                if(!le.contains(e.get())) {
                    le.add(e.get());
                    mapOfEnemy.remove(e.get());
                    mapOfEnemy.addElemWithCoordinate(e.get(), calculateNewCoordinate(coor, d));
                }
            }

            pu = Optional.ofNullable(mapOfPowerUps.getElemFromCoordinate(coor));
            if(pu.isPresent()) {
                if(!lpu.contains(pu.get())) {
                    lpu.add(pu.get());
                    mapOfPowerUps.remove(pu.get());
                    mapOfPowerUps.addElemWithCoordinate(pu.get(), calculateNewCoordinate(coor, d));
                }
            }
            coor = calculateNewCoordinate(coor, d);
        }
    }

    private Coordinate calculateNewCoordinate(Coordinate c, Direction d) {
        if(d == Direction.UP) {
            return new Coordinate(overFlowTest(c.getRow()-1), c.getColumn());
        }
        if(d == Direction.DOWN) {
            return new Coordinate(overFlowTest(c.getRow()+1), c.getColumn());
        }
        if(d == Direction.LEFT) {
            return new Coordinate(c.getRow(), overFlowTest(c.getColumn()-1));
        }
        if(d == Direction.RIGHT) {
            return new Coordinate(c.getRow(), overFlowTest(c.getColumn()+1));
        }
        throw new IllegalStateException();
    }

    private Integer overFlowTest(final Integer i) {
        if(i==-1) {
            return grid.getSize()-1;
        }
        if(i==grid.getSize()) {
            return 0;
        }
        return i;
    }

    public Coordinate getPlayerCoordinate(final Player p) {
        return mapOfPlayers.getCoordinateFromElement(p);
    }

    public Coordinate getPowerUp(final PowerUp p) {
        return mapOfPowerUps.getCoordinateFromElement(p);
    }

    public List<PowerUp> getListOfPowerUps() {
        List<PowerUp> lpu = new ArrayList<>();
        for (PowerUp powerUp : TurnManager.GetPowerUps()) {
            if(mapOfPowerUps.isPresentByObject(powerUp)) {
                lpu.add(powerUp);
            }
        }
        return lpu;
    }

    public Coordinate getEnemyCoordinate(final Enemy e) {
        if(mapOfEnemy.isPresentByObject(e)) {
            return mapOfEnemy.getCoordinateFromElement(e);
        }
        return null;
    }

    public void movePlayer(final Player p, final Direction dir) {
        Coordinate newCoor = calculateNewCoordinate(mapOfPlayers.getCoordinateFromElement(p), dir);
        mapOfPlayers.remove(p);
        mapOfPlayers.addElemWithCoordinate(p,newCoor);
        pickUpPowerUp(p,newCoor);
        labyController.updateGraphics(grid,mapOfPlayers,mapOfEnemy,mapOfPowerUps,outsideBlock);
    }

    private void pickUpPowerUp(Player p, Coordinate c) {
        if(mapOfPowerUps.isPresentByCoordinate(c)) {
            mapOfPowerUps.getElemFromCoordinate(c).collect();
            p.addObjective(mapOfPowerUps.getElemFromCoordinate(c));
            mapOfPowerUps.remove(mapOfPowerUps.getElemFromCoordinate(c));
        }
    }

    public void absoluteUpdateCoordinate(final Object o, final Coordinate coor) {
        if(o instanceof Player) {
            Player p = (Player)o;
            mapOfPlayers.remove(p);
            mapOfPlayers.addElemWithCoordinate(p, coor);
        }
        if(o instanceof Enemy) {
            Enemy e = (Enemy)o;
            mapOfEnemy.remove(e);
            mapOfEnemy.addElemWithCoordinate(e, coor);
        }
        labyController.updateGraphics(grid,mapOfPlayers,mapOfEnemy,mapOfPowerUps,outsideBlock);
    }

    public Maze getGrid() {
        return grid;
    }

    public void RotateOutsideBlock(Rotation blockRotation) {
        outsideBlock.setRotation(blockRotation);
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
    }

    public void addPowerUp(PowerUp p) {
        CoordinateGenerator cg = new CoordinateGenerator(grid.getSize());
        mapOfPowerUps.addElemWithCoordinate(p, cg.getRandomCoordinate());
    }
}
