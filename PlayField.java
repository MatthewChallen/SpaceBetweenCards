
public class PlayField {
	GameObjectList[][] playGrid;
	
	public PlayField(int y, int x) {
		playGrid = new GameObjectList[y][x];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				playGrid[j][i] = new GameObjectList();
			}
		}
	}
	
	public void spawnPlayer(PlayerObject a, int y, int x) {
		playGrid[y][x].addObject(a);
	}
	
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
		
	public void destroyObjects(int y, int x, int noToDestroy) {
		playGrid[y][x].removeObject(noToDestroy);	
	}
	
	public String getObjectFileName(int y, int x) {
		return playGrid[y][x].getObjectFileName();
	}
	
	public void newTurn() {
		//This method moves the board
	}
	
	public int getXBoardSize() {
		//This method returns the board size
		return 10;
	}
	
	public int getYBoardSize() {
		//This method returns the board size
		return 10;
	}

}