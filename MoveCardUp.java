public class MoveCardUp extends Card {

	public MoveCardUp(String name, String description) {
		super(name, description);
	}

	public String getCardFileName() {
		
		//This returns the name of the image that is rendered for this object
		return "cardShipUp.png";
	}
	
	public boolean OnPlay(PlayField theField) {
		// Moves the player one space to the Up.
		// Assumed a movePlayer() + getPlayer() method
		// both in the PlayField class but can be changed
	    theField.moveObject("up", ResourceManager.GetRM()[0].GetPlayer(), 1);	


		return true;
	}
}
