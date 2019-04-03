
public class PlayField {
	GameObjectList[][] playGrid = new GameObjectList[10][10];
	
	public PlayField() {
	}
	
	public void spawnPlayer(playerObject a, int y, int x) {
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
	
}
