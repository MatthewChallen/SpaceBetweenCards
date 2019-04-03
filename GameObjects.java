public abstract class GameObjects {
	private int objectID;
	private int health;
    private int xCoordinate;
    private int yCoordinate;

    public GameObjects(int objectID, int xCoordinate, int yCoordinate) {
            this.objectID = objectID;
            this.xCoordinate = xCoordinate;
            this.yCoordinate =yCoordinate;
            this.health = 0;
        }
    
    public int getObjectID() {
        return this.objectID;
    }

    public int getXCoordinates() {
        return this.xCoordinate;
    }
    
    public int getYCoordinates() {
        return this.yCoordinate;
    }
    
    public void setXYCoordinates(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }
    
   public void setHealth(int health) {
	   this.health = health;
   }
   
   public int getHealth() {
	   return this.health;
   }
   
   
}