package labioopint.model.maze.impl;

import java.util.Random;

import labioopint.model.maze.api.BlockType;
import labioopint.model.maze.api.Rotation;

public class Block {
    private BlockType type;
    private Rotation rotation;
    private Movable move;

    public Block(final BlockType ty) {
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

    public boolean IsMovable() {
        return move.IsMovable();
    }

    public void DisableMovement() {
        move.disable();
    }
}
