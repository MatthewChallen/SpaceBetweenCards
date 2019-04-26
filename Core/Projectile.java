package Core;

public class Projectile extends GameObject {

    int speed;
        
    public Projectile(PlayerObject shooter, int speed) {
        super(shooter.getXCoordinates(), shooter.getYCoordinates());
              
        this.speed = speed;
    }
    public Projectile(int x, int y) {
        super(x, y);
              
        this.speed = 1;
    }
    
    public void Update(PlayField field) {
        field.moveObject("up", this, speed);
    }

    
    public String getObjectFileName() {
        //This method returns null, because an object should never be of just GameObject, it should always be an extended version
        return "projectile.png";
    }
    
}
