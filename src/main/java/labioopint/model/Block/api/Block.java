package labioopint.model.Block.api;
/**
 * The Block interface represents a block in the labyrinth. It provides methods
 * to retrieve the block's type, manage its rotation, and apply random rotations.
 */
public interface Block {
	/**
     * Retrieves the type of the block.
     *
     * @return the type of the block as a BlockType
     */
    BlockType getType();
    /**
     * Retrieves the current rotation of the block.
     *
     * @return the current rotation of the block as a Rotation
     */
    Rotation getRotation();
    /**
     * Applies a random rotation to the block.
     */
    void RandomRotation();
    /**
     * Sets the rotation of the block to the specified value.
     *
     * @param blockRotation the new rotation to set for the block
     */
    void setRotation(Rotation blockRotation);

}