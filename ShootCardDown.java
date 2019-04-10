package com.playdis.cards;

import com.playdis.objects.GameObject;
import com.playdis.objects.PlayerObject;

public class ShootCardDown extends Card {

	public ShootCardDown(String name, String description) {
		super(name, description);
	}

	public String getCardFileName() {
		
		//This returns the name of the image that is rendered for this object
		return "StrawberryShootDown.jpg";
	}
	
	public boolean play(PlayField theField) {
		
		// Assumed a spawnProjectile method, similar to spawnPlayer()
	    // Spawns projectile up, down, left or right of the player
		
		// Assumed a movePlayer() + getPlayer() method
		// both in the PlayField class but can be changed
		theField.spawnProjectile(theField.getPlayer().getYCoordinates() - 1, theField.getPlayer().getXCoordinates());
		return true;
	}
}