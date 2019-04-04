public class PlayerObject extends GameObject {

	public PlayerObject(int objectID, int xCoordinate, int yCoordinate) {
		super(objectID, xCoordinate, yCoordinate);
		// TODO Auto-generated constructor stub
	}
	
	public String getObjectFileName() {
		//This returns the name of the image that is rendered for this object
		return "strawberry.jpg";
	}

}