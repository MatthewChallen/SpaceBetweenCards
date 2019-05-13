package Core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class EndGameScreen extends JPanel
{
	private ResourceManager resourceManager;
	private JFrame theGameFrame;
	private BufferedImage background;
	private int width;
	private int height;
	
	private Box gameOverBox;
	private JLabel gameOver;
	private JLabel message1;
	private JLabel message2;
	
	private String chosenMessage1;
	private String chosenMessage2;
	private String[] messages =
    {"You ejected from warp drive too early and your crew's organic",
     "material has been scattered over 2 million parsecs",
     "Your engine officer just ate a depleted uranium sandwich",
     "",
     "Paranioa inducing gas valve opened: the crew have attacked", 
     "and devoured each other in a cannibalistic frenzy",
     "Hull breach confirmed: the vacuum of space has depleted",
     "your oxygen supply, leaving you speechless",
     "Fuel pod ignited: the residents of Alpha Centauri mistook",
     "your ship for a fireworks display"
    };
	
	public EndGameScreen(ResourceManager resourceManager, int width, int height)
	{
		this.resourceManager = resourceManager;
		theGameFrame = resourceManager.getGameFrame();
		this.width = width;
		this.height = height;
		
		try
		{
			background = ImageIO.read(new File("Backgrounds/endgame.jpg"));
			
		} catch (Exception ex)
		{
			System.out.println("Failed to load background - check folder.");
		}
		
		// Set up a random choice for the game over message.
		int choice = (int)(Math.random() * 5) * 2;
		chosenMessage1 = messages[choice];
		chosenMessage2 = messages[choice + 1];
		displayGameOverMessage();
	}
	
	public void paint(Graphics screen)
	{
		width = this.getWidth();
		height = this.getHeight();
		if(background != null)
		{
			
			screen.drawImage(background, 0, 0, width, height, null);
		} else
		{
			screen.setFont(new Font("Arial", Font.BOLD, 20));
			screen.drawString("Failed to load background image", width / 2 - 150,
		       100);
		}
		
		paintMessage();
	}
	
	public void paintMessage()
	{
		gameOverBox.removeAll();
		gameOverBox.add(Box.createVerticalStrut(100));
		
		gameOverBox.add(gameOver);
		gameOver.repaint();
		gameOverBox.add(Box.createVerticalStrut(height / 40));
		
		gameOverBox.add(message1);
		message1.repaint();
		gameOverBox.add(message2);
		message2.repaint();
		gameOverBox.add(Box.createVerticalStrut(height / 40));
	}
	
	public void displayGameOverMessage()
	{
		gameOverBox = Box.createVerticalBox();
		gameOverBox.add(Box.createVerticalStrut(100));
			
		gameOver = new JLabel("G A M E   O V E R");
		MenuStyle.styleLabel(gameOver);
		gameOver.setForeground(Color.YELLOW);
		gameOver.setFont(new Font("Arial", Font.BOLD, 40));
		gameOverBox.add(gameOver);
		gameOverBox.add(Box.createVerticalStrut(height / 40));
		
		message1 = new JLabel(chosenMessage1);
		MenuStyle.styleLabel(message1);
		gameOverBox.add(message1);
		message2 = new JLabel(chosenMessage2);
		MenuStyle.styleLabel(message2);
		gameOverBox.add(message2);
		gameOverBox.add(Box.createVerticalStrut(height / 40));
		
		add(gameOverBox);
		
	}
}
