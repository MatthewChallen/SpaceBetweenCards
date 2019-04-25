import TypeListings.ObjectType;

public class PlayField {
	private GameObjectList[][] playGrid;
    private int playGridXSize;
    private int playGridYSize;
	
	public PlayField(int y, int x) {
		this.playGridXSize = x;
	    this.playGridYSize = y;
		playGrid = new GameObjectList[y][x];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				playGrid[i][j] = new GameObjectList();
			}
		}
		spawnPlayer(x, y);
		
	}
	
	// Spawns player at requested point
	public void spawnPlayer(int x, int y) {
	    x = x/2;
	    y = y-1;
	    playGrid[x][y].addObject(ResourceManager.GetRM()[0].GetNewObject(ObjectType.PLAYERSHIP, x, y));
	}
	
	
	// Checks for collision and then supplies string of object ID's
	public String checkForCollision() {
		for (int i = 0; i < playGrid.length; i++) {
			for (int j = 0; j < playGrid.length; j++) {
				if (playGrid[i][j].isThereCollision() == true) {
					return playGrid[i][j].getObjectsID();
				}
			}
		}
		
		return null;	
	}
	// calls to destroy the object/s, GameManager will need to take number of objects to be destroyed, maximum is 2
	public void destroyObjects(int y, int x, int noToDestroy) {
		playGrid[y][x].removeObject(noToDestroy);	
	}
	
	// Method for moving player around the board
	// Checks board for player object and then moves the object to another GameObjectList
	// Code can be reduced to check for player before case and then implement the move
	// Exception still needs to be implemented to stop player moving off board
	public void moveObject(String direction, GameObject [] target, int distance) {
		int x = target[0].getXCoordinates();
		int y = target[0].getYCoordinates();
	    
	    switch(direction) {
		case "left" :
			if(playGrid[x][y].removeObject(target)){
			    x -= distance;
			    if(x < 0) {
			        x=0;
			    }
			    playGrid[x][y].addObject(target);
			    target[0].setXYCoordinates(x, y);
			}
			break;
		case "right" :
		    if(playGrid[x][y].removeObject(target)){
                x += distance;
                if(x >= playGridXSize) {
                    x=playGridXSize-1;
                }
                playGrid[x][y].addObject(target);
                target[0].setXYCoordinates(x, y);
            }
			break;
		case "up" :
		    if(playGrid[x][y].removeObject(target)){
                y -= distance;
                if(y < 0) {
                    y=0;
                }
                playGrid[x][y].addObject(target);
                target[0].setXYCoordinates(x, y);
            }
			break;
		case "down" :
		    if(playGrid[x][y].removeObject(target)){
                y += distance;
                if(y >= playGridYSize) {
                    y=playGridYSize-1;
                }
                playGrid[x][y].addObject(target);
                target[0].setXYCoordinates(x, y);
            }
			break;
		
		}
	}
	
	//Moves the field down a space to prepare for the next turn
	public void moveField() {
		
	}
	
	//This moves all the objects in motion once such as projectiles
	public void moveAllObjects() {
		
	}
	
	public String getObjectFileName(int xLocation, int yLocation) {
		//Returns the filename for the sprite, so it can be rendered
		return playGrid[xLocation][yLocation].getObjectFileName();
	}
	
	public int getPlayGridXSize() {
		//Returns the grid size
        return this.playGridXSize;
    }

    public int getPlayGridYSize() {
    	//Returns the grid size
        return this.playGridYSize;
    }
}
