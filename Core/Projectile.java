package Core;

import TypeListings.Direction;

public class Projectile extends GameObject {

    int speed = 2;
    Direction direction;
        
    public Projectile(PlayerObject shooter, int speed) {
        super(shooter.getXCoordinates(), shooter.getYCoordinates());
        if(speed >0) {
            direction = Direction.UP;
            this.speed = speed;
        } else {
            direction = Direction.DOWN;
            this.speed = 0-speed;
        }
    }
    public Projectile(int x, int y) {
        super(x, y);
              
        direction = Direction.UP;
        
    }
    public Projectile(int x, int y, int speed) {
        super(x, y);
              
        this.speed = speed;
        if(speed >0) {
            direction = Direction.UP;
            this.speed = speed;
        } else {
            direction = Direction.DOWN;
            this.speed = 0-speed;
        }
    }
    
    public void Update(PlayField field) {
        field.moveObject("up", this, speed);
    }

    
    public String getObjectFileName() {
        //This method returns null, because an object should never be of just GameObject, it should always be an extended version
        if(direction == Direction.UP) {
            return "projectile.png";
        } else {
            return "projectile_down.png";
        }
        
    }
    
}
