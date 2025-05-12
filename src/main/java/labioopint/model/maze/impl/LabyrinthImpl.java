package labioopint.model.maze.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import labioopint.controller.api.LabyrinthController;
import labioopint.controller.impl.LabyrinthControllerImpl;
import labioopint.model.api.Coordinate;
import labioopint.model.api.CoordinateGenerator;
import labioopint.model.api.DualMap;
import labioopint.model.api.Pair;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;
import labioopint.model.powerup.impl.DoubleTurnPowerUp;
import labioopint.model.powerup.impl.InvulnerabilityPowerUp;
import labioopint.model.powerup.impl.SwapPositionPowerUp;

/**
 * The LabyrinthImpl class implements the Labyrinth interface and provides
 * the logic for managing the maze, players, enemies, powerups, and their
 * coordinates and interactions.
 */
public final class LabyrinthImpl implements Labyrinth, Serializable {
    private final MazeImpl grid;
    private BlockImpl outsideBlock;
    private DualMap<PowerUp> mapOfPowerUps;
    private DualMap<PlayerImpl> mapOfPlayers;
    private DualMap<Enemy> mapOfEnemy;
    private LabyrinthController labyController;
    private final TurnManager turn;
    private Pair<Boolean, Player> winner;

    @Override
    public void initialize() {
        labyController = new LabyrinthControllerImpl(turn);
        labyController.show();
        mapOfPowerUps = new DualMap<>();
        mapOfPlayers = new DualMap<>();
        mapOfEnemy = new DualMap<>();
        winner = new Pair<>(false, null);
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
        this.initialize();
        grid = new SimpleMazeImpl(size);
        outsideBlock = grid.generate();
        this.start();
    }

    private void start() {
        CoordinateGenerator cg = new CoordinateGenerator(grid.getSize());
        for (final PowerUp pu : turn.getPowerUps()) {
            mapOfPowerUps.addElemWithCoordinate(pu, cg.getRandomCoordinate());
        }
        cg = new CoordinateGenerator(CoordinateGenerator.createBasicSpawnCoordinate(grid.getSize()));
        for (final PlayerImpl p : turn.getPlayers()) {
            mapOfPlayers.addElemWithCoordinate(p, cg.getRandomCoordinate());
        }
        if (turn.getEnemy().getFirst() == Boolean.TRUE) {
            mapOfEnemy.addElemWithCoordinate(turn.getEnemy().getSecond(),
                    CoordinateGenerator.getCentralCoordinate(grid.getSize()));
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
        }
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
        return true;
    }

    public void setBlock(final BlockImpl b, final Coordinate coor) {
        grid.changeCoordinate(coor, b);
    }

    private BlockImpl shiftRow(final Integer number, final Direction d) {
        if (d.equals(Direction.RIGHT)) {
            BlockImpl last = grid.getBlock(new Coordinate(number, 0)).get();
            moveObjectBlock(new Coordinate(number, 0), d);
            BlockImpl saved;
            grid.changeCoordinate(new Coordinate(number, 0), outsideBlock);
            for (int i = 1; i < grid.getSize(); i++) {
                saved = grid.getBlock(new Coordinate(number, i)).get();
                grid.changeCoordinate(new Coordinate(number, i), last);
                last = saved;
            }
            return last;
        } else {
            BlockImpl last = grid.getBlock(new Coordinate(number, grid.getSize() - 1)).get();
            moveObjectBlock(new Coordinate(number, grid.getSize() - 1), d);
            BlockImpl saved;
            grid.changeCoordinate(new Coordinate(number, grid.getSize() - 1), outsideBlock);
            for (int i = grid.getSize() - 2; i >= 0; i--) {
                saved = grid.getBlock(new Coordinate(number, i)).get();
                grid.changeCoordinate(new Coordinate(number, i), last);
                last = saved;
            }
            return last;
        }
    }

    private BlockImpl shiftColumn(final Integer number, final Direction d) {
        if (d.equals(Direction.UP)) {
            BlockImpl last = grid.getBlock(new Coordinate(grid.getSize() - 1, number)).get();
            moveObjectBlock(new Coordinate(grid.getSize() - 1, number), d);
            BlockImpl saved;
            grid.changeCoordinate(new Coordinate(grid.getSize() - 1, number), outsideBlock);
            for (int i = grid.getSize() - 2; i >= 0; i--) {
                saved = grid.getBlock(new Coordinate(i, number)).get();
                grid.changeCoordinate(new Coordinate(i, number), last);
                last = saved;
            }
            return last;
        } else {
            BlockImpl last = grid.getBlock(new Coordinate(0, number)).get();
            moveObjectBlock(new Coordinate(0, number), d);
            BlockImpl saved;
            grid.changeCoordinate(new Coordinate(0, number), outsideBlock);
            for (int i = 1; i < grid.getSize(); i++) {
                saved = grid.getBlock(new Coordinate(i, number)).get();
                grid.changeCoordinate(new Coordinate(i, number), last);
                last = saved;
            }
            return last;
        }
    }

