package Blackjack;

import Blackjack.Card.Rank;
import Blackjack.Card.Suit;
/**
 * 52 cards in a deck
 *
 * @author Ryan Chiang
 * @version  1.0
 */

public class Deck {

	private Card[] cards = new Card[52];

	public Deck() {
		refill();
	}

	public final void refill() {
		int i = 0;
		for (Suit suit : Suit.values()) {
			for (Rank rank: Rank.values()) {
				cards[i++] = new Card(suit,rank);
			}
		}
	}

	public Card drawCard() {
		Card card = null;
		while (card == null) {
			int index = (int)(Math.random() * cards.length);
			card = cards[index];
			cards[index] = null;
		}

		return card;
	}
}