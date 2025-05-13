package labioopint.model.utilities.api;
/**
 * Represents an entity that can be enabled or disabled for movement.
 * This interface provides methods to check and modify the movable state of the entity.
 */
public interface Movable {
    /**
     * Checks if the entity is currently movable.
     *
     * @return {@code true} if the entity is movable, {@code false} otherwise
     */
    boolean isMovable();

    /**
     * Enables the entity's movable state.
     */
    void enable();

    /**
     * Disables the entity's movable state.
     */
    void disable();


}
