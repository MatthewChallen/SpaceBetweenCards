package Core;
import TypeListings.ObjectType;

public class PlayField {
	private GameObjectList[][] playGrid;
    private int playGridXSize;
    private int playGridYSize;
    private int start;
	
	public PlayField(int x, int y) {
		this.playGridXSize = x;
	    this.playGridYSize = y;
		playGrid = new GameObjectList[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				playGrid[i][j] = new GameObjectList();
			}
		}
		spawnPlayer(x, y);
		start =0;
		
	}
	
	private int GetVisualY(int y) {
	    y += start;
	    y %= playGridYSize;
	    return y;
	}
	
	public void update() {
	    
	    for(int i = 0; i < playGridXSize; ++i) {
            playGrid[i][GetVisualY(playGridYSize)].RemovedAll();
        }
	    
	    start--;
	    if(start < 0) {
	        start = playGridYSize-1;
	    }
	    System.out.println("Staring line: " +start);
	    
	    
	}
	
	// Spawns player at requested point
	public void spawnPlayer(int x, int y) {
	    x = x/2;
	    y = y-3;
	    playGrid[x][GetVisualY(y)].addObject(ResourceManager.GetRM().GetNewObject(ObjectType.PLAYERSHIP, x, GetVisualY(y)));
	}
	
	public void spawnObject(ObjectType type, int x, int y) {
	    if(x>=0 && x < playGridXSize && y >=0 && y < playGridYSize) {
	        playGrid[x][GetVisualY(y)].addObject(ResourceManager.GetRM().GetNewObject(type, x, GetVisualY(y)));
	    }
	}
	
	// Checks for collision and then supplies string of object ID's
	public String checkForCollision() {
	    //maybe playGridXSize and playGridYSize
		for (int i = 0; i < playGrid.length; i++) {
			for (int j = 0; j < playGrid.length; j++) {
				if (playGrid[i][GetVisualY(j)].isThereCollision() == true) {
					return playGrid[i][GetVisualY(j)].getObjectsID();
				}
			}
		}
		
		return null;	
	}
	// calls to destroy the object/s, GameManager will need to take number of objects to be destroyed, maximum is 2
	public void destroyObjects(int y, int x, int noToDestroy) {
		playGrid[x][GetVisualY(y)].removeObject(noToDestroy);	
	}
	
	// Method for moving player around the board
	// Checks board for player object and then moves the object to another GameObjectList
	// Code can be reduced to check for player before case and then implement the move
	// Exception still needs to be implemented to stop player moving off board
	public void moveObject(String direction, GameObject target, int distance) {
		int x = target.getXCoordinates();
		int y = target.getYCoordinates();
	    
	    switch(direction) {
		case "left" :
			if(playGrid[x][y].removeObject(target)){
			    x -= distance;
			    if(x < 0) {
			        x=0;
			    }
			    playGrid[x][y].addObject(target);
			    target.setXYCoordinates(x, y);
			}
			break;
		case "right" :
		    if(playGrid[x][y].removeObject(target)){
                x += distance;
                if(x >= playGridXSize) {
                    x=playGridXSize-1;
                }
                playGrid[x][y].addObject(target);
                target.setXYCoordinates(x, y);
            }
			break;
		case "up" :
		    if(playGrid[x][y].removeObject(target)){
                y -= distance;
                if(GetVisualY(y) < 0) {
                    
                    if(target instanceof PlayerObject) {
                        y=start;
                        
                    }else {
                      ResourceManager.GetRM().RemoveGameObject(target);
                      break;
                    }
                    
                    //y=0;
                }
                playGrid[x][y].addObject(target);
                target.setXYCoordinates(x, y);
            }
			break;
		case "down" :
		    if(playGrid[x][y].removeObject(target)){
                y += distance;
                if(target instanceof PlayerObject)
                {
                    if(GetVisualY(y) >= playGridYSize) {
                        y=(playGridYSize-1+start)%playGridYSize;
                    }
                    playGrid[x][y].addObject(target);
                    target.setXYCoordinates(x, y);
                }else {
                    ResourceManager.GetRM().RemoveGameObject(target);
                    break;
                  }
            }
			break;
		}
	    

        ResourceManager.bottomLeftText = "Player Y:" + target.getYCoordinates();

	    if(target instanceof PlayerObject) {
	        System.out.println("Player X: " + target.getXCoordinates() +", player Y: " + (target.getYCoordinates()+1));
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
		return playGrid[xLocation][GetVisualY(yLocation)].getObjectFileName();
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
