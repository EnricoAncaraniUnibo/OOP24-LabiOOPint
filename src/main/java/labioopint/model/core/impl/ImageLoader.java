package labioopint.model.core.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.File;

/**
 * The ImageLoader class is responsible for loading and managing images used in
 * the application.
 * It provides methods to load images from files and retrieve them by name.
 */
public final class ImageLoader {
    private static Map<String, Image> imageMap;

    private ImageLoader() {
    }

    /**
     * Loads images from the specified file paths and stores them in a map for later
     * retrieval.
     * The images are categorized by their names, which are used as keys in the map.
     * If an error occurs during loading, the exception is printed to the console.
     */
    public static void load() {
        imageMap = new HashMap<>();
        try {
            imageMap.put("Corridor", ImageIO.read(
                    new File("src/main/java/labioopint/resources/Tiles/Corridor.png")));
            imageMap.put("Corner", ImageIO.read(
                    new File("src/main/java/labioopint/resources/Tiles/Corner.png")));
            imageMap.put("Crossing", ImageIO.read(
                    new File("src/main/java/labioopint/resources/Tiles/Crossing.png")));
            imageMap.put("Archer", ImageIO.read(
                    new File("src/main/java/labioopint/resources/Characters/archer.png")));
            imageMap.put("Mage", ImageIO.read(
                    new File("src/main/java/labioopint/resources/Characters/mage.png")));
            imageMap.put("Warrior", ImageIO.read(
                    new File("src/main/java/labioopint/resources/Characters/warrior.png")));
            imageMap.put("Thief", ImageIO.read(
                    new File("src/main/java/labioopint/resources/Characters/thief.png")));
            imageMap.put("ArcherTurn", ImageIO.read(
                    new File("src/main/java/labioopint/resources/Characters/ArcherTurn.gif")));
            imageMap.put("MageTurn", ImageIO.read(
                    new File("src/main/java/labioopint/resources/Characters/MageTurn.gif")));
            imageMap.put("WarriorTurn", ImageIO.read(
                    new File("src/main/java/labioopint/resources/Characters/WarriorTurn.gif")));
            imageMap.put("ThiefTurn", ImageIO.read(
                    new File("src/main/java/labioopint/resources/Characters/thiefTurn.gif")));
            imageMap.put("Monster", ImageIO.read(
                    new File("src/main/java/labioopint/resources/Characters/monster.png")));
            imageMap.put("Teleport", ImageIO.read(
                    new File("src/main/java/labioopint/resources/PowerUps/teleport.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves an image by its name from the loaded images.
     *
     * @param name the name of the image to retrieve
     * @return an Optional containing the image if found, or an empty Optional if
     *         not found
     */
    public static Optional<Image> getImage(final String name) {
        if (imageMap.containsKey(name)) {
            return Optional.ofNullable(imageMap.get(name));
        }
        return Optional.empty();
    }
}
