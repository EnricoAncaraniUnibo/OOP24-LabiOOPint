package labioopint.model.maze.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.api.CoordinateGenerator;
import labioopint.model.utilities.api.DualMap;
import labioopint.model.utilities.api.Pair;
import labioopint.model.utilities.impl.CoordinateGeneratorImpl;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.utilities.impl.DualMapImpl;
import labioopint.model.utilities.impl.PairImpl;
import labioopint.model.block.api.Block;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.maze.api.Maze;
import labioopint.model.player.api.Player;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;
import labioopint.model.powerup.impl.CorridorToPowerUpPowerUp;
import labioopint.model.powerup.impl.DoubleTurnPowerUp;
import labioopint.model.powerup.impl.InvulnerabilityPowerUp;
import labioopint.model.powerup.impl.StealObjectPowerUp;
import labioopint.model.powerup.impl.SwapPositionPowerUp;

/**
 * The class that work as a coordinate manager for the game.
 */
public final class LabyrinthImpl implements Labyrinth {
    public static final long serialVersionUID = 1L;
    private final Maze grid;
    private Block outsideBlock;
    private DualMap<PowerUp> mapOfPowerUps;
    private DualMap<Player> mapOfPlayers;
    private DualMap<Enemy> mapOfEnemy;
    private List<PowerUp> objectives;
    private static final Random RANDOM = new Random();

    @Override
    public void initialize() {
        mapOfPowerUps = new DualMapImpl<>();
        mapOfPlayers = new DualMapImpl<>();
        mapOfEnemy = new DualMapImpl<>();
        objectives = new ArrayList<>();
    }

    /**
     * Construct with the presence of an enemy.
     * 
     * @param size     the size of the maze
     * @param players  the players in the maze
     * @param powerUps the powerUps in the maze
     * @param enemy    the enemy in the maze
     */
    public LabyrinthImpl(final Integer size, final List<Player> players, final List<PowerUp> powerUps,
            final Enemy enemy) {
        this.initialize();
        grid = new SimpleMazeImpl(size);
        outsideBlock = grid.generate();
        this.start(players, powerUps, enemy, true);
    }

    /**
     * Construct without the presence of an enemy.
     * 
     * @param size     the size of the maze
     * @param players  the players in the maze
     * @param powerUps the powerUps in the maze
     */
    public LabyrinthImpl(final Integer size, final List<Player> players, final List<PowerUp> powerUps) {
        this.initialize();
        grid = new SimpleMazeImpl(size);
        outsideBlock = grid.generate();
        this.start(players, powerUps, null, false);
    }

    private void start(final List<Player> players, final List<PowerUp> powerUps, final Enemy enemy,
            final boolean enemyPresent) {
        CoordinateGenerator cg = new CoordinateGeneratorImpl(grid.getSize());
        for (final PowerUp pu : powerUps) {
            mapOfPowerUps.addElemWithCoordinate(pu, cg.getRandomCoordinate());
            objectives.add(pu);
        }
        cg = new CoordinateGeneratorImpl(CoordinateGeneratorImpl.createBasicSpawnCoordinate(grid.getSize()));
        for (final Player p : players) {
            mapOfPlayers.addElemWithCoordinate(p, cg.getRandomCoordinate());
        }
        if (enemyPresent == Boolean.TRUE) {
            mapOfEnemy.addElemWithCoordinate(enemy,
                    CoordinateGeneratorImpl.getCentralCoordinate(grid.getSize()));
        }
    }

    @Override
    public Block getOutsideBlock() {
        return new BlockImpl(outsideBlock);
    }

    @Override
    public boolean moveBlock(final Coordinate c, final Direction d) {
        if (d == Direction.UP || d == Direction.DOWN) {
            outsideBlock = shiftColumn(c.getColumn(), d);
        } else {
            outsideBlock = shiftRow(c.getRow(), d);
        }
        return true;
    }

    @Override
    public void setBlock(final Block b, final Coordinate coor) {
        grid.changeCoordinate(coor, b);
    }

    private Block shiftRow(final Integer number, final Direction d) {
        if (d.equals(Direction.RIGHT)) {
            Block last = grid.getBlock(new CoordinateImpl(number, 0)).get();
            moveObjectBlock(new CoordinateImpl(number, 0), d);
            Block saved;
            grid.changeCoordinate(new CoordinateImpl(number, 0), outsideBlock);
            for (int i = 1; i < grid.getSize(); i++) {
                saved = grid.getBlock(new CoordinateImpl(number, i)).get();
                grid.changeCoordinate(new CoordinateImpl(number, i), last);
                last = saved;
            }
            return last;
        } else {
            Block last = grid.getBlock(new CoordinateImpl(number, grid.getSize() - 1)).get();
            moveObjectBlock(new CoordinateImpl(number, grid.getSize() - 1), d);
            Block saved;
            grid.changeCoordinate(new CoordinateImpl(number, grid.getSize() - 1), outsideBlock);
            for (int i = grid.getSize() - 2; i >= 0; i--) {
                saved = grid.getBlock(new CoordinateImpl(number, i)).get();
                grid.changeCoordinate(new CoordinateImpl(number, i), last);
                last = saved;
            }
            return last;
        }
    }

