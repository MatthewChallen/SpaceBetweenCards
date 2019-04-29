package Core;
import java.util.concurrent.TimeUnit;

public class GameManager {
    final private int fieldXSize = 10;
    final private int fieldYSize = 12;
	private PlayField theField;
	private Deck[] theDecks;
	private Hand theHand;
	//private int handSize;
	// Create an reference to a resource manager
	ResourceManager theResourceManager;
	private static String gameState;

	public static void main(String args[]) {
		GameManager spaceBetweenCards = new GameManager();
		spaceBetweenCards.run();
	}

	public GameManager() {
		// This method is the constructor. Will make the starting instances of all
		// objects by calling the constructors of other classes
		
		//Set up all the decks
		theHand = new Hand(); 
		Deck thePlayerDeck = new Deck();
		Deck theEnemyDeck = new Deck();
		Deck theFieldDeck = new Deck();
		Deck[] theDecks = {thePlayerDeck, theFieldDeck, theEnemyDeck};

        theHand.SetDrawDeck(theDecks[0]);
		
		//Make the resource manager
		theResourceManager = new ResourceManager("The Space Between Cards", 0, 0, fieldXSize, fieldYSize, theDecks, theHand);
		
		//Get a reference to the Play Field from the resource manager
		theField = theResourceManager.getPlayField();
	}

	public void run() {
		// This method contains the game logic loop (no rendering)
		// Firstly, make the player object at with id 1 at location 3, 3
		 GameManager.gameState = "running";
		int cardChosen;
		
        theResourceManager.repaintWindow();
		
        // Start the loop
		while (GameManager.gameState.equals("running")) {
			// wait for player input
			cardChosen = theResourceManager.getInput();
			// Choose a card with the input and play it or close
			if ((int) cardChosen == -1) {
				// This is the escape key. Avada Kedavara!
				GameManager.setGameState("Closed");
				System.out.println("Goodbye!");
			} else {
			    theHand.PlayCard(theField, cardChosen);
			    theHand.DrawCard(5);
			    Update();
				//Move objects in motion
			    ResourceManager.GetRM().resetMove();
			    ResourceManager.GetRM().moveObjects();
				
				// Rerender the screen
				theResourceManager.repaintWindow();
			}
		}
		theResourceManager.stopRendering();
	}
	
	private void Update() {
	    theField.update();
	    theResourceManager.Update();
	}
	
	public static void setGameState(String gameState) {
		GameManager.gameState = "gameState";
	}
}
