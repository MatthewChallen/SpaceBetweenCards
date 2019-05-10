package Core;

public class PlayerObject extends GameObject {

	private boolean shieldStatus;

	public PlayerObject(int objectID, int xCoordinate, int yCoordinate, boolean shieldStatus) {
		super(objectID, xCoordinate, yCoordinate);
		this.shieldStatus = shieldStatus;
	}

	// Changing image of playerObject (Shielded/Non Shielded)
	public String getObjectFileName() {
		if (this.shieldStatus == false)
			return "playerShip.png";
		else
			return "playerShipShield.png";
	}

	public boolean getShieldStatus() {
		return this.shieldStatus;
	}

	// Changing status of shield (default state false)
	// Can change to int values and have multiple shield
	// states (level 1, 2 ,3 etc. )
	public void setShieldStatus() {
		if (this.shieldStatus == false) {
			this.shieldStatus = true;
		} else {
			System.out.println("Player can only use one shield at a time");
		}
	}

}
