package labioopint.controller.api;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;

import labioopint.model.utilities.api.Pair;
/**
 * Represents the logic for managing the drawing panel in the game.
 * This interface provides methods to manage the drawing part.
 */
public interface LogicDrawPanel extends Serializable {

    /**
     * Retrieves the size of a default value in the drawing panel, calculated with proportions.
     *
     * @return the pixel size as an {@link Integer}
     */
    Integer getPixelSize();

    /**
     * Determines if the drawing panel is allowed to draw.
     *
     * @return {@code true} if drawing is allowed, {@code false} otherwise
     */
    Boolean canDraw();

    /**
     * Retrieves a list of images and their associated blocks to be drawn on the panel.
     * Each entry in the list contains a pair of an image and its scaling factor,
     * along with the coordinates and dimensions of the block.
     *
     * @return a {@link List} of {@link Pair} objects representing the images and blocks
     */
    List<Pair<Pair<Image, Double>, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>> getImagesBlocks();


}
