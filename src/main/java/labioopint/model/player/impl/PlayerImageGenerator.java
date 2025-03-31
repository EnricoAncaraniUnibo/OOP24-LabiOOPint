package labioopint.model.player.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.File;

public class PlayerImageGenerator {
	private final List<Image> imageList;
	private final List<Image> imageListTurn;
	
	public PlayerImageGenerator() {
		imageList = new ArrayList<>();
		imageListTurn = new ArrayList<>();
		try {
			imageList.add(ImageIO.read(new File("src/main/java/labioopint/resources/Characters/archer.png")));
			imageList.add(ImageIO.read(new File("src/main/java/labioopint/resources/Characters/mage.png")));
			imageList.add(ImageIO.read(new File("src/main/java/labioopint/resources/Characters/warrior.png")));
			imageList.add(ImageIO.read(new File("src/main/java/labioopint/resources/Characters/thief.png")));
			imageListTurn.add(ImageIO.read(new File("src/main/java/labioopint/resources/Characters/ArcherTurn.gif")));
			imageListTurn.add(ImageIO.read(new File("src/main/java/labioopint/resources/Characters/MageTurn.gif")));
			imageListTurn.add(ImageIO.read(new File("src/main/java/labioopint/resources/Characters/WarriorTurn.gif")));
			imageListTurn.add(ImageIO.read(new File("src/main/java/labioopint/resources/Characters/thiefTurn.gif")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}

	public List<Image> getRandomImage() {
		Random ran = new Random();
		int seed = ran.nextInt(0,imageList.size());
		List<Image> main = new ArrayList<>();
		main.add(imageList.get(seed));
		main.add(imageListTurn.get(seed));
		imageList.remove(seed);
		imageListTurn.remove(seed);
		return main;
	}
}
