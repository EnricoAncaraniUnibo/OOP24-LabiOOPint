package labioopint.Labyrinth;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Maze.api.Labyrinth;
import labioopint.model.Maze.impl.LabyrinthImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.PowerUp.impl.PowerUpImpl;
import labioopint.model.api.Coordinate;
import labioopint.model.api.Settings;

public class PositioningTest {
	private static Labyrinth lab;
	private final static Integer SIZE = 7;
	private static TurnManager turn;
	
	@BeforeAll
	static void init() {
		turn=new TurnManager(new Settings(1,4,5));
		lab = new LabyrinthImpl(SIZE);
	}
	
	@Test
	void CheckPositionForAll() {
		boolean passed=true;
		for(PlayerImpl p : TurnManager.GetPlayers()) {
			Coordinate c = lab.getPlayerCoordinate(p);
			if(Objects.isNull(c)) {
				passed=false;
			}
		}
		assertTrue(passed);
		passed=true;
		Coordinate c = lab.getEnemyCoordinate(TurnManager.GetEnemy().get());
		if(Objects.isNull(c)) {
			passed=false;
		}
		assertTrue(passed);
		passed=true;
		for(PowerUp p : TurnManager.GetPowerUps()) {
			c = lab.getPowerUp(p);
			if(Objects.isNull(c)) {
				passed=false;
			}
		}
		assertTrue(passed);
	}
	
	
	@Test
	void StartingPlayerPositions() {
		boolean passed=true;
		for(PlayerImpl p : TurnManager.GetPlayers()) {
			Coordinate c = lab.getPlayerCoordinate(p);
			if(!(c.getRow()==0 && c.getColumn()==0) && !(c.getRow()==0 && c.getColumn()==SIZE-1) && !(c.getRow()==SIZE-1 && c.getColumn()==SIZE-1) && !(c.getRow()==SIZE-1 && c.getColumn()==0)) {
				passed=false;
			}
		}
		assertTrue(passed);
	}
	
}
