package labioopint.model.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import labioopint.controller.api.ActionController;
import labioopint.controller.impl.ActionControllerImpl;
import labioopint.model.core.api.Builder;
import labioopint.model.core.api.TurnManager;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.enemy.api.EnemyFactory;
import labioopint.model.enemy.impl.EnemyFactoryImpl;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.maze.impl.LabyrinthImpl;
import labioopint.model.player.api.Player;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;
import labioopint.model.powerup.impl.DoubleTurnPowerUp;
import labioopint.model.powerup.impl.InvulnerabilityPowerUp;
import labioopint.model.powerup.impl.StealObjectPowerUp;
import labioopint.model.powerup.impl.SwapPositionPowerUp;
import labioopint.model.utilities.api.Settings;

/**
 * The class responsable for the creation of the main game components.
 */
public final class BuilderImpl implements Builder {
    /**
     * The size of the labyrinth if the number of players is small.
     */
    public static final int SMALL_LABYRINTH = 5;
    /**
     * The size of the labyrinth if the number of players is high.
     */
    public static final int BIG_LABYRINTH = 7;
    private final int numberPlayer;
    private final int numberPowerUp;
    private final EnemyFactory enemyFactory;
    private final EnemyDifficulty type;
    private final boolean enemyPresent;
    private static final Random RANDOM = new Random();

    /**
     * Construction of the {@code BuilderImpl} by saving the settings of the game.
     * 
     * @param st the settings for the game.
     */
    public BuilderImpl(final Settings st) {
        if (st.getEnemy() == 1) {
            enemyPresent = true;
        } else {
            enemyPresent = false;
        }
        numberPlayer = st.getPlayers();
        enemyFactory = new EnemyFactoryImpl();
        type = st.getEnemyDifficulty();
        numberPowerUp = st.getPowerUps();
    }

    @Override
    public Labyrinth createMaze() {
        int size = SMALL_LABYRINTH;
        if (numberPlayer == 2) {
            size = SMALL_LABYRINTH;
        } else if (numberPlayer == 4 || numberPlayer == 3) {
            size = BIG_LABYRINTH;
        }
        final Labyrinth lab;
        if (enemyPresent) {
            lab = new LabyrinthImpl(size, this.createPlayers(), this.createPowerUps(), this.createEnemy());
        } else {
            lab = new LabyrinthImpl(size, this.createPlayers(), this.createPowerUps());
        }
        return lab;
    }

    /**
     * Method used by the builder to create the players of the game.
     * 
     * @return the list of players.
     */
    private List<Player> createPlayers() {
        final List<String> nameList = new ArrayList<>();
        nameList.add("Archer");
        nameList.add("Warrior");
        nameList.add("Thief");
        nameList.add("Mage");
        final Random x = new Random();
        final List<Player> tm = new ArrayList<>();
        for (int i = 1; i <= numberPlayer; i++) {
            final int n = x.nextInt(0, nameList.size());
            final Player a = new PlayerImpl(nameList.get(n));
            nameList.remove(n);
            tm.add(a);
        }
        return tm;
    }

    @Override
    public TurnManager createTurnManager() {
        return new TurnManagerImpl(numberPlayer);
    }

    /**
     * Method used by the builder to create the enemy of the game.
     * 
     * @return the enemy.
     */
    private Enemy createEnemy() {
        if (type == EnemyDifficulty.EASY) {
            return enemyFactory.createSingleStepEnemy();
        } else if (type == EnemyDifficulty.MEDIUM) {
            return enemyFactory.createRandomEnemy();
        } else if (type == EnemyDifficulty.HARD) {
            return enemyFactory.createChaseEnemy();
        } else {
            throw new IllegalArgumentException("Enemy Difficulty not properly selected");
        }
    }

    /**
     * Method used by the builder to create the powerUps of the game.
     * 
     * @return the list of powerUps.
     */
    private List<PowerUp> createPowerUps() {
        final List<PowerUp> powerUps = new ArrayList<>();
        PowerUp powerUp;
        for (int i = 0; i < numberPowerUp; i++) {
            final int value = RANDOM.nextInt(4);
            switch (value) {
                case 0:
                    powerUp = new SwapPositionPowerUp(i);
                    break;
                case 1:
                    powerUp = new DoubleTurnPowerUp(i);
                    break;
                case 2:
                    powerUp = new InvulnerabilityPowerUp(i);
                    break;
                default:
                    powerUp = new StealObjectPowerUp(i);
                    break;
            }
            powerUps.add(powerUp);
        }
        return powerUps;
    }

    @Override
    public ActionController createActionController() {
        return new ActionControllerImpl();
    }
}
