package Core;

import java.util.Random;

import TypeListings.Direction;
import TypeListings.ObjectType;

public class Enemy extends GameObject {
    private static Random rand = new Random();
    private static boolean seedSet = false;
    private final static long seed = System.currentTimeMillis();
    
    int turn; //Counts each turn it's existed to assist in logic
    int [] AI;
    int AIsteps = 3;
    
    public Enemy(int xCoordinate, int yCoordinate) {
        super(xCoordinate, yCoordinate);
        AI = new int[AIsteps];
        turn = 0;
        
        if (seedSet == false) {
            rand.setSeed(seed);
            seedSet = true;
        }
        
        //Sets a basic AI to repeat a patter of actions
        AI[0] = rand.nextInt(4); //ensures the first played is not "up"
        for(int i = 1; i< AI.length;++i) {
            AI[i] = rand.nextInt(5);
        }
    }

    public void Update(PlayField field) {
        
        switch(AI[turn%AIsteps]) {
        case 0:
            field.spawnObject(ObjectType.PROJECTILE, this.getXCoordinates(), this.getYCoordinates()+1, 0);
            break;
        case 1:
            field.moveObject(Direction.UP, this, 1);
            break;
        case 2:
            field.moveObject(Direction.RIGHT, this, 1);
            break;
        case 3:
            field.moveObject(Direction.DOWN, this, 1);
            break;
        case 4:
            field.moveObject(Direction.LEFT, this, 1);
            break;
        default:
            break;
        }

        turn++;
    }
    
    public String getObjectFileName(){
    	return "strawberry.jpg";
    }
}
