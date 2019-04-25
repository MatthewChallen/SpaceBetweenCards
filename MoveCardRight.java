public class MoveCardRight extends Card {
    
    private int distance;

    public MoveCardRight(String name, String description) {
        super(name, description);
        distance = 1;
    }

    public MoveCardRight(String name, String description, int dist) {
        super(name, description);
        distance = dist;
    }

	public String getCardFileName() {
		
		//This returns the name of the image that is rendered for this object
		return "cardShipRight.png";
	}
	
	public boolean OnPlay(PlayField [] theField) {
		// Moves the player one space to the Right.
		// Assumed a movePlayer() + getPlayer() method
		// both in the PlayField class but can be changed
		theField[0].moveObject("right", ResourceManager.GetRM()[0].GetPlayer(), distance);
		return true;
	}
}
