package labioopint.labyrinth;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import labioopint.model.block.api.BlockType;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;

/**
 * The BlocksTest class contains unit tests for the BlockImpl class.
 * It verifies the behavior of block rotation and ensures that the rotation
 * can be changed correctly.
 */
class BlocksTest {

    /**
     * A static instance of BlockImpl used for testing.
     */
    private static BlockImpl b;

    /**
     * Initializes the BlockImpl instance before all tests are executed.
     * Sets the block type to CORRIDOR.
     */
    @BeforeAll
    static void init() {
        b = new BlockImpl(BlockType.CORRIDOR, 1);
    }

    /**
     * Tests the setRotation method of BlockImpl.
     * Ensures that the rotation of the block changes when set to a new value.
     */
    @Test
    void changeRotation() {
        final Rotation r = b.getRotation();
        b.setRotation(Rotation.ONE_HUNDRED_EIGHTY);
        assertNotEquals(r, b.getRotation());
    }
}
