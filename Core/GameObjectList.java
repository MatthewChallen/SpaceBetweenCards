package Core;

public class GameObjectList {
	private GameObject[] gridObjects = new GameObject[2];
	
	public GameObjectList() {
		
	}
	// Adds object to list, checks first to see if object already in array index and then adds objects to array
	public void addObject(GameObject gameObject) {
		if (gridObjects[0] instanceof GameObject) {
			gridObjects[1] = gameObject;
		}
		
		else {
			gridObjects[0] = gameObject;
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
	
	public void RemovedAll() {
	    for(int i =0; i< gridObjects.length;++i) {
	        if(gridObjects[i] != null){
	            if(gridObjects[i] instanceof PlayerObject) {
	                System.out.println("removed player");
	            } else {
	                System.out.println("removed bullet" + gridObjects[i].getID());
	            }
	            gridObjects[i] = null;
	        }
	        
	    }
	}
	
	//removes specific object. returns false if object not found.
	public boolean removeObject(GameObject target) {
        for(int i =0; i < gridObjects.length; ++i) {
            if(target == gridObjects[i])
            {
                gridObjects[i] = null;
                return true;
            }
        }
        
        return false;
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

