package labioopint.Builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.EnemyDifficulty;
import labioopint.model.PowerUp.impl.SwapPositionPowerUp;
import labioopint.model.api.ActionType;
import labioopint.model.api.Settings;

public class TurnManagerTest {
    private static TurnManager tm;

    @BeforeAll
    static void setUp() {
        Settings settings = new Settings(2, 2, 3, EnemyDifficulty.MEDIUM);
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
        Integer powerUpNumber = tm.getPowerUps().size();
        tm.addAddictionalPowerUp(new SwapPositionPowerUp(tm));
        assertEquals(powerUpNumber + 1, tm.getPowerUps().size());
    }
}
