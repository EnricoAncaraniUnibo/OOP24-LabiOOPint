package labioopint.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import labioopint.model.api.Settings;
import labioopint.model.core.impl.BuilderImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;

/**
 * The {@code BuilderImplTest} class contains unit tests for verifying the
 * behavior of the {@link BuilderImpl} class. It ensures that the builder
 * correctly creates players, enemies, power-ups, and the maze.
 */
class BuilderImplTest {

    private BuilderImpl builder;
    private static TurnManager tm;

    /**
     * Sets up the {@link BuilderImpl} and {@link TurnManager} instances before
     * each test. Initializes the game settings with predefined values.
     */
    @BeforeEach
    void setUp() {
        final Settings settings = new Settings(2, 2, 3, EnemyDifficulty.MEDIUM);
        tm = new TurnManager(settings);
        builder = new BuilderImpl(settings, tm);
    }

    /**
     * Tests the {@link BuilderImpl#createPlayers()} method.
     * Ensures that the correct number of players is created and that all players
     * are not null.
     */
    @Test
    void testCreatePlayers() {
        builder.createPlayers();
        assertEquals(tm.getPlayers().size(), 2);
        final List<PlayerImpl> players = tm.getPlayers();
        for (final PlayerImpl playerImpl : players) {
            assertNotNull(playerImpl);
        }
    }

    /**
     * Tests the {@link BuilderImpl#createEnemy()} method.
     * Ensures that an enemy is created and that it is not null.
     */
    @Test
    void testCreateEnemy() {
        builder.createEnemy();
        assertTrue(tm.getEnemy().getFirst() == Boolean.TRUE);
        final Enemy enemy = tm.getEnemy().getSecond();
        assertNotNull(enemy);
    }

    /**
     * Tests the {@link BuilderImpl#createPowerUps()} method.
     * Ensures that the correct number of power-ups is created and that all
     * power-ups are not null.
     */
    @Test
    void testCreatePowerUps() {
        builder.createPowerUps();
        assertEquals(tm.getPowerUps().size(), 3);
        final List<PowerUp> powerUps = tm.getPowerUps();
        for (final PowerUp power : powerUps) {
            assertNotNull(power);
        }
    }

    /**
     * Tests the {@link BuilderImpl#createMaze()} method.
     * Ensures that the maze is created and is not null.
     */
    @Test
    void testCreateMaze() {
        builder.createMaze();
        assertNotNull(tm.getLab());
    }
}
