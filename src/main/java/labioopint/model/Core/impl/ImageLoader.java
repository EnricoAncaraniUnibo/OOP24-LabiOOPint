package labioopint.model.Core.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.File;

public class ImageLoader {
    private static Map<String,Image> imageMap;

    public static void load() {
        imageMap = new HashMap<>();
        try {
            imageMap.put("Corridor",ImageIO.read(new File("src/main/java/labioopint/resources/Tiles/Corridor.png")));
            imageMap.put("Corner", ImageIO.read(new File("src/main/java/labioopint/resources/Tiles/Corner.png")));
            imageMap.put("Crossing", ImageIO.read(new File("src/main/java/labioopint/resources/Tiles/Crossing.png")));
            imageMap.put("Archer",ImageIO.read(new File("src/main/java/labioopint/resources/Characters/archer.png")));
			imageMap.put("Mage",ImageIO.read(new File("src/main/java/labioopint/resources/Characters/mage.png")));
			imageMap.put("Warrior",ImageIO.read(new File("src/main/java/labioopint/resources/Characters/warrior.png")));
			imageMap.put("Thief",ImageIO.read(new File("src/main/java/labioopint/resources/Characters/thief.png")));
			imageMap.put("ArcherTurn",ImageIO.read(new File("src/main/java/labioopint/resources/Characters/ArcherTurn.gif")));
			imageMap.put("MageTurn",ImageIO.read(new File("src/main/java/labioopint/resources/Characters/MageTurn.gif")));
			imageMap.put("WarriorTurn",ImageIO.read(new File("src/main/java/labioopint/resources/Characters/WarriorTurn.gif")));
			imageMap.put("ThiefTurn",ImageIO.read(new File("src/main/java/labioopint/resources/Characters/thiefTurn.gif")));
            imageMap.put("Monster", ImageIO.read(new File("src/main/java/labioopint/resources/Characters/monster.png")));
            imageMap.put("Teleport", ImageIO.read(new File("src/main/java/labioopint/resources/PowerUps/teleport.png")));
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public static Optional<Image> getImage(String name) {
        if(imageMap.containsKey(name)) {
            return Optional.ofNullable(imageMap.get(name));
        }
        return Optional.empty();
    }
}
