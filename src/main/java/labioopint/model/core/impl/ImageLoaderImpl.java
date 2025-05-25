package labioopint.model.core.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import labioopint.model.core.api.ImageLoader;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * The ImageLoader class is responsible for loading and managing images used in
 * the application.
 * It provides methods to load images from files and retrieve them by name.
 */
public final class ImageLoaderImpl implements ImageLoader {
        public static final long serialVersionUID = 1L;
        private transient Map<String, Image> imageMap;

        /**
         * Construct that initilize the map tha associate a name with his image.
         */
        public ImageLoaderImpl() {
                imageMap = new HashMap<>();
        }

        private void readObject(final java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
                in.defaultReadObject();
                imageMap = new HashMap<>();
                load();
        }

        /**
         * Method to load an image from resources.
         * 
         * @param resourcePath the path to the resource
         * @return the loaded Image
         * @throws IOException if the image cannot be loaded
         */
        private Image loadImageFromResource(final String resourcePath) throws IOException {
                try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
                                if (inputStream == null) {
                                                throw new IOException("Resource not found: " + resourcePath);
                                }
                                return ImageIO.read(inputStream);
                }
        }

        @Override
        public void load() throws IOException {
                imageMap = new HashMap<>();
                imageMap.put("Corridor", loadImageFromResource("/labioopint/resources/Tiles/Corridor.png"));
                imageMap.put("Corner", loadImageFromResource("/labioopint/resources/Tiles/Corner.png"));
                imageMap.put("Crossing", loadImageFromResource("/labioopint/resources/Tiles/Crossing.png"));
                imageMap.put("Archer", loadImageFromResource("/labioopint/resources/Characters/archer.png"));
                imageMap.put("Mage", loadImageFromResource("/labioopint/resources/Characters/mage.png"));
                imageMap.put("Warrior", loadImageFromResource("/labioopint/resources/Characters/warrior.png"));
                imageMap.put("Thief", loadImageFromResource("/labioopint/resources/Characters/thief.png"));
                imageMap.put("ArcherTurn", loadImageFromResource("/labioopint/resources/Characters/ArcherTurn.gif"));
                imageMap.put("MageTurn", loadImageFromResource("/labioopint/resources/Characters/MageTurn.gif"));
                imageMap.put("WarriorTurn", loadImageFromResource("/labioopint/resources/Characters/WarriorTurn.gif"));
                imageMap.put("ThiefTurn", loadImageFromResource("/labioopint/resources/Characters/thiefTurn.gif"));
                imageMap.put("Monster", loadImageFromResource("/labioopint/resources/Characters/monster.png"));
                imageMap.put("Teleport", loadImageFromResource("/labioopint/resources/PowerUps/teleport.png"));
                imageMap.put("Double Turn", loadImageFromResource("/labioopint/resources/PowerUps/double.png"));
                imageMap.put("Invulnerability", loadImageFromResource("/labioopint/resources/PowerUps/invincible.png"));
                imageMap.put("Steal Object", loadImageFromResource("/labioopint/resources/PowerUps/steal.png"));
                imageMap.put("Corridor To PowerUp",
                                loadImageFromResource("/labioopint/resources/PowerUps/corridortopower.png"));
        }

        @Override
        public Optional<Image> getImage(final String name) {
                if (imageMap.containsKey(name)) {
                        return Optional.ofNullable(imageMap.get(name));
                }
                return Optional.empty();
        }
}
