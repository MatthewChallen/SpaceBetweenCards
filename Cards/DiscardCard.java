package Cards;

import Core.Card;
import Core.Deck;
import Core.Hand;
import Core.PlayField;
import Core.ResourceManager;
import TypeListings.Direction;

public class DiscardCard extends Card {

	public DiscardCard(String name, String description) {
		super(name, description);
	}
	
	public boolean OnPlay(Hand hand) {

		// Discards all cards first
		hand.DiscardAll();
		// Re-draws a completely new hand
		for (int i = 0; hand.GetCardCount() < 5; i++) {
			hand.DrawCard(i);
		}
        return true;
    }
	
	public String getCardFileName() {
        // This returns the name of the image that is rendered for this object
        return "Discard.png";
    }

}
