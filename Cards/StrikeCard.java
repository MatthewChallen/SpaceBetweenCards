package Cards;

import Core.Card;

public class StrikeCard extends Card{

	public StrikeCard(String name, String description) {
		super(name, description);
	}
	public String getCardFileName() {
        // This returns the name of the image that is rendered for this object
        return "Lightning.png";
    }
	
	public boolean OnPlay() {
    	// Yet to be completed method
    	return true;
    	
    }

}