    private Block shiftColumn(final Integer number, final Direction d) {
        if (d.equals(Direction.UP)) {
            Block last = grid.getBlock(new CoordinateImpl(grid.getSize() - 1, number)).get();
            moveObjectBlock(new CoordinateImpl(grid.getSize() - 1, number), d);
            Block saved;
            grid.changeCoordinate(new CoordinateImpl(grid.getSize() - 1, number), outsideBlock);
            for (int i = grid.getSize() - 2; i >= 0; i--) {
                saved = grid.getBlock(new CoordinateImpl(i, number)).get();
                grid.changeCoordinate(new CoordinateImpl(i, number), last);
                last = saved;
            }
            return last;
        } else {
            Block last = grid.getBlock(new CoordinateImpl(0, number)).get();
            moveObjectBlock(new CoordinateImpl(0, number), d);
            Block saved;
            grid.changeCoordinate(new CoordinateImpl(0, number), outsideBlock);
            for (int i = 1; i < grid.getSize(); i++) {
                saved = grid.getBlock(new CoordinateImpl(i, number)).get();
                grid.changeCoordinate(new CoordinateImpl(i, number), last);
                last = saved;
            }
            return last;
        }
    }

    private void moveObjectBlock(final Coordinate c, final Direction d) {
        final List<Player> lp = new ArrayList<>();
        final List<Enemy> le = new ArrayList<>();
        final List<PowerUp> lpu = new ArrayList<>();
        List<Player> p;
        List<Enemy> e;
        List<PowerUp> pu;
        Coordinate coor = new CoordinateImpl(c);
        final DualMap<Player> tempMapOfPlayers = new DualMapImpl<>();
        final DualMap<Enemy> tempMapOfEnemy = new DualMapImpl<>();
        final DualMap<PowerUp> tempMapOfPowerUps = new DualMapImpl<>();
        for (int j = 0; j < grid.getSize(); j++) {
            p = mapOfPlayers.getElemFromCoordinate(coor);
            for (final Player pl : p) {
                if (!lp.contains(pl)) {
                    lp.add(pl);
                    mapOfPlayers.remove(pl);
                    tempMapOfPlayers.addElemWithCoordinate(pl, calculateNewCoordinate(coor, d));
                }
            }
            e = mapOfEnemy.getElemFromCoordinate(coor);
            if (!e.isEmpty() && !le.contains(e.get(0))) {
                le.add(e.get(0));
                mapOfEnemy.remove(e.get(0));
                tempMapOfEnemy.addElemWithCoordinate(e.get(0), calculateNewCoordinate(coor, d));
            }
            pu = mapOfPowerUps.getElemFromCoordinate(coor);
            for (final PowerUp pou : pu) {
                if (!lpu.contains(pou)) {
                    lpu.add(pou);
                    mapOfPowerUps.remove(pou);
                    tempMapOfPowerUps.addElemWithCoordinate(pou, calculateNewCoordinate(coor, d));
                }
            }
            coor = calculateNewCoordinate(coor, d);
        }
        for (final PowerUp powerUp : tempMapOfPowerUps.getElements()) {
            mapOfPowerUps.addElemWithCoordinate(powerUp, tempMapOfPowerUps.getCoordinateFromElement(powerUp));
        }
        for (final Player player : tempMapOfPlayers.getElements()) {
            mapOfPlayers.addElemWithCoordinate(player, tempMapOfPlayers.getCoordinateFromElement(player));
        }
        for (final Enemy en : tempMapOfEnemy.getElements()) {
            mapOfEnemy.addElemWithCoordinate(en, tempMapOfEnemy.getCoordinateFromElement(en));
        }
    }

