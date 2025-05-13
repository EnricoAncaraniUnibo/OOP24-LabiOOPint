package labioopint.model.utilities.impl;

import labioopint.model.utilities.api.Movable;

public class MovableImpl implements Movable {
    private boolean move;

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

