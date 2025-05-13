package labioopint.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import labioopint.controller.api.GameController;
import labioopint.controller.impl.GameControllerImpl;
import labioopint.model.block.api.Block;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;
import labioopint.model.powerup.impl.SwapPositionPowerUp;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.utilities.impl.SettingsImpl;

class PlayerTest {

    @Test
    void pickUpObjective() {
        GameController gc = new GameControllerImpl(new SettingsImpl(1, 2, 5, EnemyDifficulty.EASY));
        final Labyrinth lab = gc.getLab();
        final Player p = lab.getPlayers().get(0);
        p.addObjective(new SwapPositionPowerUp(0));
        assertEquals(p.getUsablePowerUps().size(), 1);
        assertEquals(p.getObjetives().size(), 1);
    }

    @Test
    void consumePowerUp() {
        GameController gc = new GameControllerImpl(new SettingsImpl(1, 2, 5, EnemyDifficulty.EASY));
        final Labyrinth lab = gc.getLab();
        final Player p = lab.getPlayers().get(0);
        final PowerUp pu = new SwapPositionPowerUp( 0);
        p.addObjective(pu);
        p.removePowerUp(pu);
        assertEquals(p.getUsablePowerUps().size(), 0);
        assertEquals(p.getObjetives().size(), 1);
    }

    @Test
    void movePlayer() {
        GameController gc = new GameControllerImpl(new SettingsImpl(1, 2, 5, EnemyDifficulty.EASY));
        final Labyrinth lab = gc.getLab();
        final List<Player> ls = lab.getPlayers();
        Block b = new BlockImpl(BlockType.CORRIDOR);
        b.setRotation(Rotation.ZERO);
        lab.setBlock(b, new CoordinateImpl(0, 0));
        b = new BlockImpl(BlockType.CORNER);
        b.setRotation(Rotation.TWO_HUNDRED_SEVENTY);
        lab.setBlock(b, new CoordinateImpl(1, 0));
        b = new BlockImpl(BlockType.CORRIDOR);
        b.setRotation(Rotation.NINETY);
        lab.setBlock(b, new CoordinateImpl(1, 1));
        lab.playerUpdateCoordinate(ls.get(0), new CoordinateImpl(0, 0));
        lab.movePlayer(ls.get(0), Direction.DOWN);
        assertEquals(lab.getPlayerCoordinate(ls.get(0)).getRow(), 1);
        lab.movePlayer(lab.getPlayers().get(gc.getTurnManager().getCurrentPlayer()), Direction.RIGHT);
        assertEquals(lab.getPlayerCoordinate(ls.get(0)).getColumn(), 1);
    }
}
