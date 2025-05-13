package labioopint.model.utilities.impl;

import labioopint.model.utilities.api.Movable;
/**
 * The class that implements the method of movable.
 */
public class MovableImpl implements Movable {
    private boolean move;
    /**
     * A new movable object with by default the possibility to move.
     */
    public MovableImpl() {
        move = true;
    }

    @Override
    public final boolean isMovable() {
        return move;
    }

    @Override
    public final void enable() {
        move = true;
    }

    @Override
    public final void disable() {
        move = false;
    }
}

