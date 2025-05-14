package labioopint.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import labioopint.model.core.api.Builder;
import labioopint.model.core.impl.BuilderImpl;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;
import labioopint.model.utilities.api.Settings;
import labioopint.model.utilities.impl.SettingsImpl;

class BuilderImplTest {

    private Labyrinth lab;

    @BeforeEach
    void setUp() {
        final Settings settings = new SettingsImpl(1, 2, 3, EnemyDifficulty.MEDIUM);
        final Builder builder = new BuilderImpl(settings);
        lab = builder.createMaze();
    }

    @Test
    void testCreatePlayers() {
        final List<Player> ls = lab.getPlayers();
        assertEquals(ls.size(), 2);
        for (final Player player : ls) {
            assertNotNull(player);
        }
    }

    @Test
    void testCreateEnemy() {
        final Enemy e = lab.getEnemy().getSecond();
        assertTrue(lab.getEnemy().getFirst());
        assertNotNull(e);
    }

    @Test
    void testCreatePowerUps() {
        final List<PowerUp> ls = lab.getPowerUpsNotCollected();
        assertEquals(ls.size(), 3);
        for (final PowerUp power : ls) {
            assertNotNull(power);
        }
    }

    @Test
    void testCreateMaze() {
        assertNotNull(lab);
    }
}
