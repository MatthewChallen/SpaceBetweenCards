
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
		spawnPlayer(new PlayerObject(0, x / 2, y - 1), y -1, x / 2);
	}
	
	// Spawns player at requested point
	public void spawnPlayer(PlayerObject a, int y, int x) {
		playGrid[y][x].addObject(a);
	}
	
	public int[] getPlayerLocation() {
		//Finds the player, and gives their location
		int[] location = new int[2];
		location [0] = -1;
		location [1] = -1;
		for(int i = 0; i < playGridXSize; i++) {
			for(int j = 0; j < playGridYSize; j++) {
				//If it is the player object at this space
				if(playGrid[i][j].whatObjectIsThis() instanceof PlayerObject) {
					//Get their location and return it at the end
					location[0] = playGrid[i][j].whatObjectIsThis().getXCoordinates();
					location[1] = playGrid[i][j].whatObjectIsThis().getYCoordinates();
				}
			}
		}
		return location;
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
	public void movePlayerObject(String direction) {
		switch(direction) {
		case "left" :
			for (int i = 0; i < playGrid.length; i++) {
				for (int j = 0; j < playGrid[i].length; j++) {
					if (playGrid[i][j].whatObjectIsThis() instanceof PlayerObject) {
						playGrid[i][j-1].addObject(playGrid[i][j].whatObjectIsThis());
						playGrid[i][j].removeObject(1); ;
						break;
					}
				}
			}
			break;
		case "right" :
			for (int i = 0; i < playGrid.length; i++) {
				for (int j = 0; j < playGrid[i].length; j++) {
					if (playGrid[i][j].whatObjectIsThis() instanceof PlayerObject) {
						playGrid[i][j+1].addObject(playGrid[i][j].whatObjectIsThis());
						playGrid[i][j].removeObject(1); ;
						break;
					}
				}
			}
			break;
		case "up" :
			for (int i = 0; i < playGrid.length; i++) {
				for (int j = 0; j < playGrid[i].length; j++) {
					if (playGrid[i][j].whatObjectIsThis() instanceof PlayerObject) {
						playGrid[i-1][j].addObject(playGrid[i][j].whatObjectIsThis());
						playGrid[i][j].removeObject(1); ;
						break;
					}
				}
			}
			break;
		case "down" :
			for (int i = 0; i < playGrid.length; i++) {
				for (int j = 0; j < playGrid[i].length; j++) {
					if (playGrid[i][j].whatObjectIsThis() instanceof PlayerObject) {
						playGrid[i+1][j].addObject(playGrid[i][j].whatObjectIsThis());
						playGrid[i][j].removeObject(1); ;
						break;
					}
				}
			}
			break;
		
		}
	}
	
	//Moves the field down a space to prepare for the next turn
	public void moveField() {

	}
	
	//This moves all the objects in motion such as projectiles
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
