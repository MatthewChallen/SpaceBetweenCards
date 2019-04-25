public class MoveCardLeft extends Card {
    
    private int distance;

    public MoveCardLeft(String name, String description) {
        super(name, description);
        distance = 1;
    }

    public MoveCardLeft(String name, String description, int dist) {
        super(name, description);
        distance = dist;
    }

	public String getCardFileName() {
		
		//This returns the name of the image that is rendered for this object
		return "cardShipLeft.png";
	}
	
	public boolean OnPlay(PlayField [] theField) {
		// Moves the player one space to the Left.
		// Assumed a movePlayer() + getPlayer() method
		// both in the PlayField class but can be changed
	    theField[0].moveObject("left", ResourceManager.GetRM()[0].GetPlayer(), distance);

		return true;
	}
}
