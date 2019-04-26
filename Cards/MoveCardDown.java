package Cards;
import Core.Card;
import Core.PlayField;
import Core.ResourceManager;

public class MoveCardDown extends Card {

    private int distance;

    public MoveCardDown(String name, String description) {
        super(name, description);
        distance = 1;
    }

    public MoveCardDown(String name, String description, int dist) {
        super(name, description);
        distance = dist;
    }

    public String getCardFileName() {

        // This returns the name of the image that is rendered for this object
        return "cardShipDown.png";
    }

    public boolean OnPlay(PlayField theField) {

        // Moves the player one space to the Down
        // Assumed a movePlayer() + getPlayer() method
        // both in the PlayField class but can be changed
        theField.moveObject("down", ResourceManager.GetRM().GetPlayer(), distance);

        return true;
    }
}
