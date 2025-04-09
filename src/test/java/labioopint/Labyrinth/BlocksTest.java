package labioopint.Labyrinth;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import labioopint.model.Block.api.BlockType;
import labioopint.model.Block.api.Rotation;
import labioopint.model.Block.impl.BlockImpl;
/**
 * The BlocksTest class contains unit tests for the BlockImpl class.
 * It verifies the behavior of block rotation and ensures that the rotation
 * can be changed correctly.
 */
public class BlocksTest {
	/**
     * A static instance of BlockImpl used for testing.
     */
	private static BlockImpl b;
	
	/**
     * Initializes the BlockImpl instance before all tests are executed.
     * Sets the block type to CORRIDOR.
     */
	@BeforeAll
	static void Init() {
		b = new BlockImpl(BlockType.CORRIDOR);
	}
	
	/**
     * Tests the setRotation method of BlockImpl.
     * Ensures that the rotation of the block changes when set to a new value.
     */
	@Test
	void ChangeRotation() {
		Rotation r = b.getRotation();
		b.setRotation(Rotation.ONE_HUNDRED_EIGHTY);
		assertNotEquals(r,b.getRotation());
	}
}
