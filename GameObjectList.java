
public class GameObjectList {
	GameObject[] gridObjects = new GameObject[2];
	
	public GameObjectList() {
		
	}
	// Adds object to list, checks first to see if object already in array index and then adds objects to array
	public void addObject(GameObject a) {
		if (gridObjects[0] instanceof GameObject) {
			gridObjects[1] = a;
		}
		
		else {
			gridObjects[0] = a;
		}
	}
	
	// Removes objects from the board, allows to choose how many need to be removed
	public void removeObject(int numObjectsToRemove) {
		int a = numObjectsToRemove;
		if ( a > 3) {
			a = 2;
		}
		if (a == 1) {
		gridObjects[0] = null;
		}
		else {
			gridObjects[0] = null;
			gridObjects[1] = null;
		}
	}
	
	// Checks for collision and returns true or false depending. True = collision and False = no collision
	public boolean isThereCollision(){
		if (gridObjects[0] instanceof GameObject && gridObjects[1] instanceof GameObject) {
			return true;
		}
		return false;		
	}
	
	// Gets the object ID
	public String getObjectsID() {
		return gridObjects[0].getObjectID() + " " + gridObjects[1].getObjectID();
	}
	
	// Supplies object to PlayField
	public GameObject whatObjectIsThis() {
		return gridObjects[0];
	}
	
	public String getObjectFileName() {
		String fileName = null;
		if(gridObjects[0] != null) {
			fileName = gridObjects[0].getObjectFileName();
		}
		return fileName;
	}
	
}
