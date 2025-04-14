package labioopint.model.api;

/**
 * The Movable class represents an object that can be enabled or disabled
 * for movement. It provides methods to check and modify its movable state.
 */
public class Movable {
    private boolean movable;

    /**
     * Constructs a new Movable object with the default state set to movable.
     */
    public Movable() {
        movable = true;
    }

    /**
     * Checks if the object is currently movable.
     *
     * @return true if the object is movable, false otherwise
     */
    public final boolean isMovable() {
        return movable;
    }

    public void enable() {
        movable = true;
    }

    /**
     * Disables movement for the object by setting its state to not movable.
     */
    public void disable() {
        movable = false;
    }
}

