package labioopint.model.Enemy.impl;

public class Movable {
    private boolean movable;

    public Movable() {
        movable = true;
    }

    public boolean IsMovable() {
        return movable;
    }

    public void enable() {
        movable = true;
    }

    public void disable() {
        movable = false;
    }
}