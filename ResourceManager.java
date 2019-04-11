import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ResourceManager implements KeyListener, MouseListener {
	private PlayField theField;
	private ArrayList<Card> theHand;
	private ArrayList<Card> theDeck;
	private ArrayList<Sprite> spriteList;
	private GameWindow theGameWindow;
	private JFrame theGameFrame;
	private int cardChosen;
	private boolean isCardChosen;
	private PlayerObject thePlayer;
	private boolean readyToRender;
	private int maxHandSize;
	private String bottomLeftText[];

	public ResourceManager(String gameName, int xScreenSize, int yScreenSize, int xBoardSize, int yBoardSize) {
		// This method is the constructor for the resource manager. If xSize and ySize
		// are 0, it will full screen

		// Create a new playing field
		theField = new PlayField(xBoardSize, yBoardSize);

		// Make the player object and add it to the field
		thePlayer = new PlayerObject(0, xBoardSize / 2, yBoardSize - 1);
		theField.spawnPlayer(thePlayer, thePlayer.getYCoordinates(), thePlayer.getXCoordinates());

		// Set up the cards
		theHand = new ArrayList<Card>(0);
		theDeck = new ArrayList<Card>(0);
		maxHandSize = 5;
		addCardsToDeck(60);
		drawCard();

		// Create a new list of sprites, to be added to as needed
		this.spriteList = new ArrayList<Sprite>();
		this.bottomLeftText = new String[1];
		this.bottomLeftText[0] = "Welcome";

		// The size of the window will be gotten dynamically as the user can change the
		// size, it should not be stored as
		// a separate variable. Begin the rendering process
		this.beginRendering(gameName, xScreenSize, yScreenSize);

		// Prepare to take input
		cardChosen = 0;
		isCardChosen = false;

		// Wait until the game is ready to render
		while (!readyToRender) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// Thread stopped while waiting
			}
		}

	}

	public PlayField getPlayField() {
		// This method should only ever be called by the game manager in order to be
		// able to move things around the player field
		return this.theField;
	}

	private void beginRendering(String gameName, int xScreenSize, int yScreenSize) {
		// This method creates a separate thread for rendering the screen
		readyToRender = false;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createWindow(gameName, xScreenSize, yScreenSize);
			}
		});
	}

	private void createWindow(String gameName, int xScreenSize, int yScreenSize) {
		// This creates a container for the game, known as a "JFrame"
		// Then adds the extended "JPanel" object called "theGameWindow" to the frame
		// that we can
		// use to render things with.
		theGameFrame = new JFrame(gameName);
		theGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Pass the playing field and sprite list by reference so they can be rendered
		// by the game window.
		this.theGameWindow = new GameWindow(this.theField, this.spriteList, this.theHand, this.bottomLeftText);
		theGameFrame.add(this.theGameWindow);
		if (xScreenSize == 0 && yScreenSize == 0) {
			theGameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		} else {
			theGameFrame.setSize(new Dimension(xScreenSize, yScreenSize));
		}
		theGameFrame.setResizable(true);
		theGameFrame.setVisible(true);
		theGameFrame.setBackground(Color.BLACK);
		theGameWindow.setFocusable(true);
		theGameWindow.addKeyListener(this);
		theGameWindow.addMouseListener(this);
		// The game is now ready to render, and the resourceManager can finish it's
		// constructor
		readyToRender = true;

	}

	public boolean drawCard() {
		// This method draws a card from the deck and adds it to the hand
		if (theDeck.size() == 0) {
			return false;
		}
		// If there is an empty space in the hand...
		for (int i = theHand.size(); i < maxHandSize; i++) {

			// Add a card from the deck to the hand
			theHand.add(theDeck.get(theDeck.size() - 1));
			// Remove that card from the deck
			theDeck.remove(theDeck.size() - 1);

		}
		System.out.println("There are " + theDeck.size() + " Cards remaining in the deck!");
		return true;
	}

	public void playCard(int cardNo) {
		// This method plays the card, then removes it from the hand
		theHand.get(cardNo - 1).play(theField);
		theHand.remove(cardNo - 1);
		drawCard();
		bottomLeftText[0] = "Played the " + cardNo + "th card!";
	}

	private void addCardsToDeck(int deckSize) {
		// This method adds cards to the deck to make a default deck
		for (int i = 0; i < deckSize; i++) {
			// Add a random card from the options
			theDeck.add(new Card());
		}
	}

	public void repaintWindow() {
		// repaints the window
		theGameWindow.repaint();
	}

	public int getInput() {
		// This method gets input for the GameManager
		while (!isCardChosen) {
			try {
				Thread.sleep(10);
				// Sleep until the key has been pressed
			} catch (InterruptedException e) {
				// Thread has been closed while sleeping
				System.out.println("bye!");
			}
		}
		// When a key has been pressed, return the key that was pressed only once
		isCardChosen = false;
		return cardChosen;
	}

	// The key press events
	@Override
	public void keyPressed(KeyEvent e) {
		// No actions need to be taken on key pressed, we are only logging keys typed
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// No actions need to be taken on key release, we are only logging keys typed
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// When a key is pressed, store the pressed key and let the primary thread know
		// to read it if it is a valid input
		char keyPressed = e.getKeyChar();
		cardChosen = Character.getNumericValue(keyPressed);
		if (cardChosen > 0 && cardChosen <= theHand.size()) {
			isCardChosen = true;
		}
		if ((int) cardChosen == -1) {
			// This is the escape key
			isCardChosen = true;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// This method will choose the clicked card

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// No actions need to be taken on this method, we are only logging mouse clicks.
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// No actions need to be taken on this method, we are only logging mouse clicks.
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// No actions need to be taken on this method, we are only logging mouse clicks.
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// No actions need to be taken on this method, we are only logging mouse clicks.
	}

	public void stopRendering() {
		theGameWindow.removeKeyListener(this);
		theGameFrame.dispose();
	}

}