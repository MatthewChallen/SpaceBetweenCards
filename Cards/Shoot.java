package Cards;

import Core.Card;
import Core.PlayField;
import Core.ResourceManager;
import TypeListings.ObjectType;

public class Shoot extends Card {
    
    int speed;
    public Shoot(String name, String description) {
        super(name, description);
        speed = 1;
    }

    public Shoot(String name, String description, int speed) {
        super(name, description);
        this.speed = speed;
    }

    public String getCardFileName() {

        // This returns the name of the image that is rendered for this object
        return "projectile.png";
    }

    public boolean OnPlay(PlayField theField) {
        // Moves the player one space to the Up.
        // Assumed a movePlayer() + getPlayer() method
        // both in the PlayField class but can be changed
        theField.spawnObject(ObjectType.PROJECTILE, ResourceManager.GetRM()[0].GetPlayer().getXCoordinates(), ResourceManager.GetRM()[0].GetPlayer().getYCoordinates()-1);

        return true;
    }
}
