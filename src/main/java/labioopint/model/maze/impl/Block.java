package labioopint.model.maze.impl;

import java.util.Random;

import labioopint.model.api.Movable;
import labioopint.model.maze.api.BlockType;
import labioopint.model.maze.api.Rotation;

public class Block extends Movable {
    private BlockType type;
    private Rotation rotation;

    public Block(final BlockType ty) {
        super();
        this.rotation = Rotation.ZERO;
        this.type = ty;
    }

    public BlockType getType() {
        return this.type;
    }

    public Rotation getRotation() {
        return rotation;
    }

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

    public void setRotation(Rotation blockRotation) {
        rotation = blockRotation;
    }
}
