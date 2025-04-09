package labioopint.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Maze.api.Direction;
import labioopint.model.Maze.impl.LabyrinthImpl;
import labioopint.model.Player.api.Player;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.impl.SwapPositionPowerUp;
import labioopint.model.api.Coordinate;
import labioopint.model.api.Settings;

public class PlayerTest {
    private static Player p;
    
    @BeforeAll
    static void init() {
    	p = new PlayerImpl("Archer");
    }
    
    @BeforeEach
    void reCreate() {
    	p = new PlayerImpl("Archer");
    }
    
    @Test
    void PickUpObjective() {
    	p.addObjective(new SwapPositionPowerUp());
    	assertEquals(p.getUsablePowerUps().size(),1);
    	assertEquals(p.getObjetives().size(),1);
    }
    
    @Test
    void ConsumePowerUp() {
    	SwapPositionPowerUp pu = new SwapPositionPowerUp();
    	p.addObjective(pu);
    	p.removePowerUp(pu);
    	assertEquals(p.getUsablePowerUps().size(),0);
    	assertEquals(p.getObjetives().size(),1);
    }
    
    @Test
    void MovePlayer() {
    	TurnManager.Init(new Settings(0,2,0));
    	LabyrinthImpl lab = new LabyrinthImpl(5);
    	lab.absoluteUpdateCoordinate(TurnManager.GetCurrentPlayer(), new Coordinate(0,0));
    	lab.movePlayer(TurnManager.GetCurrentPlayer(), Direction.DOWN);
    	assertEquals(lab.getPlayerCoordinate(TurnManager.GetCurrentPlayer()).getRow(),1);
    	lab.movePlayer(TurnManager.GetCurrentPlayer(), Direction.RIGHT);
    	assertEquals(lab.getPlayerCoordinate(TurnManager.GetCurrentPlayer()).getColumn(),1);
    }
}
