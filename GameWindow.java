import java.awt.Color;
import java.awt.Font;
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
	private ArrayList<Card> theHand;
	private double topSectionPercentage;
	private double bottomSectionPercentage;
	private String[] bottomLeftText;

	public GameWindow(PlayField theField, ArrayList<Sprite> spriteList, ArrayList<Card> theHand, String[] bottomLeftText) {
		// This creates a new window, passing the things that need to be displayed by
		// reference
		this.theField = theField;
		this.spriteList = spriteList;
		this.theHand = theHand;
		this.topSectionPercentage = 0.2;
		this.bottomSectionPercentage = 0.2;
		this.bottomLeftText = bottomLeftText;
	}

	public void paint(Graphics Screen) {
		// This method is called by the system, but can be called by the resource
		// manager via the "repaint" method
		Screen.setFont(new Font("Game Font", Font.PLAIN, 24));
		System.out.println(bottomLeftText[0]);
		Screen.setColor(Color.BLACK);
		Screen.fillRect(0, 0, this.getWidth(), this.getHeight());
		Screen.setColor(Color.WHITE);
		Screen.drawString(bottomLeftText[0], 20, this.getHeight()-20);
		paintBackGround(Screen);
		paintBoard(Screen);
		paintCards(Screen);
		
	}

	private void paintBoard(Graphics Screen) {
		//This method paints the game board and all objects on it
		
		//These are the size of the board and screen
		int xSize = theField.getPlayGridXSize();
		int ySize = theField.getPlayGridYSize();
		int screenHeight = this.getHeight();
		int screenWidth = this.getWidth();
		//This is the amount of space at the bottom and the top of the screen for the cards the player and the enemies can play
		int bottomOffSet = (int)(bottomSectionPercentage*screenHeight+((1-topSectionPercentage-bottomSectionPercentage)*screenHeight)/xSize);
		int topOffSet = (int)(topSectionPercentage*screenHeight);
		//This is the dimensions of the sprites, adjusted to the amount of space they have
		int spriteHeight = ((screenHeight - bottomOffSet - topOffSet)/ySize);
		int spriteWidth = screenWidth/xSize;
		String fileName = null;
		boolean foundSprite = false;
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				// Go through the player field, and render the objects
				fileName = theField.getObjectFileName(j, i);
				if (fileName != null) {
					// Search the sprite list for the sprite with that filename and render it
					for (int k = 0; k < spriteList.size(); k++) {
						if (fileName.contentEquals(spriteList.get(k).getName())) {
							spriteList.get(k).paint(Screen,i*spriteWidth,
									topOffSet + j*spriteHeight, spriteHeight,
									spriteWidth);
							foundSprite = true;
						}
					}
					// If the sprite is not found, attempt to load it. Then render the most recently added sprite
					if (!foundSprite) {
						loadNewSprite(fileName);
						spriteList.get(spriteList.size()-1).paint(Screen,i*spriteWidth,
								topOffSet + j*spriteHeight, spriteHeight,
								spriteWidth);
						
					}
				}

			}
		}
	}

	private void paintBackGround(Graphics Screen) {
		//This method paints the background
		
	}

	private void paintCards(Graphics Screen) {
		//This method paints the cards in the players hand
		
		//These are the size of the board and screen
		int xSize = theField.getPlayGridXSize();
		int ySize = theField.getPlayGridYSize();
		int screenHeight = this.getHeight();
		int screenWidth = this.getWidth();
		//This is the amount of space from the bottom that the cards have to be shown in
		int bottomOffSet = (int)(bottomSectionPercentage*screenHeight+((1-topSectionPercentage-bottomSectionPercentage)*screenHeight)/xSize);
		//These are the dimensions of the sprites
		int spriteHeight = bottomOffSet;
		int spriteWidth = spriteHeight/2;
		//This is the amount space to the left of the cards
		int leftOffSet = (int) (screenWidth - theHand.size()*spriteWidth)/2;
		//These variables are the filename and a controller for the loops
		String fileName = null;
		boolean foundSprite = false;
		//Go though all the cards in the hand
		for(int i = 0; i < theHand.size(); i++) {
			//Finding the filename of the sprite to render
			fileName = theHand.get(i).getCardFileName();
			//Finding the associated sprite
			if(fileName != null) {
				for(int j = 0; j < spriteList.size(); j++) {
					//if this is the correct sprite, render it!
					if(fileName.contentEquals(spriteList.get(j).getName())) {
						spriteList.get(j).paint(Screen, leftOffSet+i*spriteWidth, screenHeight-spriteHeight, spriteHeight, spriteWidth);
						foundSprite = true;
					}
				}
				if(!foundSprite) {
					//If we didn't find the sprite in the list of loaded sprites, attempt to load it and render the most recently added sprite!!
					loadNewSprite(fileName);
					spriteList.get(spriteList.size()-1).paint(Screen, screenHeight-spriteHeight, leftOffSet+i*spriteWidth, spriteHeight, spriteWidth);
				}
			}
		}
	}

	private void loadNewSprite(String fileName) {
		// Loads a sprite with the mentioned filename and adds it to the loaded sprites
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			System.err.println("Failed to load sprite with filename " + fileName);
		}
		if (img != null) {
			spriteList.add(new Sprite(img, fileName));
			System.out.println("Successfully added file: " + fileName);
		}
	}
}
