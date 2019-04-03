import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ResourceManager implements KeyListener {
	private PlayField theField;
	private Deck theDeck;
	private ArrayList<Card> cardList;
	private ArrayList<Sprite> spriteList;
	private GameWindow theGameWindow;
	private JFrame theGameFrame;
	private char keyPressed;
	private boolean isKeyPressed;

	public ResourceManager(String gameName, int xSize, int ySize) {
		if (xSize == 0) {
			xSize = 1000;
		}
		if (ySize == 0) {
			ySize = 1000;
		}
		theField = new PlayField(10, 10);
		theDeck = new Deck();

		this.beginRendering(gameName, xSize, ySize);

		keyPressed = '\0';
		isKeyPressed = false;
		
		this.spriteList = new ArrayList<Sprite>();
	}
	
	public PlayField getPlayField() {
		//This method should only ever be called by the game manager in order to be able to move things around the player field
		return this.theField;
	}

	private void beginRendering(String gameName, int xSize, int ySize) {
		// This method creates a separate thread for rendering the screen
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createWindow(gameName, xSize, ySize);
			}
		});
	}

	private void createWindow(String gameName, int xSize, int ySize) {
		// This creates a container for the game, known as a "JFrame"
		// Then adds the extended "JPanel" object called "theGameWindow" to the frame
		// that we can
		// use to render things with.
		JFrame theGameFrame = new JFrame(gameName);
		theGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Pass the playing field and sprite list by reference so they can be rendered
		// by the game window.
		this.theGameWindow = new GameWindow(this.theField, this.spriteList);
		theGameFrame.add(this.theGameWindow);
		theGameFrame.setSize(new Dimension(xSize, ySize));
		theGameFrame.setResizable(true);
		theGameFrame.setVisible(true);
		theGameFrame.setBackground(Color.BLACK);
		theGameWindow.setFocusable(true);
		theGameWindow.addKeyListener(this);

	}

	public void repaintWindow() {
		//repaints the window
		theGameWindow.repaint();
	}

	public char getInput() {
		while (!isKeyPressed) {
			try {
				Thread.sleep(10);
				// Sleep until the key has been pressed
			} catch (InterruptedException e) {
				// Thread has been closed while sleeping
				System.out.println("bye!");
			}
		}
		//When a key has been pressed, return the key that was pressed only once
		isKeyPressed = false;
		return keyPressed;
	}

	// The key press events
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		//When a key is pressed, store the pressed key and let the primary thread know to read it!
		keyPressed = e.getKeyChar();
		isKeyPressed = true;

	}

}