import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameWindow extends JPanel {
	private PlayField theField;
	private ArrayList<Sprite> spriteList;

	public GameWindow(PlayField theField, ArrayList<Sprite> spriteList) {
		// This creates a new window, passing the things that need to be displayed by
		// reference
		this.theField = theField;
		this.spriteList = spriteList;
	}

	public void paint(Graphics Screen) {
		//This method is called by the system, but can be called by the resource manager via the "repaint" method
		int xSize = 10;
		int ySize = 10;
		int screenHeight = this.getHeight();
		int screenWidth = this.getWidth();
		String fileName = null;
		boolean foundSprite = false;
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				//Go through the player field, and render the objects
				fileName = theField.getObjectFileName(i, j);
				
				if (fileName != null) {
					//Search the sprite list for the sprite with that filename and render it
					for (int k = 0; k < spriteList.size(); k++) {
						if (fileName.contentEquals(spriteList.get(k).getName())) {
							spriteList.get(k).paint(Screen, (int)(i*0.1*screenWidth),(int)(j*0.1*screenHeight) , (int)(0.05*screenHeight), (int)(0.05*screenWidth));
							foundSprite = true;
						}
					}
					//If the sprite is not found, attempt to load it.
					if(!foundSprite) {
						loadNewSprite(fileName);
					}
				}

			}
		}
	}
	
	private void loadNewSprite(String fileName) {
		//Loads a sprite with the mentioned filename and adds it to the loaded sprites
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fileName));
		}catch(IOException e) {
			System.err.println("Failed to load sprite with filename " + fileName);
		}
		if(img != null) {
			spriteList.add(new Sprite(img, fileName));
			System.out.println("Successfully added file: " + fileName);
		}
	}
}
