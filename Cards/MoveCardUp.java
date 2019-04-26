package Cards;

import Core.Card;
import Core.PlayField;
import Core.ResourceManager;

public class MoveCardUp extends Card {
    
    private int distance;

    public MoveCardUp(String name, String description) {
        super(name, description);
        distance = 1;
    }

    public MoveCardUp(String name, String description, int dist) {
        super(name, description);
        distance = dist;
    }

    public String getCardFileName() {

        // This returns the name of the image that is rendered for this object
        return "cardShipUp.png";
    }

    public boolean OnPlay(PlayField theField) {
        // Moves the player one space to the Up.
        // Assumed a movePlayer() + getPlayer() method
        // both in the PlayField class but can be changed
        theField.moveObject("up", ResourceManager.GetRM()[0].GetPlayer(), distance);

        return true;
    }
}
