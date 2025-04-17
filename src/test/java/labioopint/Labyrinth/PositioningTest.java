package labioopint.Labyrinth;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Enemy.api.EnemyDifficulty;
import labioopint.model.Maze.api.Labyrinth;
import labioopint.model.Maze.impl.LabyrinthImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.Coordinate;
import labioopint.model.api.Settings;
/**
 * The PositioningTest class contains unit tests for verifying the correct
 * positioning of players, enemies, and power-ups within the labyrinth.
 * It ensures that all entities are correctly placed, starting positions
 * are valid, and positions can be updated as expected.
 */
public class PositioningTest {
	private Labyrinth lab;
	private final static Integer SIZE = 7;
	private TurnManager tu;
	
	/**
     * Tests that all players, enemies, and power-ups have valid positions
     * within the labyrinth.
     */
	@Test
	void CheckPositionForAll() {
		boolean passed=true;
		tu = new TurnManager(new Settings(1,4,5,EnemyDifficulty.EASY));
		lab = new LabyrinthImpl(SIZE, tu);
		for(PlayerImpl p : tu.getPlayers()) {
			Coordinate c = lab.getPlayerCoordinate(p);
			if(Objects.isNull(c)) {
				passed=false;
			}
		}
		assertTrue(passed);
		passed=true;
		Coordinate c = lab.getEnemyCoordinate(tu.getEnemy().get());
		if(Objects.isNull(c)) {
			passed=false;
		}
		assertTrue(passed);
		passed=true;
		for(PowerUp p : tu.getPowerUps()) {
			c = lab.getPowerUpCoordinate(p);
			if(Objects.isNull(c)) {
				passed=false;
			}
		}
		assertTrue(passed);
	}
	
	/**
     * Tests that the starting positions of players are at the corners of the labyrinth.
     */
	@Test
	void StartingPlayerPositions() {
		tu = new TurnManager(new Settings(1,4,5,EnemyDifficulty.EASY));
		lab = new LabyrinthImpl(SIZE, tu);
		lab= new LabyrinthImpl(SIZE,tu);
		boolean passed=true;
		for(PlayerImpl p : tu.getPlayers()) {
			Coordinate c = lab.getPlayerCoordinate(p);
			if(!(c.getRow()==0 && c.getColumn()==0) && !(c.getRow()==0 && c.getColumn()==SIZE-1) && !(c.getRow()==SIZE-1 && c.getColumn()==SIZE-1) && !(c.getRow()==SIZE-1 && c.getColumn()==0)) {
				passed=false;
			}
		}
		assertTrue(passed);
	}
	
	/**
     * Tests that the positions of players and enemies can be updated correctly
     * and that the old positions are removed.
     */
	@Test 
	void ChangePosition() {
		tu = new TurnManager(new Settings(1,4,5,EnemyDifficulty.EASY));
		lab = new LabyrinthImpl(SIZE, tu);
		List<PlayerImpl> ls = tu.getPlayers();
		Enemy e = tu.getEnemy().get();
		
		Coordinate old = lab.getPlayerCoordinate(ls.get(0));
		lab.PlayerUpdateCoordinate(ls.get(0), new Coordinate(3,3));
		boolean oldRemoved=true;
		if(lab.getPlayerCoordinate(ls.get(0)).getRow()==old.getRow() && lab.getPlayerCoordinate(ls.get(0)).getColumn()==old.getColumn()) {
			oldRemoved=false;
		}
		assertTrue(oldRemoved);
		
		boolean correctChanged=true;
		if(lab.getPlayerCoordinate(ls.get(0)).getRow()!=3 || lab.getPlayerCoordinate(ls.get(0)).getColumn()!=3) {
			correctChanged=false;
		}
		assertTrue(correctChanged);
		
		old = lab.getEnemyCoordinate(e);
		List<Coordinate> lCoor = new ArrayList<>();
		lCoor.add(new Coordinate(Math.abs(old.getRow()-1),Math.abs(old.getColumn()-1)));
		lab.EnemyUpdateCoordinate(e, lCoor);
		oldRemoved=true;
		if(lab.getEnemyCoordinate(e).getRow()==old.getRow() && lab.getEnemyCoordinate(e).getColumn()==old.getColumn()) {
			oldRemoved=false;
		}
		assertTrue(oldRemoved);
		
		correctChanged=true;
		if(lab.getEnemyCoordinate(e).getRow()!=Math.abs(old.getRow()-1) || lab.getEnemyCoordinate(e).getColumn()!=Math.abs(old.getColumn()-1)) {
			correctChanged=false;
		}
		assertTrue(correctChanged);
	}
	
}
