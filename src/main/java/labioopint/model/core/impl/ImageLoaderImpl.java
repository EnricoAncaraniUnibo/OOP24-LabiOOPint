package labioopint.model.core.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import labioopint.model.core.api.ImageLoader;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

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

        @Override
        public void load() throws IOException {
                imageMap = new HashMap<>();
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
                imageMap.put("Double Turn", ImageIO.read(
                                new File("src/main/java/labioopint/resources/PowerUps/double.png")));
                imageMap.put("Invulnerability", ImageIO.read(
                                new File("src/main/java/labioopint/resources/PowerUps/invincible.png")));
                imageMap.put("Steal Object", ImageIO.read(
                                new File("src/main/java/labioopint/resources/PowerUps/steal.png")));
                imageMap.put("Corridor To PowerUp", ImageIO.read(
                                new File("src/main/java/labioopint/resources/PowerUps/corridortopower.png")));
        }

        @Override
        public Optional<Image> getImage(final String name) {
                if (imageMap.containsKey(name)) {
                        return Optional.ofNullable(imageMap.get(name));
                }
                return Optional.empty();
        }
}
