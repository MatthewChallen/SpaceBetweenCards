package Cards;

import Core.Card;
import Core.PlayField;
import Core.ResourceManager;
import TypeListings.ObjectType;

public class ShootCard extends Card {
    
    int speed;
    public ShootCard(String name, String description) {
        super(name, description);
        speed = 1;
    }

    public ShootCard(String name, String description, int speed) {
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
        theField.spawnObject(ObjectType.PLAYERPROJECTILE, ResourceManager.GetRM().getPlayer().getXCoordinates(), ResourceManager.GetRM().getPlayer().getYCoordinates()-1);
        ResourceManager.GetRM().getMM().addMusic(ObjectType.PROJECTILE);
        return true;
    }
}
