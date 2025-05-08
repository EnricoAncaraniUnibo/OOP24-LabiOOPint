package labioopint.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import labioopint.model.api.ActionType;
import labioopint.model.api.Settings;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.powerup.impl.SwapPositionPowerUp;

class TurnManagerTest {
    private static TurnManager tm;

    @BeforeAll
    static void setUp() {
        final Settings settings = new Settings(2, 2, 3, EnemyDifficulty.MEDIUM);
        tm = new TurnManager(settings);
    }

    @Test
    void testTurnManagerActions() {
        assertEquals(tm.getCurrentAction(), ActionType.BLOCK_PLACEMENT);
        tm.nextAction();
        assertEquals(tm.getCurrentAction(), ActionType.PLAYER_MOVEMENT);
        tm.nextAction();
        assertEquals(tm.getCurrentAction(), ActionType.BLOCK_PLACEMENT);
    }

    @Test
    void testTurnManagerAddPowerUp() {
        final Integer powerUpNumber = tm.getPowerUps().size();
        tm.addAddictionalPowerUp(new SwapPositionPowerUp(tm,0));
        assertEquals(powerUpNumber + 1, tm.getPowerUps().size());
    }
}
