package mule.model.blackjack;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import java.util.HashSet;
import java.util.Set;

import mule.model.blackjack.Card.Rank;

/**
 * A hand of cards
 */

public class Hand {

    private Set<Card> cards;
    private int value;

	private int aces = 0;

	public Hand() {
        cards = new HashSet<Card>();
	}

	public Card takeCard(Card card) {
		cards.add(card);

        if (card.rank.equals(Rank.ACE) && value + 11 > 21) {
            value = value + card.value - 10;    //then count ace as '1' not '11'
        }
        else {
            value = value + card.value;
        }

        return card;
	}

	public void reset() {
		cards.clear();
		value = 0;
		aces = 0;
	}

	public int getValue() {
		return value;
	}

    public Set<Card> getCards() {
        return cards;
    }

    public int getNumberOfCards() {
        return cards.size();
    }

}
