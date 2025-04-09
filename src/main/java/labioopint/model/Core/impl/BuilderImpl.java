package labioopint.model.Core.impl;

import java.util.*;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Enemy.api.EnemyDifficulty;
import labioopint.model.Enemy.api.EnemyFactory;
import labioopint.model.Enemy.impl.EnemyFactoryImpl;
import labioopint.model.Maze.impl.LabyrinthImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.PowerUp.impl.SwapPositionPowerUp;
import labioopint.model.api.Settings;

/**
 * BuilderImpl is responsible for creating various game components such as
 * labyrinths, players, and enemies based on the game settings.
 */
public class BuilderImpl {

    public final static int SMALL_LABYRINTH = 5;
    public final static int BIG_LABYRINTH = 7;
    private int definitiveDimension; // NO USARE VAR PUBBLICHE USA METODO CON GETTER
    private int numberPlayer;
    private int numberPowerUp;
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
        numberPowerUp = st.getPowerUps();
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
            LabyrinthImpl labyrint = new LabyrinthImpl(SMALL_LABYRINTH);
            getDimension(definitiveDimension);
            return labyrint;
        } else if (numberPlayer == 4) {
            definitiveDimension = BIG_LABYRINTH;
            LabyrinthImpl labyrint = new LabyrinthImpl(BIG_LABYRINTH);
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
    public List<PlayerImpl> createPlayers() {
        List<String> nameList = new ArrayList<>();
        nameList.add("Archer");
        nameList.add("Warrior");
        nameList.add("Thief");
        nameList.add("Mage");
        Random x = new Random();
        List<PlayerImpl> tm = new ArrayList<>();
        for (int i = 1; i <= numberPlayer; i++) {
            int n = x.nextInt(0,nameList.size());
            PlayerImpl a = new PlayerImpl(nameList.get(n));
            nameList.remove(n);
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
    public Optional<Enemy> createEnemy(){
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

    public List<PowerUp> createPowerUps() {
        List<PowerUp> powerUps = new ArrayList<>();
        for (int i = 0; i < numberPowerUp; i++) {
            PowerUp powerUp = (PowerUp) new SwapPositionPowerUp();
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
    public int getDimension(int dim) {
        return dim;
    }

}
