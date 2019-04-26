package Core;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private Deck drawDeck; // deck the hand is drawing from and discarding to

    private List<Card> hand = new ArrayList<Card>();

    public List<Card> GetHandList() {
        return hand;
    }

    public int GetCardCount() {
        return hand.size();
    }
    // referance to the deck the hand is interacting with
    public void SetDrawDeck(Deck deck) {
        drawDeck = deck;
    }

    // Plays the Xth card
    public void PlayCard(PlayField theField, int X) {
        hand.get(X-1).OnPlay(theField);
        Discard(X-1);
    }

    // Discards the Xth card in the hand
    public void Discard(int X) {
        hand.get(X).OnDiscard();
        drawDeck.Discard(hand.get(X));
        hand.remove(X);
    }

    // Discards entire hand
    public void DiscardAll() {
        while (hand.size() != 0) {
            Discard(0);
        }
    }

    public void DrawCard() { // draws card from deck, calls Card.OnDraw()
        Card hold = drawDeck.DrawCard();
        if (hold != null) {
            hand.add(hold);
            hold.OnDraw();
        }
    }

    public void DrawCard(int X) { // Draws X cards
        for (int i = 0; i < X; ++i) {
            DrawCard();
        }
    }

    public Hand() {
        drawDeck = null;
    }

}
