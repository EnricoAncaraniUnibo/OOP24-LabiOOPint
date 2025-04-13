package labioopint.model.Maze.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import labioopint.controller.impl.LabyrinthController;
import labioopint.model.Block.api.Rotation;
import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Maze.api.Direction;
import labioopint.model.Maze.api.Labyrinth;
import labioopint.model.Player.api.Player;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.Coordinate;
import labioopint.model.api.CoordinateGenerator;
import labioopint.model.api.DualMap;

public class LabyrinthImpl implements Labyrinth {
    private final MazeImpl grid;
    private BlockImpl outsideBlock;
    private DualMap<PowerUp> mapOfPowerUps;
    private DualMap<PlayerImpl> mapOfPlayers;
    private DualMap<Enemy> mapOfEnemy;
    private LabyrinthController labyController;
    private TurnManager turn;

    @Override
    public void Default() {
        labyController = new LabyrinthController(turn);
        mapOfPowerUps = new DualMap<>();
        mapOfPlayers = new DualMap<>();
        mapOfEnemy = new DualMap<>();
    }

    public LabyrinthImpl(final Integer size, final TurnManager tu) {
        turn = tu;
        this.Default();
        grid = new SimpleMazeImpl(size);
        outsideBlock = grid.Generate();
        this.start();
    }

    private void start() {
        CoordinateGenerator cg = new CoordinateGenerator(grid.getSize()); 
        for (PowerUp pu : turn.GetPowerUps()) {
            mapOfPowerUps.addElemWithCoordinate(pu, cg.getRandomCoordinate());
        }
        cg = new CoordinateGenerator(CoordinateGenerator.createBasicSpawnCoordinate(grid.getSize()));
        for (PlayerImpl p : turn.GetPlayers()) {
            mapOfPlayers.addElemWithCoordinate(p, cg.getRandomCoordinate());
        }
        if(turn.GetEnemy().isPresent()) {
            mapOfEnemy.addElemWithCoordinate(turn.GetEnemy().get(), CoordinateGenerator.getCentralCoordinate(grid.getSize()));
        }
        labyController.updateGraphics(grid,mapOfPlayers,mapOfEnemy,mapOfPowerUps,outsideBlock);
    }

    @Override
    public BlockImpl getOutsideBlock() {
        return outsideBlock;
    }

    @Override
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

    private BlockImpl shiftRow(final Integer number, final Direction d) {
        if (d.equals(Direction.RIGHT)) {
            BlockImpl last = grid.GetBlock(new Coordinate(number, 0)).get();
            moveObjectBlock(new Coordinate(number, 0), d);
            BlockImpl Saved;
            grid.ChangeCoordinate(new Coordinate(number,0), outsideBlock);
            for (int i = 1; i < grid.getSize(); i++) {
                Saved = grid.GetBlock(new Coordinate(number,i)).get();
                grid.ChangeCoordinate(new Coordinate(number, i), last);
                last=Saved;
            }
            return last;
        } else {
            BlockImpl last = grid.GetBlock(new Coordinate(number, grid.getSize()-1)).get();
            moveObjectBlock(new Coordinate(number, grid.getSize()-1), d);
            BlockImpl Saved;
            grid.ChangeCoordinate(new Coordinate(number,grid.getSize()-1), outsideBlock);
            for (int i = grid.getSize()-2; i >= 0; i--) {
                Saved = grid.GetBlock(new Coordinate(number,i)).get();
                grid.ChangeCoordinate(new Coordinate(number, i), last);
                last=Saved;
            }
            return last;
        }
    }

