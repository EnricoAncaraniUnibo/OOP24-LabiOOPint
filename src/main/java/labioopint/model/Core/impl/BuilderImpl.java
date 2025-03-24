package labioopint.model.Core.impl;

import java.util.*;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Enemy.api.EnemyDifficulty;
import labioopint.model.Enemy.api.EnemyFactory;
import labioopint.model.Enemy.impl.EnemyFactoryImpl;
import labioopint.model.Enemy.impl.EnemyImpl;
import labioopint.model.api.Settings;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.maze.impl.PowerUp;
import labioopint.model.player.impl.Player;

/**
 * BuilderImpl is responsible for creating various game components such as
 * labyrinths, players, and enemies based on the game settings.
 */
public class BuilderImpl {

    public final static int SMALL_LABYRINTH = 5;
    public final static int BIG_LABYRINTH = 7;
    private int definitiveDimension; // NO USARE VAR PUBBLICHE USA METODO CON GETTER
    private int numberPlayer;
    private EnemyFactory enemyFactory;
    private EnemyDifficulty type;

    // potrebbe avere bisogno di ricevere setting dal costruttore, se è vuoto,
    // setting va passato in qualche modo
    /**
     * Constructs a BuilderImpl instance. The number of players is retrieved
     * from the game settings.
     */
    public BuilderImpl(Settings st) {
        numberPlayer = st.getPlayers();
        enemyFactory = new EnemyFactoryImpl();
        type = st.getEnemyDifficulty();
    }

    /**
     * Creates a labyrinth based on the number of players.
     * 
     * @return a Labyrinth instance with the appropriate dimension.
     * @throws IllegalArgumentException if the number of players is not supported.
     */
    public Labyrinth createMaze(final List<Player> players, final Optional<Enemy> enemy, final List<PowerUp> powerUps) {
        if (numberPlayer == 2) {
            definitiveDimension = SMALL_LABYRINTH;
            Labyrinth labyrint = new Labyrinth(SMALL_LABYRINTH, players, enemy, powerUps);
            getDimension(definitiveDimension);
            return labyrint;
        } else if (numberPlayer == 4) {
            definitiveDimension = BIG_LABYRINTH;
            Labyrinth labyrint = new Labyrinth(BIG_LABYRINTH, players, enemy, powerUps);
            getDimension(definitiveDimension);
            return labyrint;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Creates a list of players based on the number of players.
     * 
     * @return a list of Player instances.
     */
    public List<Player> createPlayers() {
        List<Player> tm = new ArrayList<>();
        for (int i = 1; i <= numberPlayer; i++) {
            Player a = new Player(i);
            tm.add(a);
        }
        return tm;
    }

    /**
     * Creates an enemy based on the game settings.
     * 
     * @return a BaseEnemy instance.
     * @throws Exception
     */
    public Optional<Enemy> createEnemy() throws Exception {
        if (type == EnemyDifficulty.EASY) {
            Optional<Enemy> enemy = Optional.of(enemyFactory.createSingleStepEnemy());
            return enemy;
        } else if (type == EnemyDifficulty.MEDIUM) {
            Optional<Enemy> enemy = Optional.of(enemyFactory.createRandomEnemy());
            return enemy;
        } else if (type == EnemyDifficulty.HARD) {
            Optional<Enemy> enemy = Optional.of(enemyFactory.createChaseEnemy());
            return enemy;
        } else {
            return Optional.empty();
        }
    }

    /**
     * Retrieves the dimension of the labyrinth.
     * 
     * @param dim the dimension to retrieve.
     * @return the dimension of the labyrinth.
     */
    public int getDimension(int dim) {
        return dim;
    }

}
