package com.playdis.cards;

public class MoveCardUp extends Card {

	public MoveCardUp(String name, String description) {
		super(name, description);
	}

	public String getCardFileName() {
		
		//This returns the name of the image that is rendered for this object
		return "StrawberryMoveUp.jpg";
	}
	
	public boolean play(PlayField theField) {
		
		// Moves the player one space to the Up.
		// Assumed a movePlayer() + getPlayer() method
		// both in the PlayField class but can be changed
		theField.movePlayer(theField.getPlayer().getYCoordinates() + 1, theField.getPlayer().getXCoordinates());	
		return true;
	}
}