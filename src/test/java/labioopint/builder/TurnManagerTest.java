package labioopint.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import labioopint.model.core.api.Builder;
import labioopint.model.core.api.TurnManager;
import labioopint.model.core.impl.BuilderImpl;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.model.utilities.api.Settings;
import labioopint.model.utilities.impl.ActionType;
import labioopint.model.utilities.impl.SettingsImpl;

class TurnManagerTest {
    private static TurnManager tm;
    private static Labyrinth lab;

    @BeforeAll
    static void setUp() {
        final Settings settings = new SettingsImpl(2, 2, 3, EnemyDifficulty.MEDIUM);
        final Builder builder = new BuilderImpl(settings);
        lab = builder.createMaze();
        tm = builder.createTurnManager();
    }

    @Test
    void testTurnManagerActions() {
        final Player p1 = lab.getPlayers().get(tm.getCurrentPlayer());
        assertEquals(tm.getCurrentAction(), ActionType.BLOCK_PLACEMENT);
        tm.nextAction();
        assertEquals(tm.getCurrentAction(), ActionType.PLAYER_MOVEMENT);
        final Player p2 = lab.getPlayers().get(tm.getCurrentPlayer());
        assertEquals(p1, p2);
        tm.nextAction();
        assertEquals(tm.getCurrentAction(), ActionType.BLOCK_PLACEMENT);
        final Player p3 = lab.getPlayers().get(tm.getCurrentPlayer());
        assertNotEquals(p2, p3);
    }
}
