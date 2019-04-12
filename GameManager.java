

public class GameManager {
	PlayField theField;
	PlayerObject thePlayer;
	// Create an instance of the resource manager

	ResourceManager theResourceManager;

	public static void main(String args[]) {
		GameManager spaceBetweenCards = new GameManager();
		spaceBetweenCards.run();
	}

	public GameManager() {
		theResourceManager = new ResourceManager("The Space Between Cards", 0, 0, 10, 10);
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
			//wait for player input
			cardChosen = theResourceManager.getInput();
			// Choose a card with the input and play it or close
			if((int)cardChosen == -1) {
				//This is the escape key. Avada Kedavara!
				running = false;
				System.out.println("Goodbye!");
			}else {
				theResourceManager.playCard(cardChosen - 0);
				// theField.newTurn();
				// Rerender the screen
				theResourceManager.repaintWindow();
			}
		}
		theResourceManager.stopRendering();
	}
}


//Testing changes