package come.playdis.deck;

import java.util.ArrayList;
import java.util.Collections;

import com.playdis.cards.Card;
import com.playdis.cards.MoveCardDown;
import com.playdis.cards.MoveCardLeft;
import com.playdis.cards.MoveCardRight;
import com.playdis.cards.MoveCardUp;
import com.playdis.cards.ShootCardDown;
import com.playdis.cards.ShootCardLeft;
import com.playdis.cards.ShootCardRight;
import com.playdis.cards.ShootCardUp;

public class Deck {
	
	private ArrayList<Card> deckCards;
	private String id;
	private int maxCards;
	
	public Deck(String id, int maxCards) {
		this.id = id;
		this.maxCards = maxCards;
		// Fills + Shuffles when created...
		fillDeck();
		shuffleDeck();
	}
	
	public String getID() {
		return this.id;
	}
	public void fillDeck() {
		// Hard-coded for testing purpose only...
		Card MCD = new MoveCardDown("MCD", "Move 1 space down...");
		Card MCL= new MoveCardLeft("MCL", "Move 1 space left...");
		Card MCR = new MoveCardRight("MCR", "Move 1 space right...");
		Card MCU = new MoveCardUp("MCU", "Move 1 space up...");
		Card SCD = new ShootCardDown("SCD", "Shoot 1 space down...");
		Card SCL = new ShootCardLeft("SCL", "Shoot 1 space down...");
		Card SCR = new ShootCardRight("SCR", "Shoot 1 space down...");
		Card SCU = new ShootCardUp("SCU", "Shoot 1 space down...");
	}
	public void shuffleDeck() {
		Collections.shuffle(deckCards);
	}
	public void moveCard(int someCard) {
		deckCards.remove(someCard);
	}
	public void addCard(String name, String description) {
		Card tempCard = new MoveCardDown(name, description);
		deckCards.add(tempCard);
	}
}
