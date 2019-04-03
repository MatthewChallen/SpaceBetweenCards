public class GameObjectList {
	GameObject[] gridObjects = new GameObject[2];
	
	public GameObjectList() {
		
	}
	
	public void addObject(GameObject a) {
		gridObjects[0] = a;
	}
	
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
	
	public boolean isThereCollision(){
		if (gridObjects[0] instanceof GameObject && gridObjects[1] instanceof GameObject) {
			return true;
		}
		return false;		
	}
	
	public String getObjectsID() {
		return gridObjects[0].getObjectID() + " " + gridObjects[1].getObjectID();
	}
	
	public String getObjectFileName() {
		String fileName = null;
		if(gridObjects[0] != null) {
			fileName = gridObjects[0].getObjectFileName();
		}
		return fileName;
	}
}