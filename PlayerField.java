public abstract class PlayerField {
	private int objectID;
    private int xCoordinate;
    private int yCoordinate;

    public PlayerField(int objectID, int xCoordinate, int yCoordinate) {
            this.objectID = objectID;
            this.xCoordinate = xCoordinate;
            this.yCoordinate =yCoordinate;
        }
    
    public int getObjectID() {
        return this.objectID;
    }

    public int getXYCoordinates() {
        return this.xCoordinate + this.yCoordinate;
    }
    
    public void setXYCoordinates(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }
}