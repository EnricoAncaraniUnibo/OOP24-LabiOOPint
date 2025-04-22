package labioopint.model.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import labioopint.model.api.Settings;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.enemy.api.EnemyFactory;
import labioopint.model.enemy.impl.EnemyFactoryImpl;
import labioopint.model.maze.impl.LabyrinthImpl;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;
import labioopint.model.powerup.impl.SwapPositionPowerUp;

/**
 * BuilderImpl is responsible for creating various game components such as
 * labyrinths, players, and enemies based on the game settings.
 */
public class BuilderImpl {

    /**
     * The dimension of a small labyrinth.
     */
    public static final int SMALL_LABYRINTH = 5;
    /**
     * The dimension of a big labyrinth.
     */
    public static final int BIG_LABYRINTH = 7;
    private int definitiveDimension;
    private int numberPlayer;
    private int numberPowerUp;
    private EnemyFactory enemyFactory;
    private EnemyDifficulty type;
    private TurnManager turn;

    /**
     * Constructs a BuilderImpl instance. The number of players, enemy difficulty,
     * and number of power-ups are retrieved from the game settings.
     *
     * @param st the game settings containing the configuration for the game
     * @param tu the TurnManager used to manage game state
     */
    public BuilderImpl(final Settings st, final TurnManager tu) {
        numberPlayer = st.getPlayers();
        enemyFactory = new EnemyFactoryImpl();
        type = st.getEnemyDifficulty();
        numberPowerUp = st.getPowerUps();
        turn = tu;
    }

    /**
     * Creates a labyrinth based on the number of players.
     * 
     * @return a Labyrinth instance with the appropriate dimension.
     * @throws IllegalArgumentException if the number of players is not supported.
     */
    public LabyrinthImpl createMaze() {
        if (numberPlayer == 2) {
            definitiveDimension = SMALL_LABYRINTH;
            LabyrinthImpl labyrint = new LabyrinthImpl(SMALL_LABYRINTH, turn);
            getDimension(definitiveDimension);
            return labyrint;
        } else if (numberPlayer == 4) {
            definitiveDimension = BIG_LABYRINTH;
            LabyrinthImpl labyrint = new LabyrinthImpl(BIG_LABYRINTH, turn);
            getDimension(definitiveDimension);
            return labyrint;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Creates a list of players based on the number of players.
     *
     * @return a list of {@link PlayerImpl} instances
     */
    public List<PlayerImpl> createPlayers() {
        List<String> nameList = new ArrayList<>();
        nameList.add("Archer");
        nameList.add("Warrior");
        nameList.add("Thief");
        nameList.add("Mage");
        Random x = new Random();
        List<PlayerImpl> tm = new ArrayList<>();
        for (int i = 1; i <= numberPlayer; i++) {
            int n = x.nextInt(0, nameList.size());
            PlayerImpl a = new PlayerImpl(nameList.get(n), turn);
            nameList.remove(n);
            tm.add(a);
        }
        return tm;
    }

    /**
     * Creates an enemy based on the game settings.
     *
     * @return an {@link Optional} containing the created {@link Enemy}, or empty if
     *         no enemy is created
     */
    public Optional<Enemy> createEnemy() {
        if (type == EnemyDifficulty.EASY) {
            Optional<Enemy> enemy = Optional.of(enemyFactory.createSingleStepEnemy(turn));
            return enemy;
        } else if (type == EnemyDifficulty.MEDIUM) {
            Optional<Enemy> enemy = Optional.of(enemyFactory.createRandomEnemy(turn));
            return enemy;
        } else if (type == EnemyDifficulty.HARD) {
            Optional<Enemy> enemy = Optional.of(enemyFactory.createChaseEnemy(turn));
            return enemy;
        } else {
            return Optional.empty();
        }
    }

    /**
     * Creates a list of power-ups based on the game settings.
     *
     * @return a list of {@link PowerUp} instances
     */
    public List<PowerUp> createPowerUps() {
        List<PowerUp> powerUps = new ArrayList<>();
        for (int i = 0; i < numberPowerUp; i++) {
            PowerUp powerUp = (PowerUp) new SwapPositionPowerUp(turn);
            powerUps.add(powerUp);
        }
        return powerUps;
    }

    /**
     * Retrieves the dimension of the labyrinth.
     * 
     * @param dim the dimension to retrieve.
     * @return the dimension of the labyrinth.
     */
    public int getDimension(final int dim) {
        return dim;
    }

}
