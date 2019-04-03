
public class GameObjectList {
	GameObjects[] gridObjects = new GameObjects[2];
	
	public GameObjectList() {
		
	}
	
	public void addObject(GameObjects a) {
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
		if (gridObjects[0] instanceof GameObjects && gridObjects[1] instanceof GameObjects) {
			return true;
		}
		return false;		
	}
	
	public String getObjectsID() {
		return gridObjects[0].getObjectID() + " " + gridObjects[1].getObjectID();
	}
}

