package labioopint.model.Maze.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import labioopint.model.PowerUp.impl.SwapPositionPowerUp;
import labioopint.model.api.Coordinate;
import labioopint.model.api.CoordinateGenerator;
import labioopint.model.api.DualMap;
/**
 * The LabyrinthImpl class implements the Labyrinth interface and provides
 * the logic for managing the maze, players, enemies, powerups, and their coordinates and interactions.
 */
public final class LabyrinthImpl implements Labyrinth {
    private final MazeImpl grid;
    private BlockImpl outsideBlock;
    private DualMap<PowerUp> mapOfPowerUps;
    private DualMap<PlayerImpl> mapOfPlayers;
    private DualMap<Enemy> mapOfEnemy;
    private LabyrinthController labyController;
    private TurnManager turn;
    private Optional<Player> winner;

    @Override
    public void Initialize() {
        labyController = new LabyrinthController(turn);
        mapOfPowerUps = new DualMap<>();
        mapOfPlayers = new DualMap<>();
        mapOfEnemy = new DualMap<>();
        winner = Optional.empty();
    }
    /**
     * Constructs a LabyrinthImpl with the specified size and TurnManager.
     * Initializes the maze, outside block, and other game elements.
     *
     * @param size the size of the maze
     * @param tu   the TurnManager instance to manage game
     */
    public LabyrinthImpl(final Integer size, final TurnManager tu) {
        turn = tu;
        this.Initialize();
        grid = new SimpleMazeImpl(size);
        outsideBlock = grid.Generate();
        this.start();
    }

