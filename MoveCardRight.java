public class MoveCardRight extends Card {

	public MoveCardRight(String name, String description) {
		super(name, description);
	}

	public String getCardFileName() {
		
		//This returns the name of the image that is rendered for this object
		return "cardShipRight.png";
	}
	
	public boolean play(PlayField theField) {
		// Moves the player one space to the Right.
		// Assumed a movePlayer() + getPlayer() method
		// both in the PlayField class but can be changed
		theField.movePlayerObject("right");		

		

		return true;
	}
}
