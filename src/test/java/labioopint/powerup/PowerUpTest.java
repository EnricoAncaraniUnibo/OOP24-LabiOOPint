package labioopint.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import labioopint.model.api.Coordinate;
import labioopint.model.api.Settings;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;
import labioopint.model.powerup.impl.DoubleTurnPowerUp;
import labioopint.model.powerup.impl.InvulnerabilityPowerUp;
import labioopint.model.powerup.impl.StealObjectPowerUp;
import labioopint.model.powerup.impl.SwapPositionPowerUp;

public class PowerUpTest {

    @Test
    void testDoubleTurnPowerUp() {
        final TurnManager tu = new TurnManager(new Settings(0, 2, 0, EnemyDifficulty.EASY));
        final PowerUp powerup = new DoubleTurnPowerUp(tu, 0);
        assertNotNull(powerup);
        tu.addAddictionalPowerUp(powerup);
        final PlayerImpl player1 = tu.getCurrentPlayer();
        player1.addObjective(powerup);
        powerup.collect();
        player1.getUsablePowerUps().get(0).activate();
        final PlayerImpl player2 = tu.getCurrentPlayer();
        assertEquals(player2, player1);
    }

    @Test
    void testSwapPositionPowerUp() {
        final TurnManager tu = new TurnManager(new Settings(0, 2, 0, EnemyDifficulty.EASY));
        final PowerUp powerup = new SwapPositionPowerUp(tu, 0);
        assertNotNull(powerup);
        tu.addAddictionalPowerUp(powerup);
        final List<PlayerImpl> players = tu.getPlayers();
        final Coordinate player1Coordinate = tu.getLab().getPlayerCoordinate(players.get(0));
        final Coordinate player2Coordinate = tu.getLab().getPlayerCoordinate(players.get(1));
        players.get(0).addObjective(powerup);
        powerup.collect();
        players.get(0).getUsablePowerUps().get(0).activate();
        assertEquals(player1Coordinate, tu.getLab().getPlayerCoordinate(players.get(1)));
        assertEquals(player2Coordinate, tu.getLab().getPlayerCoordinate(players.get(0)));
    }

    @Test
    void testInvulnerabilityPowerUp() {
        final TurnManager tu = new TurnManager(new Settings(1, 2, 0, EnemyDifficulty.HARD));
        final PowerUp powerup = new InvulnerabilityPowerUp(tu, 0);
        assertNotNull(powerup);
        tu.addAddictionalPowerUp(powerup);
        final PlayerImpl player = tu.getCurrentPlayer();
        player.addObjective(powerup);
        powerup.collect();
        player.getUsablePowerUps().get(0).activate();
        tu.getLab().setBlock(new BlockImpl(BlockType.CORRIDOR), new Coordinate(2, 2));
        tu.getLab().setBlock(new BlockImpl(BlockType.CORRIDOR), new Coordinate(1, 2));
        tu.getLab().playerUpdateCoordinate(player, new Coordinate(1, 2));
        tu.nextAction();
        tu.nextAction();
        assertEquals(tu.getLab().getPlayerCoordinate(player),
                tu.getLab().getEnemyCoordinate(tu.getEnemy().getSecond()));
        assertEquals(player.getObjetives().size(), 1);
    }

    @Test
    void testStealObjectPowerUp() {
        final TurnManager tu = new TurnManager(new Settings(0, 2, 0, EnemyDifficulty.HARD));
        final PowerUp stealPowerup = new StealObjectPowerUp(tu, 0);
        final PowerUp otherPowerup = new InvulnerabilityPowerUp(tu, 0);
        assertNotNull(stealPowerup);
        tu.addAddictionalPowerUp(stealPowerup);
        tu.addAddictionalPowerUp(otherPowerup);
        final List<PlayerImpl> players = tu.getPlayers();
        players.get(0).addObjective(stealPowerup);
        stealPowerup.collect();
        players.get(1).addObjective(otherPowerup);
        otherPowerup.collect();
        players.get(0).getUsablePowerUps().get(0).activate();
        assertEquals(players.get(0).getObjetives().size(), 2);
        assertEquals(players.get(1).getObjetives().size(), 0);
    }
}
