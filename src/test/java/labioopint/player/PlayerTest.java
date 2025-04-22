package labioopint.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import labioopint.model.api.Coordinate;
import labioopint.model.api.Settings;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.maze.api.Direction;
import labioopint.model.player.api.Player;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.impl.SwapPositionPowerUp;

class PlayerTest {
	private TurnManager tu;
    
    @Test
    void pickUpObjective() {
		tu = new TurnManager(new Settings(1,4,5,EnemyDifficulty.EASY));
		final Player p = new PlayerImpl("Archer",tu);
    	p.addObjective(new SwapPositionPowerUp(tu));
    	assertEquals(p.getUsablePowerUps().size(),1);
    	assertEquals(p.getObjetives().size(),1);
    }
    
    @Test
    void consumePowerUp() {
		final Player p = new PlayerImpl("Archer",tu);
    	final SwapPositionPowerUp pu = new SwapPositionPowerUp(tu);
    	p.addObjective(pu);
    	p.removePowerUp(pu);
    	assertEquals(p.getUsablePowerUps().size(),0);
    	assertEquals(p.getObjetives().size(),1);
    }
    
    @Test
    void movePlayer() {
		tu = new TurnManager(new Settings(0,2,0,EnemyDifficulty.EASY));
		final List<PlayerImpl> ls = tu.getPlayers();
		BlockImpl b = new BlockImpl(BlockType.CORRIDOR);
		b.setRotation(Rotation.ZERO);
		tu.getLab().setBlock(b, new Coordinate(0,0));
		b = new BlockImpl(BlockType.CORNER);
		b.setRotation(Rotation.TWO_HUNDRED_SEVENTY);
		tu.getLab().setBlock(b, new Coordinate(1,0));
		b = new BlockImpl(BlockType.CORRIDOR);
		b.setRotation(Rotation.NINETY);
		tu.getLab().setBlock(b, new Coordinate(1,1));
    	tu.getLab().playerUpdateCoordinate(ls.get(0), new Coordinate(0,0));
    	tu.getLab().movePlayer(ls.get(0), Direction.DOWN);
    	assertEquals(tu.getLab().getPlayerCoordinate(ls.get(0)).getRow(),1);
    	tu.getLab().movePlayer(tu.getCurrentPlayer(), Direction.RIGHT);
    	assertEquals(tu.getLab().getPlayerCoordinate(ls.get(0)).getColumn(),1);
    }
}
