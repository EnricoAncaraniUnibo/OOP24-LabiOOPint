package labioopint.model.Block.impl;

import java.util.Random;

import labioopint.model.Block.api.Block;
import labioopint.model.Block.api.BlockType;
import labioopint.model.Block.api.Rotation;
import labioopint.model.api.Movable;

public class BlockImpl extends Movable implements Block {
    private BlockType type;
    private Rotation rotation;

    public BlockImpl(final BlockType ty) {
        super();
        this.rotation = Rotation.ZERO;
        this.type = ty;
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
    public void RandomRotation() {
        Random r = new Random();
        Integer value = r.nextInt(0, 4);
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
        }
    }

    @Override
    public void setRotation(Rotation blockRotation) {
        rotation = blockRotation;
    }
}
