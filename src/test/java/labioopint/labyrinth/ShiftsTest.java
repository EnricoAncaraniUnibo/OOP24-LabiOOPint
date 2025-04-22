package labioopint.labyrinth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import labioopint.model.api.Coordinate;
import labioopint.model.api.Settings;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.maze.impl.LabyrinthImpl;
/**
 * The ShiftsTest class contains unit tests for verifying the correct behavior
 * of shifting rows and columns in the labyrinth. It ensures that blocks are
 * moved correctly, the outside block is updated as expected, and the grid
 * maintains its integrity after each shift operation.
 */
class ShiftsTest {
	private final static Integer SIZE=5;
	
	/**
     * Tests shifting a row to the right. Verifies that the blocks in the row
     * are shifted correctly, and the outside block is updated as expected.
     */
	@Test
	void testShiftRowRight() {
		TurnManager tu = new TurnManager(new Settings(1,2,3,EnemyDifficulty.EASY));
		Labyrinth lab = new LabyrinthImpl(SIZE, tu);
		final BlockImpl initialOutsideBlock = lab.getOutsideBlock();
		final List<BlockImpl> ls = new ArrayList<>();
		for(int i=0;i<SIZE;i++)  {
			ls.add(lab.getGrid().getBlock(new Coordinate(2,i)).get());
		}
		lab.moveBlock(new Coordinate(2, 0), Direction.RIGHT);
		assertEquals(initialOutsideBlock,lab.getGrid().getBlock(new Coordinate(2,0)).get());
		for(int i=0;i<SIZE-1;i++) {
			assertEquals(ls.get(i),lab.getGrid().getBlock(new Coordinate(2,i+1)).get());
		}
		assertEquals(ls.get(ls.size()-1),lab.getOutsideBlock());
	}
	
	/**
     * Tests shifting a row to the left. Verifies that the blocks in the row
     * are shifted correctly, and the outside block is updated as expected.
     */
	@Test
	void testShiftRowLeft() {
		TurnManager tu = new TurnManager(new Settings(1,2,3,EnemyDifficulty.EASY));
		Labyrinth lab = new LabyrinthImpl(SIZE, tu);
		final BlockImpl initialOutsideBlock = lab.getOutsideBlock();
		final List<BlockImpl> ls = new ArrayList<>();
		for(int i=SIZE-1;i>=0;i--)  {
			ls.add(lab.getGrid().getBlock(new Coordinate(2,i)).get());
		}
		lab.moveBlock(new Coordinate(2, SIZE-1), Direction.LEFT);
		assertEquals(initialOutsideBlock,lab.getGrid().getBlock(new Coordinate(2,SIZE-1)).get());
		int index=0;
		for(int i=SIZE-1;i>0;i--) {
			assertEquals(ls.get(index),lab.getGrid().getBlock(new Coordinate(2,i-1)).get());
			index++;
		}
		assertEquals(ls.get(ls.size()-1),lab.getOutsideBlock());
	}
	
	/**
     * Tests shifting a column down. Verifies that the blocks in the column
     * are shifted correctly, and the outside block is updated as expected.
     */
	@Test
	void testShiftColumnDown() {
		TurnManager tu = new TurnManager(new Settings(1,2,3,EnemyDifficulty.EASY));
		Labyrinth lab = new LabyrinthImpl(SIZE, tu);
		final BlockImpl initialOutsideBlock = lab.getOutsideBlock();
		final List<BlockImpl> ls = new ArrayList<>();
		for(int i=0;i<SIZE;i++)  {
			ls.add(lab.getGrid().getBlock(new Coordinate(i,2)).get());
		}
		lab.moveBlock(new Coordinate(0, 2), Direction.DOWN);
		assertEquals(initialOutsideBlock,lab.getGrid().getBlock(new Coordinate(0,2)).get());
		for(int i=0;i<SIZE-1;i++) {
			assertEquals(ls.get(i),lab.getGrid().getBlock(new Coordinate(i+1,2)).get());
		}
		assertEquals(ls.get(ls.size()-1),lab.getOutsideBlock());
	}
	
	/**
     * Tests shifting a column up. Verifies that the blocks in the column
     * are shifted correctly, and the outside block is updated as expected.
     */
	@Test
	void testShiftColumnUp() {
		TurnManager tu = new TurnManager(new Settings(1,2,3,EnemyDifficulty.EASY));
		Labyrinth lab = new LabyrinthImpl(SIZE, tu);
		final BlockImpl initialOutsideBlock = lab.getOutsideBlock();
		final List<BlockImpl> ls = new ArrayList<>();
		for(int i=SIZE-1;i>=0;i--)  {
			ls.add(lab.getGrid().getBlock(new Coordinate(i,2)).get());
		}
		lab.moveBlock(new Coordinate(SIZE-1, 2), Direction.UP);
		assertEquals(initialOutsideBlock,lab.getGrid().getBlock(new Coordinate(SIZE-1,2)).get());
		int index=0;
		for(int i=SIZE-1;i>0;i--) {
			assertEquals(ls.get(index),lab.getGrid().getBlock(new Coordinate(i-1,2)).get());
			index++;
		}
		assertEquals(ls.get(ls.size()-1),lab.getOutsideBlock());
	}
}
