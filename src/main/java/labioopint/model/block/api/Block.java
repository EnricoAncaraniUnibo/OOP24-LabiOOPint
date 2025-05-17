package labioopint.model.block.api;

import java.io.Serializable;
/**
 * Represents a block in the game.
 * This interface provides methods to retrieve and manipulate the block's type and rotation.
 */
public interface Block extends Serializable {

    /**
     * Retrieves the type of the block.
     *
     * @return the {@link BlockType} of the block
     */
    BlockType getType();

    /**
     * Retrieves the current rotation of the block.
     *
     * @return the {@link Rotation} of the block
     */
    Rotation getRotation();

    /**
     * Sets a random rotation for the block.
     */
    void randomRotation();

    /**
     * Sets the rotation of the block to the specified value.
     *
     * @param blockRotation the {@link Rotation} to set for the block
     */
    void setRotation(Rotation blockRotation);

    int getID();

}