    private Coordinate calculateNewCoordinate(final Coordinate c, final Direction d) {
        if (d == Direction.UP) {
            return new CoordinateImpl(overFlowTest(c.getRow() - 1), c.getColumn());
        }
        if (d == Direction.DOWN) {
            return new CoordinateImpl(overFlowTest(c.getRow() + 1), c.getColumn());
        }
        if (d == Direction.LEFT) {
            return new CoordinateImpl(c.getRow(), overFlowTest(c.getColumn() - 1));
        }
        if (d == Direction.RIGHT) {
            return new CoordinateImpl(c.getRow(), overFlowTest(c.getColumn() + 1));
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
    public Coordinate getPlayerCoordinate(final Player p) {
        return mapOfPlayers.getCoordinateFromElement(p);
    }

    @Override
    public Coordinate getPowerUpCoordinate(final PowerUp p) {
        return mapOfPowerUps.getCoordinateFromElement(p);
    }

    @Override
    public List<PowerUp> getPowerUpsNotCollected() {
        return mapOfPowerUps.getElements().stream().toList();
    }

    @Override
    public Coordinate getEnemyCoordinate(final Enemy e) {
        if (mapOfEnemy.isPresentByObject(e)) {
            return mapOfEnemy.getCoordinateFromElement(e);
        }
        return null;
    }

    @Override
    public void movePlayer(final Player p, final Direction dir) {
        final Coordinate newCoor = calculateNewCoordinate(mapOfPlayers.getCoordinateFromElement(p), dir);
        mapOfPlayers.remove(p);
        mapOfPlayers.addElemWithCoordinate(p, newCoor);
        pickUpPowerUp(p, newCoor);
    }

    private void pickUpPowerUp(final Player p, final Coordinate c) {
        if (mapOfPowerUps.isPresentByCoordinate(c)) {
            for (final PowerUp powerUp : mapOfPowerUps.getElemFromCoordinate(c)) {
                powerUp.collect();
                p.addObjective(powerUp);
                mapOfPowerUps.remove(powerUp);
            }
        }
    }

    @Override
    public Maze getGrid() {
        return new SimpleMazeImpl(grid);
    }

    @Override
    public void rotateOutsideBlock(final Rotation blockRotation) {
        outsideBlock.setRotation(blockRotation);
    }

    @Override
    public void addPowerUp(final PowerUp p) {
        final CoordinateGenerator cg = new CoordinateGeneratorImpl(grid.getSize());
        boolean repeat;
        Coordinate c;
        do {
            repeat = false;
            c = cg.getRandomCoordinate();
            for (final Player playerImpl : mapOfPlayers.getElements()) {
                if (c.equals(mapOfPlayers.getCoordinateFromElement(playerImpl))) {
                    repeat = true;
                }
            }
            for (final PowerUp po : mapOfPowerUps.getElements()) {
                if (c.equals(mapOfPowerUps.getCoordinateFromElement(po))) {
                    repeat = true;
                }
            }
            for (final Enemy e : mapOfEnemy.getElements()) {
                if (c.equals(mapOfEnemy.getCoordinateFromElement(e))) {
                    repeat = true;
                }
            }
        } while (repeat);
        mapOfPowerUps.addElemWithCoordinate(p, c);
        boolean present = false;
        if (objectives.contains(p)) {
            present = true;
        }
        if (!present) {
            objectives.add(p);
        }
    }

    @Override
    public void playerUpdateCoordinate(final Player p, final Coordinate coor) {
        mapOfPlayers.remove(p);
        mapOfPlayers.addElemWithCoordinate((PlayerImpl) p, coor);
    }

    @Override
    public void enemyUpdateCoordinate(final Enemy e, final List<Coordinate> coor) {
        for (final Coordinate coordinate : coor) {
            mapOfEnemy.remove(e);
            mapOfEnemy.addElemWithCoordinate(e, coordinate);
            e.playerHit(getPlayers(), this);
        }
    }

    @Override
    public void powerUpUpdateCoordinate(final PowerUp p, final Coordinate coor) {
        mapOfPowerUps.remove(p);
        mapOfPowerUps.addElemWithCoordinate(p, coor);
    }

    @Override
    public List<Player> getPlayers() {
        return mapOfPlayers.getElements().stream().sorted(new Comparator<>() {

            @Override
            public int compare(final Player o1, final Player o2) {
                return o1.getID().compareTo(o2.getID());
            }

        }).toList();
    }

    @Override
    public Pair<Boolean, Enemy> getEnemy() {
        if (mapOfEnemy.getElements().isEmpty()) {
            return new PairImpl<>(false, null);
        }
        return new PairImpl<>(true, mapOfEnemy.getElements().stream().toList().get(0));
    }

    @Override
    public List<PowerUp> getObjectives() {
        return List.copyOf(objectives);
    }

    @Override
    public void removePlayerObject(final Player p, final PowerUp pou) {
        p.removeObjectiveSelect(pou);
    }

    @Override
    public Optional<Player> checkWinner() {
        if (getPowerUpsNotCollected().isEmpty()) {
            final List<Player> sortedPlayers = getPlayers().stream()
                    .sorted(Comparator.comparing(p -> p.getObjetives().size(), Comparator.reverseOrder()))
                    .collect(Collectors.toList());
            if (sortedPlayers.get(0).getObjetives().size() == sortedPlayers.get(1).getObjetives().size()) {
                final PowerUp pou;
                final int i = getObjectives().size();
                final int value = RANDOM.nextInt(5);
                switch (value) {
                    case 0:
                        pou = new SwapPositionPowerUp(i);
                        break;
                    case 1:
                        pou = new DoubleTurnPowerUp(i);
                        break;
                    case 2:
                        pou = new InvulnerabilityPowerUp(i);
                        break;
                    case 3:
                        pou = new StealObjectPowerUp(i);
                        break;
                    default:
                        pou = new CorridorToPowerUpPowerUp(i);
                        break;
                }
                addPowerUp(pou);
                return Optional.empty();
            } else {
                return Optional.of(sortedPlayers.get(0));
            }
        }
        return Optional.empty();
    }

}
