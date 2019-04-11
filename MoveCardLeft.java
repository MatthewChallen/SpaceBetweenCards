public class MoveCardLeft extends Card {

	public MoveCardLeft(String name, String description) {
		super(name, description);
	}

	public String getCardFileName() {
		
		//This returns the name of the image that is rendered for this object
		return "StrawberryMoveLeft.jpg";
	}
	
	public boolean play(PlayField theField) {
		System.out.println("yo bro");
		// Moves the player one space to the Left.
		// Assumed a movePlayer() + getPlayer() method
		// both in the PlayField class but can be changed
		theField.movePlayerObject("left");	
		return true;
	}
}
