package Cards;

import Core.Card;
import Core.PlayField;
import Core.ResourceManager;
import TypeListings.Direction;

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
	
    public int getDistance() {
    	return distance;
    }
	
	public boolean OnPlay(PlayField theField) {
		// Moves the player one space to the Left.
		// Assumed a movePlayer() + getPlayer() method
		// both in the PlayField class but can be changed
	    theField.moveObject(Direction.LEFT, ResourceManager.GetRM().getPlayer(), distance);

		return true;
	}
}
