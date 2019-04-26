package Core;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Sprite {
	// This is a stored image that will be displayed at certain locations on
	// request.
	private BufferedImage img;
	private String name;
	private int height;
	private int width;

	public Sprite(BufferedImage img, String name) {
		this.img = img;
		this.name = name;
		this.height = img.getHeight();
		this.width = img.getWidth();
	}

	public void paint(Graphics Screen, int xLocation, int yLocation, int height, int width) {
		// This method paints the sprite at the chosen location.
		
		// Draw it
		Screen.drawImage(this.img, xLocation, yLocation, width, height, null);
	}

	public String getName() {
		return this.name;

	}

}
