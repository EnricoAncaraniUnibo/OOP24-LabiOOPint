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
import labioopint.model.powerup.impl.DoubleTurnPowerUp;
import labioopint.model.powerup.impl.InvulnerabilityPowerUp;
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
    private final int numberPlayer;
    private final int numberPowerUp;
    private final EnemyFactory enemyFactory;
    private final EnemyDifficulty type;
    private final TurnManager turn;

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
            return new LabyrinthImpl(SMALL_LABYRINTH, turn);
        } else if (numberPlayer == 4 || numberPlayer == 3) {
            return new LabyrinthImpl(BIG_LABYRINTH, turn);
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
        final List<String> nameList = new ArrayList<>();
        nameList.add("Archer");
        nameList.add("Warrior");
        nameList.add("Thief");
        nameList.add("Mage");
        final Random x = new Random();
        final List<PlayerImpl> tm = new ArrayList<>();
        for (int i = 1; i <= numberPlayer; i++) {
            final int n = x.nextInt(0, nameList.size());
            final PlayerImpl a = new PlayerImpl(nameList.get(n), turn);
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
    public Enemy createEnemy() {
        if (type == EnemyDifficulty.EASY) {
            return enemyFactory.createSingleStepEnemy(turn);
        } else if (type == EnemyDifficulty.MEDIUM) {
            return enemyFactory.createRandomEnemy(turn);
        } else if (type == EnemyDifficulty.HARD) {
            return enemyFactory.createChaseEnemy(turn);
        } else {
            throw new IllegalArgumentException("Enemy Difficulty not properly selected");
        }
    }

    /**
     * Creates a list of power-ups based on the game settings.
     *
     * @return a list of {@link PowerUp} instances
     */
    public List<PowerUp> createPowerUps() {
        final List<PowerUp> powerUps = new ArrayList<>();
        Random r=new Random();
        PowerUp powerUp;
        for (int i = 0; i < numberPowerUp; i++) {
            int value = r.nextInt(3);
            switch (value) {
                case 0:
                    powerUp = new SwapPositionPowerUp(turn);
                    break;
                default:
                    powerUp = new DoubleTurnPowerUp(turn);
                    break;
            }
            
            powerUps.add(powerUp);
        }
        return powerUps;
    }
}
