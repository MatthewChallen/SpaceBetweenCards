package Core;

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
	// private ArrayList<Card> theHand;
	private Hand[] theHand;
	private String bottomLeftText;
	private String folderName;
	private int bottomPercentageAllocated;
	private int rightPercentageAllocated;
	private double cardRatio;

	public GameWindow(PlayField theField, ArrayList<Sprite> spriteList, Hand[] theHand, String bottomLeftText) {
		// This creates a new window, passing the things that need to be displayed by
		// reference
		this.theField = theField;
		this.spriteList = spriteList;
		this.theHand = theHand;
		this.cardRatio = 2;
		this.bottomPercentageAllocated = 20;
		this.rightPercentageAllocated = 20;
		this.bottomLeftText = bottomLeftText;

		folderName = "Sprites/";
		// Load the default sprite at space 0
		loadNewSprite("Strawberry.jpg");
	}

	public void paint(Graphics screen) {
		// This method is called by the system, but can be called by the resource
		// manager via the "repaint" method

		// Set variables
		int bottomSpacePixels = this.getHeight() * bottomPercentageAllocated / 100;
		int rightSpacePixels = this.getWidth() * rightPercentageAllocated / 100;
		// Make the left offset the same as the right offset,
		// so that the hand is in the middle
		int leftSpacePixels = rightSpacePixels;
		// Make the top space allocated to the enemy deck the same as the bottom space
		// allocated to the player deck, for symmetry
		int topSpacePixels = bottomSpacePixels;

		// Put the bottom left text on the bottom left
		screen.setFont(new Font("Game Font", Font.PLAIN, 24));
		System.out.println(bottomLeftText);

		// Clear the screen with black
		screen.setColor(Color.BLACK);
		screen.fillRect(0, 0, this.getWidth(), this.getHeight());
		screen.setColor(Color.WHITE);
		screen.drawString(bottomLeftText, 20, this.getHeight() - 20);

		paintBackGround(screen);
		// paintCards(screen);

		// Render the player deck in the bottom left corner
		paintPlayerDeck(screen, 0, this.getHeight() - bottomSpacePixels, leftSpacePixels, bottomSpacePixels);

		// Render the discard pile in the bottom right corner
		paintPlayerDiscardDeck(screen, this.getWidth() - rightSpacePixels, this.getHeight() - bottomSpacePixels,
				rightSpacePixels, bottomSpacePixels);

		// Render the hand in between the two, with the space left over
		paintPlayerHand(screen, leftSpacePixels, this.getHeight() - bottomSpacePixels,
				this.getWidth() - leftSpacePixels - rightSpacePixels, bottomSpacePixels);

		// Render the enemy deck in the top right corner
		paintEnemyDeck(screen, this.getWidth() - rightSpacePixels, 0, rightSpacePixels, topSpacePixels);

		// Render the player field in the top left corner
		paintField(screen, 0, 0, getWidth() - rightSpacePixels, getHeight() - bottomSpacePixels);
	}

	private void paintBackGround(Graphics Screen) {
		// This method paints the background

	}

	private boolean loadNewSprite(String fileName) {
		// Loads a sprite with the mentioned filename and adds it to the loaded sprites
		BufferedImage img = null;
		Boolean doLoad = true;
		// Make sure the sprite isn't already loaded
		for (int i = 0; i < spriteList.size(); i++) {
			if (fileName.contentEquals(spriteList.get(i).getName())) {
				doLoad = false;
			}
		}
		// Load the sprite
		if (doLoad) {
			try {
				img = ImageIO.read(new File(folderName + fileName));
			} catch (IOException e) {
				System.err.println("Failed to load sprite with filename " + fileName);
			}
			if (img != null) {
				// Add the sprite to the list
				spriteList.add(new Sprite(img, fileName));
				System.out.println("Successfully added file: " + fileName);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private void paintPlayerHand(Graphics screen, int xPosition, int yPosition, int xSize, int ySize) {
		// This method paints the players hand in the space specified

		// First, find out if the hand is more constrained by height or width
		double heightConstraint = ySize / cardRatio;
		double widthConstraint = xSize / theHand[0].GetCardCount();

		// declare/initialize other needed variables
		int cardHeight = 0;
		int cardWidth = 0;
		int occupiedSpace = 0;
		int currentXPosition = xPosition;
		String fileName;
		boolean foundSprite = false;

		// Find the restricting dimension on the cards
		if (heightConstraint <= widthConstraint) {
			// If the cards are more constrained by height, base the cards on the available
			// height
			cardHeight = ySize;
			cardWidth = (int) (ySize / cardRatio);
			// Find the excess space, and remove it
			occupiedSpace = cardWidth * theHand[0].GetCardCount();
			xPosition += (xSize - occupiedSpace) / 2;
			xSize = occupiedSpace;
		} else {
			// If the cards are more constrained by width, base the cards on the available
			// width
			cardWidth = xSize / theHand[0].GetCardCount();
			cardHeight = (int) (cardWidth * cardRatio);
			// Find the excess space, and remove it
			occupiedSpace = cardHeight;
			yPosition += (ySize - occupiedSpace) / 2;
			ySize = occupiedSpace;
		}

		// Finally, render the cards
		for (int cardNo = 0; cardNo < theHand[0].GetCardCount(); cardNo++) {
			// Find the name of the cards sprite
			fileName = theHand[0].GetHandList().get(cardNo).getCardFileName();
			if (fileName != null) {
				// Assume the sprite is not found, then search the sprite list for that sprite
				foundSprite = false;
				for (int spriteNo = 0; spriteNo < spriteList.size(); spriteNo++) {
					if (spriteList.get(spriteNo).getName().equals(fileName)) {
						// Sprite found, render.
						foundSprite = true;
						currentXPosition = xPosition + cardNo * cardWidth;
						spriteList.get(spriteNo).paint(screen, currentXPosition, yPosition, cardHeight, cardWidth);
					}
				}
				// If the sprite was never found, load it
				if (!foundSprite) {
					if (loadNewSprite(fileName)) {
						// If the sprite is successfully loaded, render the most recent sprite
						currentXPosition = xPosition + cardNo * cardWidth;
						spriteList.get(spriteList.size() - 1).paint(screen, currentXPosition, yPosition, cardHeight,
								cardWidth);
					}
				}
			}
		}
	}

	private void paintPlayerDeck(Graphics screen, int xPosition, int yPosition, int xSize, int ySize) {
		// This method paints the players deck in the space specified
	}

	private void paintPlayerDiscardDeck(Graphics screen, int xPosition, int yPosition, int xSize, int ySize) {

	}

	private void paintField(Graphics screen, int xStartingPosition, int yStartingPosition, int xSize, int ySize) {
		// This method paints the field in the space specified

		// Get the sprite sizes
		int spriteHeight = ySize / theField.getPlayGridYSize();
		int spriteWidth = xSize / theField.getPlayGridYSize();

		// Declare and initialize other variables
		String fileName = null;
		boolean foundSprite = false;
		int currentXPosition = 0;
		int currentYPosition = 0;
		
		//Set the color to cyan
		screen.setColor(Color.CYAN);
		
		// Render the lines from top to bottom
		for (int x = 0; x < theField.getPlayGridXSize() + 1; x++) {
			currentXPosition = (x * xSize) / theField.getPlayGridXSize();
			screen.drawLine(currentXPosition, yStartingPosition, currentXPosition, yStartingPosition + ySize);
		}
		
		//Render the lines from left to right
		for (int y = 0; y < theField.getPlayGridYSize() + 1; y++) {
			currentYPosition = (y * ySize) / theField.getPlayGridYSize();
			screen.drawLine(xStartingPosition, currentYPosition, xStartingPosition + xSize, currentYPosition);
		}

		// Reduce the size of the sprites slightly, so that they fit within the lines
		spriteHeight--;
		spriteWidth--;

		// Iterate through the player field
		for (int x = 0; x < theField.getPlayGridXSize(); x++) {
			for (int y = 0; y < theField.getPlayGridYSize(); y++) {
				// Get the filename of the sprite at that location, or null if there is none
				fileName = theField.getObjectFileName(x, y);
				// If there is an object at that location to render, continue
				if (fileName != null) {
					// Assume the sprite is not found, then search the sprite list for that sprite
					foundSprite = false;
					for (int spritePosition = 0; spritePosition < spriteList.size(); spritePosition++) {
						if (fileName.equals(spriteList.get(spritePosition).getName())) {
							// Sprite was found, render it
							foundSprite = true;
							// Increase the position by one to fit the lines in to the left rather than the
							// right
							currentXPosition = yStartingPosition + (x * xSize) / theField.getPlayGridXSize() + 1;
							currentYPosition = yStartingPosition + (y * ySize) / theField.getPlayGridYSize() + 1;
							spriteList.get(spritePosition).paint(screen, currentXPosition, currentYPosition,
									spriteHeight, spriteWidth);
						}
					}
					// If the sprite wasn't found, load it
					if (!foundSprite) {
						if (loadNewSprite(fileName)) {
							System.out.println(fileName + " loaded");
							// If the sprite is successfully loaded, render the most recently loaded sprite
							// Increase the position by one to fit the lines in to the left rather than the
							// right
							currentXPosition = yStartingPosition + (x * xSize) / theField.getPlayGridXSize() + 1;
							currentYPosition = yStartingPosition + (y * ySize) / theField.getPlayGridYSize() + 1;
							spriteList.get(spriteList.size() - 1).paint(screen, currentXPosition, currentYPosition,
									spriteHeight, spriteWidth);
						}
					}
				}

			}
		}

	}

	private void paintEnemyDeck(Graphics screen, int xPosition, int yPosition, int xSize, int ySize) {
		// This method paints the enemy deck in the space specified
	}
}