    private void moveObjectBlock(final Coordinate c, final Direction d) {
        final List<PlayerImpl> lp = new ArrayList<>();
        final List<Enemy> le = new ArrayList<>();
        final List<PowerUp> lpu = new ArrayList<>();
        Optional<PlayerImpl> p;
        Optional<Enemy> e;
        Optional<PowerUp> pu;
        Coordinate coor = new Coordinate(c);
        DualMap<PlayerImpl> tempMapOfPlayers = new DualMap<>();
        DualMap<Enemy> tempMapOfEnemy = new DualMap<>();
        DualMap<PowerUp> tempMapOfPowerUps = new DualMap<>();
        for (int j = 0; j < grid.getSize(); j++) {
            p = Optional.ofNullable(mapOfPlayers.getElemFromCoordinate(coor));
            if (p.isPresent() && !lp.contains(p.get())) {
                lp.add(p.get());
                mapOfPlayers.remove(p.get());
                tempMapOfPlayers.addElemWithCoordinate(p.get(), calculateNewCoordinate(coor, d));
            }

            e = Optional.ofNullable(mapOfEnemy.getElemFromCoordinate(coor));
            if (e.isPresent() && !le.contains(e.get())) {
                le.add(e.get());
                mapOfEnemy.remove(e.get());
                tempMapOfEnemy.addElemWithCoordinate(e.get(), calculateNewCoordinate(coor, d));
            }

            pu = Optional.ofNullable(mapOfPowerUps.getElemFromCoordinate(coor));
            if (pu.isPresent() && !lpu.contains(pu.get())) {
                lpu.add(pu.get());
                mapOfPowerUps.remove(pu.get());
                tempMapOfPowerUps.addElemWithCoordinate(pu.get(), calculateNewCoordinate(coor, d));
            }
            coor = calculateNewCoordinate(coor, d);
        }
        for (PowerUp powerUp : tempMapOfPowerUps.getElemets()) {
            mapOfPowerUps.addElemWithCoordinate(powerUp, tempMapOfPowerUps.getCoordinateFromElement(powerUp));
        }
        for (PlayerImpl player : tempMapOfPlayers.getElemets()) {
            mapOfPlayers.addElemWithCoordinate(player, tempMapOfPlayers.getCoordinateFromElement(player));
        }
        for (Enemy en : tempMapOfEnemy.getElemets()) {
            mapOfEnemy.addElemWithCoordinate(en, tempMapOfEnemy.getCoordinateFromElement(en));
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
        if (i.equals(grid.getSize())) {
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
        final List<PowerUp> lpu = new ArrayList<>();
        for (final PowerUp powerUp : turn.getPowerUps()) {
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
        final Coordinate newCoor = calculateNewCoordinate(mapOfPlayers.getCoordinateFromElement(p), dir);
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
            final Optional<Player> pl = checkWinner();
            if (pl.isPresent()) {
                winner = new Pair<Boolean, Player>(true, pl.get());
            }
        }
    }

    @Override
    public MazeImpl getGrid() {
        return grid;
    }

    @Override
    public void rotateOutsideBlock(final Rotation blockRotation) {
        outsideBlock.setRotation(blockRotation);
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
    }

    @Override
    public void addPowerUp(final PowerUp p) {
        final CoordinateGenerator cg = new CoordinateGenerator(grid.getSize());
        boolean repeat = false;
        Coordinate c;
        do {
            repeat = false;
            c = cg.getRandomCoordinate();
            for (PlayerImpl playerImpl : mapOfPlayers.getElemets()) {
                if (c.equals(mapOfPlayers.getCoordinateFromElement(playerImpl))) {
                    repeat = true;
                }
            }
            for (PowerUp po : mapOfPowerUps.getElemets()) {
                if (c.equals(mapOfPowerUps.getCoordinateFromElement(po))) {
                    repeat = true;
                }
            }
            for (Enemy e : mapOfEnemy.getElemets()) {
                if (c.equals(mapOfEnemy.getCoordinateFromElement(e))) {
                    repeat = true;
                }
            }
        } while (repeat);
        mapOfPowerUps.addElemWithCoordinate(p, c);
    }

    @Override
    public void playerUpdateCoordinate(final Player p, final Coordinate coor) {
        mapOfPlayers.remove((PlayerImpl) p);
        mapOfPlayers.addElemWithCoordinate((PlayerImpl) p, coor);
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
    }

    @Override
    public void enemyUpdateCoordinate(final Enemy e, final List<Coordinate> coor) {
        for (final Coordinate coordinate : coor) {
            mapOfEnemy.remove(e);
            mapOfEnemy.addElemWithCoordinate(e, coordinate);
            labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
        }
    }

    private Optional<Player> checkWinner() {
        if (mapOfPowerUps.getElemets().isEmpty()) {
            final List<PlayerImpl> sortedPlayers = turn.getPlayers().stream()
                    .sorted(Comparator.comparing(p -> p.getObjetives().size(), Comparator.reverseOrder()))
                    .collect(Collectors.toList());
            if (sortedPlayers.get(0).getObjetives().size() == sortedPlayers.get(1).getObjetives().size()) {
                Random r = new Random();
                final PowerUp pou;
                int i = turn.getPowerUps().size();
                int value = r.nextInt(3);
                switch (value) {
                    case 0:
                        pou = new SwapPositionPowerUp(turn, i);
                        break;
                    case 1:
                        pou = new DoubleTurnPowerUp(turn, i);
                        break;
                    default:
                        pou = new InvulnerabilityPowerUp(i);
                        break;
                }
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
        if (winner.getFirst()) {
            Optional<Player> pl = Optional.of(winner.getSecond());
            return pl;
        }
        return Optional.empty();

    }

    @Override
    public void powerUpUpdateCoordinate(final PowerUp p, final Coordinate coor) {
        mapOfPowerUps.remove(p);
        mapOfPowerUps.addElemWithCoordinate(p, coor);
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
    }

    public void startView() {
        labyController.show();
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
    }

    public void updateView() {
        labyController.updateGraphics(grid, mapOfPlayers, mapOfEnemy, mapOfPowerUps, outsideBlock);
    }
}