    private void start() {
        CoordinateGenerator cg = new CoordinateGenerator(grid.getSize()); 
        for (PowerUp pu : turn.getPowerUps()) {
            mapOfPowerUps.addElemWithCoordinate(pu, cg.getRandomCoordinate());
        }
        cg = new CoordinateGenerator(CoordinateGenerator.createBasicSpawnCoordinate(grid.getSize()));
        for (PlayerImpl p : turn.getPlayers()) {
            mapOfPlayers.addElemWithCoordinate(p, cg.getRandomCoordinate());
        }
        if (turn.getEnemy().isPresent()) {
            mapOfEnemy.addElemWithCoordinate(turn.getEnemy().get(), CoordinateGenerator.getCentralCoordinate(grid.getSize()));
        }
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
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
            default:
                break;
        }
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
        return true;
    }

    public void setBlock(BlockImpl b, Coordinate coor) {
        grid.ChangeCoordinate(coor, b);
    }

    private BlockImpl shiftRow(final Integer number, final Direction d) {
        if (d.equals(Direction.RIGHT)) {
            BlockImpl last = grid.GetBlock(new Coordinate(number, 0)).get();
            moveObjectBlock(new Coordinate(number, 0), d);
            BlockImpl saved;
            grid.ChangeCoordinate(new Coordinate(number, 0), outsideBlock);
            for (int i = 1; i < grid.getSize(); i++) {
                saved = grid.GetBlock(new Coordinate(number, i)).get();
                grid.ChangeCoordinate(new Coordinate(number, i), last);
                last = saved;
            }
            return last;
        } else {
            BlockImpl last = grid.GetBlock(new Coordinate(number, grid.getSize() - 1)).get();
            moveObjectBlock(new Coordinate(number, grid.getSize() - 1), d);
            BlockImpl saved;
            grid.ChangeCoordinate(new Coordinate(number, grid.getSize() - 1), outsideBlock);
            for (int i = grid.getSize() - 2; i >= 0; i--) {
                saved = grid.GetBlock(new Coordinate(number, i)).get();
                grid.ChangeCoordinate(new Coordinate(number, i), last);
                last = saved;
            }
            return last;
        }
    }

    private BlockImpl shiftColumn(final Integer number, final Direction d) {
        if (d.equals(Direction.UP)) {
            BlockImpl last = grid.GetBlock(new Coordinate(grid.getSize() - 1, number)).get();
            moveObjectBlock(new Coordinate(grid.getSize() - 1, number), d);
            BlockImpl saved;
            grid.ChangeCoordinate(new Coordinate(grid.getSize() - 1, number), outsideBlock);
            for (int i = grid.getSize() - 2; i >= 0; i--) {
                saved = grid.GetBlock(new Coordinate(i, number)).get();
                grid.ChangeCoordinate(new Coordinate(i, number), last);
                last = saved;
            }
            return last;
        } else {
            BlockImpl last = grid.GetBlock(new Coordinate(0, number)).get();
            moveObjectBlock(new Coordinate(0, number), d);
            BlockImpl saved;
            grid.ChangeCoordinate(new Coordinate(0, number), outsideBlock);
            for (int i = 1; i < grid.getSize(); i++) {
                saved = grid.GetBlock(new Coordinate(i, number)).get();
                grid.ChangeCoordinate(new Coordinate(i, number), last);
                last = saved;
            }
            return last;
        }
    }

    private void moveObjectBlock(final Coordinate c, final Direction d) {
        List<PlayerImpl> lp = new ArrayList<>();
        List<Enemy> le = new ArrayList<>();
        List<PowerUp> lpu = new ArrayList<>();
        Optional<PlayerImpl> p;
        Optional<Enemy> e;
        Optional<PowerUp> pu;
        Coordinate coor = new Coordinate(c);
        for (int j = 0; j < grid.getSize(); j++) {
            p = Optional.ofNullable(mapOfPlayers.getElemFromCoordinate(coor));
            if (p.isPresent()) {
                if (!lp.contains(p.get())) {
                    lp.add(p.get());
                    mapOfPlayers.remove(p.get());
                    mapOfPlayers.addElemWithCoordinate(p.get(), calculateNewCoordinate(coor, d));
                }
            }

            e = Optional.ofNullable(mapOfEnemy.getElemFromCoordinate(coor));
            if (e.isPresent()) {
                if (!le.contains(e.get())) {
                    le.add(e.get());
                    mapOfEnemy.remove(e.get());
                    mapOfEnemy.addElemWithCoordinate(e.get(), calculateNewCoordinate(coor, d));
                }
            }

            pu = Optional.ofNullable(mapOfPowerUps.getElemFromCoordinate(coor));
            if (pu.isPresent()) {
                if (!lpu.contains(pu.get())) {
                    lpu.add(pu.get());
                    mapOfPowerUps.remove(pu.get());
                    mapOfPowerUps.addElemWithCoordinate(pu.get(), calculateNewCoordinate(coor, d));
                }
            }
            coor = calculateNewCoordinate(coor, d);
        }
    }

    private Coordinate calculateNewCoordinate(final Coordinate c, final Direction d) {
        if (d == Direction.UP) {
            return new Coordinate(overFlowTest(c.getRow() - 1), c.getColumn());
        }
        if (d == Direction.DOWN) {
            return new Coordinate(overFlowTest(c.getRow() + 1), c.getColumn());
        }
        if (d == Direction.LEFT) {
            return new Coordinate(c.getRow(), overFlowTest(c.getColumn() - 1));
        }
        if (d == Direction.RIGHT) {
            return new Coordinate(c.getRow(), overFlowTest(c.getColumn() + 1));
        }
        throw new IllegalStateException();
    }

    private Integer overFlowTest(final Integer i) {
        if (i == -1) {
            return grid.getSize() - 1;
        }
        if (i == grid.getSize()) {
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
        for (PowerUp powerUp : turn.getPowerUps()) {
            if (mapOfPowerUps.isPresentByObject(powerUp)) {
                lpu.add(powerUp);
            }
        }
        return lpu;
    }

    @Override
    public Coordinate getEnemyCoordinate(final Enemy e) {
        if (mapOfEnemy.isPresentByObject(e)) {
            return mapOfEnemy.getCoordinateFromElement(e);
        }
        return null;
    }

    @Override
    public void movePlayer(final PlayerImpl p, final Direction dir) {
        Coordinate newCoor = calculateNewCoordinate(mapOfPlayers.getCoordinateFromElement(p), dir);
        mapOfPlayers.remove(p);
        mapOfPlayers.addElemWithCoordinate(p, newCoor);
        pickUpPowerUp(p, newCoor);
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
    }

    private void pickUpPowerUp(final PlayerImpl p, final Coordinate c) {
        if (mapOfPowerUps.isPresentByCoordinate(c)) {
            mapOfPowerUps.getElemFromCoordinate(c).collect();
            p.addObjective(mapOfPowerUps.getElemFromCoordinate(c));
            mapOfPowerUps.remove(mapOfPowerUps.getElemFromCoordinate(c));
            Optional<Player> pl = checkWinner();
            winner = pl;
        }
    }

    @Override
    public MazeImpl getGrid() {
        return grid;
    }

    @Override
    public void RotateOutsideBlock(final Rotation blockRotation) {
        outsideBlock.setRotation(blockRotation);
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
    }

    @Override
    public void addPowerUp(final PowerUp p) {
        CoordinateGenerator cg = new CoordinateGenerator(grid.getSize());
        mapOfPowerUps.addElemWithCoordinate(p, cg.getRandomCoordinate());
    }

    @Override
    public void PlayerUpdateCoordinate(final Player p, final Coordinate coor) {
        mapOfPlayers.remove((PlayerImpl) p);
        mapOfPlayers.addElemWithCoordinate((PlayerImpl) p, coor);
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
    }

    @Override
    public void EnemyUpdateCoordinate(final Enemy e, final List<Coordinate> coor) {
        for (Coordinate coordinate : coor) {
            mapOfEnemy.remove(e);
            mapOfEnemy.addElemWithCoordinate(e, coordinate);
            labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
        }
    }

    private Optional<Player> checkWinner() {
        if(mapOfPowerUps.getElemets().isEmpty()) {
            List<PlayerImpl> sortedPlayers = turn.getPlayers().stream()
            .sorted(Comparator.comparing(p -> p.getObjetives().size(), Comparator.reverseOrder()))
            .collect(Collectors.toList());
            if (sortedPlayers.get(0).getObjetives().size()==sortedPlayers.get(1).getObjetives().size()) {
                PowerUp pou = new SwapPositionPowerUp(turn);
                turn.addAddictionalPowerUp(pou);
                addPowerUp(pou);
                labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
                return Optional.empty();
            } else {
                return Optional.of(sortedPlayers.get(0));
            }
        }
        return Optional.empty();
    }

    public Optional<Player> getWinner() {
        return winner;
    }
    @Override
    public void PowerUpUpdateCoordinate(PowerUp p, Coordinate coor) {
        mapOfPowerUps.remove((PowerUp) p);
        mapOfPowerUps.addElemWithCoordinate((PowerUp) p, coor);
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
    }
}
