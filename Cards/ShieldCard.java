package Cards;

import Core.Card;
import Core.PlayField;
import Core.PlayerObject;
//import Core.ResourceManager;
//import TypeListings.Direction;
import Core.ResourceManager;
import TypeListings.ObjectType;

public class ShieldCard extends Card {

	private boolean status;
	private int power;
    
	public ShieldCard(String name, String description, int power) {
        super(name, description);
        status = false;
        this.power = power;
    }

    public String getCardFileName() {
        // This returns the name of the image that is rendered for this object
        return "Shield.png";
    }
    
    public boolean getStatus() {
    	return status;
    }
 

    public boolean OnPlay(PlayerObject player) {
    	player.setShieldStatus();
    	return true;
    }
}
