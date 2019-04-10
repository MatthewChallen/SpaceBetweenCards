public class ShootCardUp extends Card {

	public ShootCardUp(String name, String description) {
		super(name, description);
	}

	public String getCardFileName() {
		
		//This returns the name of the image that is rendered for this object
		return "StrawberryShootUp.jpg";
	}
	
	public boolean play(PlayField theField) {
		
		// Assumed a spawnProjectile method, similar to spawnPlayer()
	    // Spawns projectile up, down, left or right of the player
		
		// Assumed a movePlayer() + getPlayer() method
		// both in the PlayField class but can be changed
		theField.spawnProjectile(theField.getPlayer().getYCoordinates() + 1, theField.getPlayer().getXCoordinates());
		return true;
	}
}
