

public class GameManager {

	// Create an instance of the resource manager

	ResourceManager theResourceManager;

	public static void main(String args[]) {
		GameManager spaceBetweenCards = new GameManager();
		spaceBetweenCards.run();
	}

	public GameManager() {
		theResourceManager = new ResourceManager("The Space Between Cards", 0, 0);
	}

	public void run() {
		// This method contains the game logic loop (no rendering)
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
