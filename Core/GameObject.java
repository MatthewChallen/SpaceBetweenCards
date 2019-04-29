package Core;

import TypeListings.Direction;

public abstract class GameObject {
	private int objectID;
	private int health;
	private int xCoordinate;
	private int yCoordinate;

	static private int ID = 1;

	protected GameObject(int objectID, int xCoordinate, int yCoordinate) {
		this.objectID = objectID;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.health = 0;
	}

	public GameObject(int xCoordinate, int yCoordinate) {
		this.objectID = ID++;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.health = 0;
	}

	public void Update(PlayField field) {
	}

	public int getID() {
		return objectID;
	}

	public void setYCoordinate(int y) {
		yCoordinate = y;
	}

	public void setXCoordinate(int x) {
		xCoordinate = x;
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
	
	//This method must be implemented in any subclasses, so that they can be rendered
	public abstract String getObjectFileName();
	
	public Direction getDirection() {
		return null;
	}
	
	public int getSpeed() {
		return 0;
	}
}