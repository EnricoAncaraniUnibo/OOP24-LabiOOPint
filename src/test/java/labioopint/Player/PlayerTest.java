package labioopint.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import labioopint.model.api.Coordinate;
import labioopint.model.api.Settings;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.impl.LabyrinthImpl;
import labioopint.model.player.api.Player;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.impl.SwapPositionPowerUp;

public class PlayerTest {
    private static Player p;
	private TurnManager tu;
    
    @Test
    void PickUpObjective() {
		tu = new TurnManager(new Settings(1,4,5,EnemyDifficulty.EASY));
		p = new PlayerImpl("Archer",tu);
    	p.addObjective(new SwapPositionPowerUp(tu));
    	assertEquals(p.getUsablePowerUps().size(),1);
    	assertEquals(p.getObjetives().size(),1);
    }
    
    @Test
    void ConsumePowerUp() {
		p = new PlayerImpl("Archer",tu);
    	SwapPositionPowerUp pu = new SwapPositionPowerUp(tu);
    	p.addObjective(pu);
    	p.removePowerUp(pu);
    	assertEquals(p.getUsablePowerUps().size(),0);
    	assertEquals(p.getObjetives().size(),1);
    }
    
    @Test
    void MovePlayer() {
		tu = new TurnManager(new Settings(0,2,0,EnemyDifficulty.EASY));
		p = new PlayerImpl("Archer",tu);
    	LabyrinthImpl lab = new LabyrinthImpl(5,tu);
    	lab.PlayerUpdateCoordinate(tu.getCurrentPlayer(), new Coordinate(0,0));
    	lab.movePlayer(tu.getCurrentPlayer(), Direction.DOWN);
    	assertEquals(lab.getPlayerCoordinate(tu.getCurrentPlayer()).getRow(),1);
    	lab.movePlayer(tu.getCurrentPlayer(), Direction.RIGHT);
    	assertEquals(lab.getPlayerCoordinate(tu.getCurrentPlayer()).getColumn(),1);
    }
}
