package labioopint.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import labioopint.model.api.ActionType;
import labioopint.model.api.Settings;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.powerup.impl.SwapPositionPowerUp;

/**
 * The {@code TurnManagerTest} class contains unit tests for verifying the
 * behavior of the {@link TurnManager} class. It ensures that the turn manager
 * correctly handles game actions and manages power-ups.
 */
class TurnManagerTest {
    private static TurnManager tm;

    /**
     * Sets up the {@link TurnManager} instance before all tests are executed.
     * Initializes the game settings with predefined values.
     */
    @BeforeAll
    static void setUp() {
        final Settings settings = new Settings(2, 2, 3, EnemyDifficulty.MEDIUM);
        tm = new TurnManager(settings);
    }

    /**
     * Tests the behavior of the {@link TurnManager} when transitioning between
     * different game actions. Ensures that the actions cycle correctly between
     * block placement and player movement.
     */
    @Test
    void testTurnManagerActions() {
        assertEquals(tm.getCurrentAction(), ActionType.BLOCK_PLACEMENT);
        tm.nextAction();
        assertEquals(tm.getCurrentAction(), ActionType.PLAYER_MOVEMENT);
        tm.nextAction();
        assertEquals(tm.getCurrentAction(), ActionType.BLOCK_PLACEMENT);
    }

    /**
     * Tests the ability of the {@link TurnManager} to add additional power-ups
     * to the game. Ensures that the power-up list size increases after adding
     * a new power-up.
     */
    @Test
    void testTurnManagerAddPowerUp() {
        final Integer powerUpNumber = tm.getPowerUps().size();
        tm.addAddictionalPowerUp(new SwapPositionPowerUp(tm, 0));
        assertEquals(powerUpNumber + 1, tm.getPowerUps().size());
    }
}
