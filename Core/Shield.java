package Core;

import Cards.ShieldCard;
import TypeListings.Direction;
import TypeListings.ObjectType;

public class Shield extends GameObject {
	
	private int shieldPercentage;
        
    public Shield(PlayerObject stopper, int shieldPercentage) {
        super(stopper.getXCoordinates(), stopper.getYCoordinates());
        this.shieldPercentage = shieldPercentage;
    }
    
    public Shield(int x, int y) {
        super(x, y);
        shieldPercentage = 100;
    }
    
    
    public String getObjectFileName() {
        //This method returns null, because an object should never be of just GameObject, it should always be an extended version
        return "Shield.png";
    }
}