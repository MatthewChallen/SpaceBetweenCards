package Cards;
import Core.Card;
import Core.PlayField;
import Core.ResourceManager;
import TypeListings.Direction;

public class MoveCard extends Card {

    private int distance;
    private Direction direction;

    public MoveCard(String name, String description, Direction direction) {
        super(name, description);
        distance = 1;
        this.direction = direction;
    }

    public MoveCard(String name, String description, Direction direction, int dist) {
        super(name, description);
        distance = dist;

        this.direction = direction;
    }

    public String getCardFileName() {

        // This returns the name of the image that is rendered for this object
        String hold;
        switch(direction) {
        case UP:
            hold = "cardShipUP.png";
            break;

        case DOWN:
            hold = "cardShipDown.png";
            break;

        case LEFT:
            hold = "cardShipLeft.png";
            break;

        case RIGHT:
            hold = "cardShipRight.png";
            break;
        default:
            hold = "";
        }
        return hold;
    }
    
    public int getDistance() {
        return distance;
    }
    
    public Direction GetDirection() {
        return direction;
    }

    public boolean OnPlay(PlayField theField) {

        // Moves the player direction one space
        // Assumed a movePlayer() + getPlayer() method
        // both in the PlayField class but can be changed
        theField.moveObject(direction, ResourceManager.GetRM().getPlayer(), distance);

        return true;
    }
}