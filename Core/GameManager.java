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
		boolean running = true;
		int cardChosen;
		
        theResourceManager.repaintWindow();
		
        // Start the loop
		while (running) {
			// wait for player input
			cardChosen = theResourceManager.getInput();
			// Choose a card with the input and play it or close
			if ((int) cardChosen == -1) {
				// This is the escape key. Avada Kedavara!
				running = false;
				System.out.println("Goodbye!");
			} else {
			    theHand.PlayCard(theField, cardChosen);
			    theHand.DrawCard(5);
			    Update();
				//theResourceManager.playCard(cardChosen - 0);
				// theField.newTurn();
				// Rerender the screen
				theResourceManager.repaintWindow();
			}
		}
		theResourceManager.stopRendering();
	}
	
	private void Update() {
	    int size = theResourceManager.GetObjectListSize();
	    for(int i = 0; i <size; ++i) {
	        theResourceManager.GetObjectListElement(i).Update(theField);
	    }
	    
	    theField.update();
	    theResourceManager.Update();
	}

	/*public boolean checkEndConditions() {
		boolean running = true;
		// If the Player is not on the board, stop the game
		if (theField.getPlayerLocation()[0] >= 0 && theField.getPlayerLocation()[0] < theField.getPlayGridXSize()
				&& theField.getPlayerLocation()[1] >= 0
				&& theField.getPlayerLocation()[1] < theField.getPlayGridYSize()) {
			//The player is on the field, the game can continue!
		}else {
			//The player is not on the field!
			running = false;
		}
		
		//If the enemy deck is out of cards, stop the game
		if(theDecks[2].GetCardCount() == 0) {
			running = false;
		}
		
		return running;
	}*/
}
