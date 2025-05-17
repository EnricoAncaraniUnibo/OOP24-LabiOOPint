package labioopint.model.block.impl;

import java.util.Random;

import labioopint.model.utilities.impl.MovableImpl;
import labioopint.model.block.api.Block;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.api.Rotation;

/**
 * Class implementation of a block.
 * Extend {@code MovableImpl}.
 */
public final class BlockImpl extends MovableImpl implements Block {
    public static final long serialVersionUID = 1L;
    private final BlockType type;
    private Rotation rotation;
    private static final Random RANDOM = new Random();
    private final int id;

    /**
     * Create the block by giving it a type and a default rotation.
     * 
     * @param ty the type of the block.
     * @param id the id given to the block
     */
    public BlockImpl(final BlockType ty, final int id) {
        super();
        this.rotation = Rotation.ZERO;
        this.type = ty;
        this.id = id;
    }

    /**
     * Create the block from another block coping it.
     * 
     * @param b the block to copy
     */
    public BlockImpl(final Block b) {
        super();
        this.rotation = b.getRotation();
        this.type = b.getType();
        id = b.getID();
    }

    @Override
    public BlockType getType() {
        return this.type;
    }

    @Override
    public Rotation getRotation() {
        return rotation;
    }

    @Override
    public void randomRotation() {
        final Integer value = RANDOM.nextInt(0, 4);
        switch (value) {
            case 0:
                rotation = Rotation.ZERO;
                break;
            case 1:
                rotation = Rotation.NINETY;
                break;
            case 2:
                rotation = Rotation.ONE_HUNDRED_EIGHTY;
                break;
            case 3:
                rotation = Rotation.TWO_HUNDRED_SEVENTY;
                break;
            default:
                break;
        }
    }

    @Override
    public void setRotation(final Rotation blockRotation) {
        rotation = blockRotation;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BlockImpl)) {
            return false;
        }
        final BlockImpl other = (BlockImpl) obj;
        return this.type == other.type
                && this.rotation == other.rotation && this.id == other.id;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(type, rotation);
    }

    @Override
    public int getID() {
        return id;
    }
}
