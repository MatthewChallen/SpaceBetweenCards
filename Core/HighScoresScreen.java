package Core;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class HighScoresScreen extends JPanel
{
	private ResourceManager resourceManager;
	private JFrame theGameFrame;
	private BufferedImage background;
	private int width;
	private int height;
	
	private Box highScores;
	private JLabel highScore1;
	private JLabel highScore2;
	private JLabel highScore3;
	private JLabel highScore4;
	private JLabel highScore5;
	private JButton mainMenu;
	
	public HighScoresScreen(ResourceManager resourceManager, int width, int height)
	{
		this.resourceManager = resourceManager;
		theGameFrame = resourceManager.getGameFrame();
		this.width = width;
		this.height = height;
		
		try
		{
			background = ImageIO.read(new File("Backgrounds/high_scores.jpg"));
			
		} catch (Exception ex)
		{
			System.out.println("Failed to load background - check folder.");
		}
		
		addHighScores();
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
		
		paintHighScores();
	}
	
	public void paintHighScores()
	{
		highScores.removeAll();
		highScores.add(Box.createVerticalStrut(height / 4));
		highScores.add(highScore1);
		highScore1.repaint();
		highScores.add(Box.createVerticalStrut(height / 20));
		highScores.add(highScore2);
		highScore2.repaint();
		highScores.add(Box.createVerticalStrut(height / 20));
		highScores.add(highScore3);
		highScore3.repaint();
		highScores.add(Box.createVerticalStrut(height / 20));
		highScores.add(highScore4);
		highScore4.repaint();
		highScores.add(Box.createVerticalStrut(height / 20));
		highScores.add(highScore5);
		highScore5.repaint();
		highScores.add(Box.createVerticalStrut(height / 20));
		highScores.add(mainMenu);
		mainMenu.repaint();
	}
	
	public void addHighScores()
	{
		highScores = Box.createVerticalBox();
		highScores.add(Box.createVerticalStrut(height / 4));
		
		highScore1 = new JLabel("Vladimir Putin    38");
		MenuStyle.styleLabel(highScore1);
		highScores.add(highScore1);
		highScores.add(Box.createVerticalStrut(height / 20));
		
		highScore2 = new JLabel("William Shatner     24");
		MenuStyle.styleLabel(highScore2);
		highScores.add(highScore2);
		highScores.add(Box.createVerticalStrut(height / 20));
		
		highScore3 = new JLabel("Nancy Pelosi     53");
		MenuStyle.styleLabel(highScore3);
		highScores.add(highScore3);
		highScores.add(Box.createVerticalStrut(height / 20));
		
		highScore4 = new JLabel("Karl Stefanovic     14");
		MenuStyle.styleLabel(highScore4);
		highScores.add(highScore4);
		highScores.add(Box.createVerticalStrut(height / 20));
		
		highScore5 = new JLabel("Elon Musk    16");
		MenuStyle.styleLabel(highScore5);
		highScores.add(highScore5);
		highScores.add(Box.createVerticalStrut(height / 20));
		
		setupButton();
		
		add(highScores);
	}
	
	public void setupButton()
	{
		mainMenu = new JButton("Main Menu");
		MenuStyle.styleButton(mainMenu);
		
		mainMenu.addActionListener(e ->
		{
			this.setVisible(false);
			theGameFrame.add(new TitleScreen(resourceManager, width, height));
		});
		highScores.add(mainMenu);
	}
}
