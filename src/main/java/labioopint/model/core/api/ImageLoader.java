package labioopint.model.core.api;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
/**
 * Class used to load the images to draw.
 */
public interface ImageLoader extends Serializable {

    /**
         * Loads images from the specified file paths and stores them in a map for later
         * retrieval.
         * The images are categorized by their names, which are used as keys in the map.
         * If an error occurs during loading, the exception is printed to the console.
         * @throws IOException 
         */
    void load() throws IOException;

    /**
         * Retrieves an image by its name from the loaded images.
         *
         * @param name the name of the image to retrieve
         * @return an Optional containing the image if found, or an empty Optional if
         *         not found
         */
    Optional<Image> getImage(String name);

}
