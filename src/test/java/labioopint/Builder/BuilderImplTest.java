package labioopint.Builder;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import labioopint.model.Core.impl.BuilderImpl;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Enemy.api.EnemyDifficulty;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.Settings;

public class BuilderImplTest {

    private BuilderImpl builder;
    private static TurnManager tm;

    @BeforeEach
    void setUp() {
        Settings settings = new Settings(2, 2, 3, EnemyDifficulty.MEDIUM);
        tm = new TurnManager(settings);
        builder = new BuilderImpl(settings, tm);
    }

    @Test
    void testCreatePlayers() {
        builder.createPlayers();
        assertEquals(tm.getPlayers().size(), 2);
        List<PlayerImpl> players = tm.getPlayers();
        for (PlayerImpl playerImpl : players) {
            assertNotNull(playerImpl);
        }
    }

    @Test
    void testCreateEnemy() {
        builder.createEnemy();
        assertTrue(tm.getEnemy().isPresent());
        Enemy enemy = tm.getEnemy().get();
        assertNotNull(enemy);
    }

    @Test
    void testCreatePowerUps() {
        builder.createPowerUps();
        assertEquals(tm.getPowerUps().size(), 3);
        List<PowerUp> powerUps = tm.getPowerUps();
        for (PowerUp power : powerUps) {
            assertNotNull(power);
        }
    }

    @Test
    void testCreateMaze() {
        builder.createMaze();
        assertNotNull(tm.getLab());
    }
}
