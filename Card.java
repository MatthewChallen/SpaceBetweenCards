
public class Card {
	public boolean play(PlayField theField) {
		// This method does the cards effect. Is overwritten for individual cards. Uses
		// methods on the passed PlayField to do so. See PlayField for the methods.
		// If the card cannot be played (it would move the player off the board, for
		// example) then the method returns false. Otherwise, it returns true.
		return false;
	}
	
	public String getCardFileName() {
		//This method returns a string of the filename of the JPG file that has the image of the card.
		//This method is overwritten for individual cards.
		return "strawberry.jpg";
	}
}
