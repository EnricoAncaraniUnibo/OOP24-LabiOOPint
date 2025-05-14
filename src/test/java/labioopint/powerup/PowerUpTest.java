package labioopint.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import labioopint.controller.api.GameController;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.api.TurnManager;
import labioopint.model.core.impl.TurnManagerImpl;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.maze.impl.LabyrinthImpl;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;
import labioopint.model.powerup.impl.DoubleTurnPowerUp;
import labioopint.model.powerup.impl.InvulnerabilityPowerUp;
import labioopint.model.powerup.impl.StealObjectPowerUp;
import labioopint.model.powerup.impl.SwapPositionPowerUp;
import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.utilities.impl.SettingsImpl;
import labioopint.model.player.api.Player;
import labioopint.controller.impl.GameControllerImpl;

class PowerUpTest {

    @Test
    void testDoubleTurnPowerUp() {
        final PowerUp powerup = new DoubleTurnPowerUp(0);
        final GameController gc = new GameControllerImpl(new SettingsImpl(0, 2, 0, EnemyDifficulty.EASY));
        final Labyrinth lab = gc.getLabyrinth();
        final TurnManager tu = gc.getTurnManager();
        assertNotNull(powerup);
        final PlayerImpl player1 = (PlayerImpl) lab.getPlayers().get(tu.getCurrentPlayer());
        player1.addObjective(powerup);
        powerup.collect();
        player1.getUsablePowerUps().get(0).activate(lab, tu);
        tu.nextAction();
        gc.action("End Turn");
        final PlayerImpl player2 = (PlayerImpl) lab.getPlayers().get(tu.getCurrentPlayer());
        assertEquals(player2, player1);
    }

    @Test
    void testSwapPositionPowerUp() {
        final List<Player> players = List.of(new PlayerImpl("1"), new PlayerImpl("2"));
        final PowerUp powerup = new SwapPositionPowerUp(0);
        final LabyrinthImpl lab = new LabyrinthImpl(5, players, List.of(powerup));
        final TurnManagerImpl tu = new TurnManagerImpl(2);
        assertNotNull(powerup);
        final Coordinate player1Coordinate = lab.getPlayerCoordinate(players.get(0));
        final Coordinate player2Coordinate = lab.getPlayerCoordinate(players.get(1));
        players.get(0).addObjective(powerup);
        powerup.collect();
        players.get(0).getUsablePowerUps().get(0).activate(lab, tu);
        assertEquals(player1Coordinate, lab.getPlayerCoordinate(players.get(1)));
        assertEquals(player2Coordinate, lab.getPlayerCoordinate(players.get(0)));
    }

    @Test
    void testInvulnerabilityPowerUp() {
        final GameController gc = new GameControllerImpl(new SettingsImpl(1, 2, 0, EnemyDifficulty.HARD));
        final PowerUp powerup = new InvulnerabilityPowerUp(0);
        final LabyrinthImpl lab = (LabyrinthImpl) gc.getLabyrinth();
        final TurnManagerImpl tu = (TurnManagerImpl) gc.getTurnManager();
        assertNotNull(powerup);
        final PlayerImpl player = (PlayerImpl) lab.getPlayers().get(tu.getCurrentPlayer());
        player.addObjective(powerup);
        powerup.collect();
        player.getUsablePowerUps().get(0).activate(lab, tu);
        lab.setBlock(new BlockImpl(BlockType.CORRIDOR), new CoordinateImpl(2, 2));
        lab.setBlock(new BlockImpl(BlockType.CORRIDOR), new CoordinateImpl(1, 2));
        lab.playerUpdateCoordinate(player, new CoordinateImpl(1, 2));
        tu.nextAction();
        gc.action("End Turn");
        assertEquals(lab.getPlayerCoordinate(player), lab.getEnemyCoordinate(lab.getEnemy().getSecond()));
        assertEquals(player.getObjetives().size(), 1);
    }

    @Test
    void testStealObjectPowerUp() {
        final List<Player> players = List.of(new PlayerImpl("1"), new PlayerImpl("2"));
        final PowerUp stealPowerup = new StealObjectPowerUp(0);
        final PowerUp otherPowerup = new InvulnerabilityPowerUp(0);
        final LabyrinthImpl lab = new LabyrinthImpl(5, players, List.of(stealPowerup, otherPowerup));
        final TurnManagerImpl tu = new TurnManagerImpl(2);
        assertNotNull(stealPowerup);
        players.get(0).addObjective(stealPowerup);
        stealPowerup.collect();
        players.get(1).addObjective(otherPowerup);
        otherPowerup.collect();
        players.get(0).getUsablePowerUps().get(0).activate(lab, tu);
        assertEquals(players.get(0).getObjetives().size(), 2);
        assertEquals(players.get(1).getObjetives().size(), 0);
    }
}
