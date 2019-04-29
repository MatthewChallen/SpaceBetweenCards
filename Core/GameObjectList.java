package Core;

import java.util.ArrayList;

public class GameObjectList {
	private ArrayList<GameObject> gridObjects = new ArrayList<GameObject>();

	public GameObjectList() {

	}

	// Adds object to list, checks first to see if object already in array index and
	// then adds objects to array
	public void addObject(GameObject gameObject) {
		gridObjects.add(gameObject);
	}

	//Removes all objects from the board
	public void removedAll() {
		while (gridObjects.size() > 0) {
			ResourceManager.GetRM().RemoveGameObject(gridObjects.get(0));
			gridObjects.remove(0);
		}

	}

	// removes specific object. returns false if object not found.
	public boolean removeObject(GameObject target) {
		return gridObjects.remove(target);
	}

	// Checks for collision and returns true or false depending. True = collision
	// and False = no collision
	public boolean isThereCollision() {
		if(gridObjects.size() > 1) {
			return true;
		}else {
			return false;
		}
	}

	// Gets the object ID
	public String getObjectsID() {
		return gridObjects.get(0).getObjectID() + " " + gridObjects.get(1).getObjectID();
	}

	// Supplies object to PlayField
	public GameObject whatObjectIsThis() {
		return gridObjects.get(0);
	}

	public String getObjectFileName() {
		String fileName = null;
		if (gridObjects.size() > 0) {
			fileName = gridObjects.get(0).getObjectFileName();
		}
		return fileName;
	}

}
