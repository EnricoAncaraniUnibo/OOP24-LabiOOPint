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

class BuilderImplTest {

    private BuilderImpl builder;
    private static TurnManager tm;

    @BeforeEach
    void setUp() {
        final Settings settings = new Settings(2, 2, 3, EnemyDifficulty.MEDIUM);
        tm = new TurnManager(settings);
        builder = new BuilderImpl(settings, tm);
    }

    @Test
    void testCreatePlayers() {
        builder.createPlayers();
        assertEquals(tm.getPlayers().size(), 2);
        final List<PlayerImpl> players = tm.getPlayers();
        for (final PlayerImpl playerImpl : players) {
            assertNotNull(playerImpl);
        }
    }

    @Test
    void testCreateEnemy() {
        builder.createEnemy();
        assertTrue(tm.getEnemy().isPresent());
        final Enemy enemy = tm.getEnemy().get();
        assertNotNull(enemy);
    }

    @Test
    void testCreatePowerUps() {
        builder.createPowerUps();
        assertEquals(tm.getPowerUps().size(), 3);
        final List<PowerUp> powerUps = tm.getPowerUps();
        for (final PowerUp power : powerUps) {
            assertNotNull(power);
        }
    }

    @Test
    void testCreateMaze() {
        builder.createMaze();
        assertNotNull(tm.getLab());
    }
}