    private BlockImpl shiftColumn(final Integer number, final Direction d) {
        if (d.equals(Direction.UP)) {
            BlockImpl last = grid.GetBlock(new Coordinate(grid.getSize()-1, number)).get();
            moveObjectBlock(new Coordinate(grid.getSize()-1, number), d);
            BlockImpl Saved;
            grid.ChangeCoordinate(new Coordinate(grid.getSize()-1, number), outsideBlock);
            for (int i = grid.getSize()-2; i >= 0; i--) {
                Saved = grid.GetBlock(new Coordinate(i,number)).get();
                grid.ChangeCoordinate(new Coordinate(i, number), last);
                last=Saved;
            }
            return last;
        } else {
            BlockImpl last = grid.GetBlock(new Coordinate(0, number)).get();
            moveObjectBlock(new Coordinate(0, number), d);
            BlockImpl Saved;
            grid.ChangeCoordinate(new Coordinate(0,number), outsideBlock);
            for (int i = 1; i < grid.getSize(); i++) {
                Saved = grid.GetBlock(new Coordinate(i,number)).get();
                grid.ChangeCoordinate(new Coordinate(i,number), last);
                last=Saved;
            }
            return last;
        }
    }

    private void moveObjectBlock(Coordinate c, Direction d) {
        List<PlayerImpl> lp = new ArrayList<>();
        List<Enemy> le = new ArrayList<>();
        List<PowerUp> lpu = new ArrayList<>();
        Optional<PlayerImpl> p;
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

    @Override
    public Coordinate getPlayerCoordinate(final PlayerImpl p) {
        return mapOfPlayers.getCoordinateFromElement(p);
    }

    @Override
    public Coordinate getPowerUpCoordinate(final PowerUp p) {
        return mapOfPowerUps.getCoordinateFromElement(p);
    }

    @Override
    public List<PowerUp> getListOfPowerUps() {
        List<PowerUp> lpu = new ArrayList<>();
        for (PowerUp powerUp : turn.GetPowerUps()) {
            if(mapOfPowerUps.isPresentByObject(powerUp)) {
                lpu.add(powerUp);
            }
        }
        return lpu;
    }

    @Override
    public Coordinate getEnemyCoordinate(final Enemy e) {
        if(mapOfEnemy.isPresentByObject(e)) {
            return mapOfEnemy.getCoordinateFromElement(e);
        }
        return null;
    }

    @Override
    public void movePlayer(final PlayerImpl p, final Direction dir) {
        Coordinate newCoor = calculateNewCoordinate(mapOfPlayers.getCoordinateFromElement(p), dir);
        mapOfPlayers.remove(p);
        mapOfPlayers.addElemWithCoordinate(p,newCoor);
        pickUpPowerUp(p,newCoor);
        labyController.updateGraphics(grid,mapOfPlayers,mapOfEnemy,mapOfPowerUps,outsideBlock);
    }

    private void pickUpPowerUp(PlayerImpl p, Coordinate c) {
        if(mapOfPowerUps.isPresentByCoordinate(c)) {
            mapOfPowerUps.getElemFromCoordinate(c).collect();
            p.addObjective(mapOfPowerUps.getElemFromCoordinate(c));
            mapOfPowerUps.remove(mapOfPowerUps.getElemFromCoordinate(c));
        }
    }

    @Override
    public MazeImpl getGrid() {
        return grid;
    }

    @Override
    public void RotateOutsideBlock(Rotation blockRotation) {
        outsideBlock.setRotation(blockRotation);
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
    }

    @Override
    public void addPowerUp(PowerUp p) {
        CoordinateGenerator cg = new CoordinateGenerator(grid.getSize());
        mapOfPowerUps.addElemWithCoordinate(p, cg.getRandomCoordinate());
    }

    @Override
    public void PlayerUpdateCoordinate(Player p, Coordinate coor) {
        mapOfPlayers.remove((PlayerImpl)p);
        mapOfPlayers.addElemWithCoordinate((PlayerImpl)p, coor);
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
    }

    @Override
    public void EnemyUpdateCoordinate(Enemy e, List<Coordinate> coor) {
        for (Coordinate coordinate : coor) {
            mapOfEnemy.remove(e);
            mapOfEnemy.addElemWithCoordinate(e, coordinate);
            labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
        }
    }
}
