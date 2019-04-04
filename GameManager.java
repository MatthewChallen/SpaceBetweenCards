

public class GameManager {
	PlayField theField;
	// Create an instance of the resource manager

	ResourceManager theResourceManager;

	public static void main(String args[]) {
		GameManager spaceBetweenCards = new GameManager();
		spaceBetweenCards.run();
	}

	public GameManager() {
		theResourceManager = new ResourceManager("The Space Between Cards", 0, 0);
		theField = theResourceManager.getPlayField();
		
	}

	public void run() {
		// This method contains the game logic loop (no rendering)
		// Firstly, make the player object at with id 1 at location 3, 3
		this.theField.spawnPlayer(new PlayerObject(1, 3, 3),  3, 3);
		boolean running = true;
		char input;
		
		// Start the loop
		while (running) {
			//wait for player input
			input = theResourceManager.getInput();
			switch(input) {
			case 1:
			}
			// Choose a card with the input or close
			System.out.println(input);
			// Rerender the screen
			theResourceManager.repaintWindow();
		}
	}
	

}
