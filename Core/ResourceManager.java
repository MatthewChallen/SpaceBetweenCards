package Core;
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

import TypeListings.ObjectType;

public class ResourceManager implements KeyListener, MouseListener {
    private PlayField theField;
    private Deck[] theDecks;
    //private ArrayList<Card> theHand;
    private Hand[] theHand; 
    //private ArrayList<Card> theDeck;
    private ArrayList<Sprite> spriteList;
    private ArrayList<GameObject> objectList;
    private ArrayList<GameObject> objectKillList;
    private GameWindow theGameWindow;
    private JFrame theGameFrame;
    private int cardChosen;
    private boolean isCardChosen;
    private boolean readyToRender;
    private int maxHandSize;
    
    //made static so other's could use it as debug text
    private static String bottomLeftText[];
    
    
    //Made instance avalible so classes can request objects.
    static private ResourceManager[] instance = new ResourceManager[1];
    static public ResourceManager[] GetRM() {
        return instance;
    }

    // This method is the constructor for the resource manager. If xSize and ySize
    // are 0, it will full screen
    public ResourceManager(String gameName, int xScreenSize, int yScreenSize, int xBoardSize, int yBoardSize,
            Deck[] theDecks, Hand[] theHand) {

        instance[0] = this;

        // Set up the cards
        this.theDecks = theDecks;
        this.theHand = theHand;
        //theHand = new ArrayList<Card>(0);
        //theDeck = new ArrayList<Card>(0);
        maxHandSize = 5;
        theHand[0].DrawCard(maxHandSize);
        
        
        //addCardsToDeck(60);
        //drawCard();

        // Create a new list of sprites, to be added to as needed
        this.spriteList = new ArrayList<Sprite>();
        this.bottomLeftText = new String[1];
        this.bottomLeftText[0] = "Welcome";


        
        // Create a new list of game objects.
        this.objectList = new ArrayList<GameObject>(0);
        //buffer for objects to remove
        this.objectKillList = new ArrayList<GameObject>(0);
        

        // Create a new playing field
        theField = new PlayField(xBoardSize, yBoardSize);

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

    // This method should only ever be called by the game manager once in order to
    // be
    // able to move things around the player field
    public PlayField getPlayField() {
        return this.theField;
    }

    // This method creates a separate thread for rendering the screen
    private void beginRendering(String gameName, int xScreenSize, int yScreenSize) {
        readyToRender = false;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createWindow(gameName, xScreenSize, yScreenSize);
            }
        });
    }

    // This creates a container for the game, known as a "JFrame"
    // Then adds the extended "JPanel" object called "theGameWindow" to the frame
    // that we can use to render things with.
    private void createWindow(String gameName, int xScreenSize, int yScreenSize) {
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

    public int GetObjectListSize()
    {
        return objectList.size();
    }
    
    public GameObject GetObjectListElement(int index) {
        return objectList.get(index);
    }
    
    public void RemoveGameObject(GameObject target) {
        objectKillList.add(target);
    }
    
    public void Update() {
        while(objectKillList.size() > 0) {
            for(int i = objectList.size()-1; i>=0;--i) {
                if(objectKillList.get(0) == objectList.get(i)) {
                    objectKillList.remove(0);
                    objectList.remove(i);
                    i=-1;
                }
            }
        }
    }
    
    /*
    // This method draws a card from the deck and adds it to the hand
    public boolean drawCard() {
        if (theDeck.size() == 0) {
            return false;
        }
        // If there is an empty space in the hand...
        for (int i = theHand[0].GetCardCount(); i < maxHandSize; i++) {

            // Add a card from the deck to the hand
            theHand[0].DrawCard();
            // Remove that card from the deck

        }
        System.out.println("There are " + theDeck.size() + " Cards remaining in the deck!");
        return true;
    }*/

    /*
    // This method plays the card, then removes it from the hand. Returns true if
    // the cardNo is a valid input
    public boolean playCard(int cardNo) {
        bottomLeftText[0] = "Played the " + theHand.get(cardNo - 1).getName() + " card!";
        theHand[0].
        theHand.get(cardNo - 1).play(theField);
        theHand.remove(cardNo - 1);
        drawCard();
        return true;
    }*/

    
    /*
    // This method adds cards to the deck to make a default deck
    private void addCardsToDeck(int deckSize) {
        for (int i = 0; i < deckSize; i++) {
            // Add a random card from the options
            theDeck.add(new MoveCardLeft("Move Card left", "Moves the player left"));
            theDeck.add(new MoveCardUp("Move Card up", "Moves the player up"));
            theDeck.add(new MoveCardDown("Move Card down", "Moves the player down"));
            theDeck.add(new MoveCardRight("Move Card right", "Moves the player right"));
        }
    }*/

    public PlayerObject GetPlayer() {
        PlayerObject hold = null;
        for(int i=0;i< objectList.size(); ++i)
        {
            if(objectList.get(i).getID() == 0)
            {
                hold = (PlayerObject) objectList.get(i);
                i = objectList.size();
            } else {
                hold = null;
            }
        }
        return hold;
    }
    //returns 1 element array on the selected game object.
    public GameObject GetNewObject(ObjectType type, int X, int Y) {

        // temp array to return

        GameObject hold = null;
        switch (type) {
        case PLAYERSHIP:
            hold = new PlayerObject(0, X, Y);
            objectList.add(hold);
            break;
        case PROJECTILE:
            hold = new Projectile(X, Y);
            objectList.add(hold);
            break;
        default:
            return null;
        }
        return hold;

    }

    public void repaintWindow() {
        // repaints the window
        theGameWindow.repaint();
    }

    // This method gets input for the GameManager
    public int getInput() {
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
        //bottomLeftText[0] = "Played the " + cardChosen + " card!";
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
        if (cardChosen > 0 && cardChosen <= theHand[0].GetCardCount()) {
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

    // This method ends the rendering process
    public void stopRendering() {
        theGameWindow.removeKeyListener(this);
        theGameFrame.dispose();
    }

}