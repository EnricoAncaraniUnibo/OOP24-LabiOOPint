package labioopint.model.player.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.File;

public class PlayerImageGenerator {
	private final List<Image> imageList;
	
	public PlayerImageGenerator() {
		imageList = new ArrayList<>();
		try {
			imageList.add(ImageIO.read(new File("src/main/java/labioopint/resources/Characters/archer.png")));
			imageList.add(ImageIO.read(new File("src/main/java/labioopint/resources/Characters/mage.png")));
			imageList.add(ImageIO.read(new File("src/main/java/labioopint/resources/Characters/warrior.png")));
			imageList.add(ImageIO.read(new File("src/main/java/labioopint/resources/Characters/thief.png")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}

	public Image getRandomImage() {
		Random ran = new Random();
		int seed = ran.nextInt(0,imageList.size());
		Image main = imageList.get(seed);
		imageList.remove(seed);
		return main;
	}
}
