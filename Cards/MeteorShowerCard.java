package Cards;

import Core.Card;
import Core.Enemy;
import Core.GameObject;
import Core.PlayField;

public class MeteorShowerCard extends Card {

	public MeteorShowerCard(String name, String description) {
		super(name, description);
	}
	
	public String getCardFileName() {
        // This returns the name of the image that is rendered for this object
        return "MeteorShower.png";
    }
	
    public boolean OnPlay() {
    	// Yet to be completed method
    	return true;
    	
    }

}
