package mule.model.blackjack;

import mule.model.blackjack.Card.Rank;
import mule.model.blackjack.Card.Suit;

public class CardToFile {

    private static Map<Card, String> map;

    static {
        map = new HashMap<>();
        map.add(new Card(Suit.CLUBS, Rank.ACE), "mule/view/cards/A_C.png");
        map.add(new Card(Suit.CLUBS, Rank.TWO), "mule/view/cards/2_C.png");
        map.add(new Card(Suit.CLUBS, Rank.THREE), "mule/view/cards/3_C.png");
        map.add(new Card(Suit.CLUBS, Rank.FOUR), "mule/view/cards/4_C.png");
        map.add(new Card(Suit.CLUBS, Rank.FIVE), "mule/view/cards/5_C.png");
        map.add(new Card(Suit.CLUBS, Rank.SIX), "mule/view/cards/6_C.png");
        map.add(new Card(Suit.CLUBS, Rank.SEVEN), "mule/view/cards/7_C.png");
        map.add(new Card(Suit.CLUBS, Rank.EIGHT), "mule/view/cards/8_C.png");
        map.add(new Card(Suit.CLUBS, Rank.NINE), "mule/view/cards/9_C.png");
        map.add(new Card(Suit.CLUBS, Rank.TEN), "mule/view/cards/10_C.png");
        map.add(new Card(Suit.CLUBS, Rank.JACK), "mule/view/cards/J_C.png");
        map.add(new Card(Suit.CLUBS, Rank.QUEEN), "mule/view/cards/Q_C.png");
        map.add(new Card(Suit.CLUBS, Rank.KING), "mule/view/cards/K_C.png");
        map.add(new Card(Suit.HEARTS, Rank.ACE), "mule/view/cards/A_H.png");
        map.add(new Card(Suit.HEARTS, Rank.TWO), "mule/view/cards/2_H.png");
        map.add(new Card(Suit.HEARTS, Rank.THREE), "mule/view/cards/3_H.png");
        map.add(new Card(Suit.HEARTS, Rank.FOUR), "mule/view/cards/4_H.png");
        map.add(new Card(Suit.HEARTS, Rank.FIVE), "mule/view/cards/5_H.png");
        map.add(new Card(Suit.HEARTS, Rank.SIX), "mule/view/cards/6_H.png");
        map.add(new Card(Suit.HEARTS, Rank.SEVEN), "mule/view/cards/7_H.png");
        map.add(new Card(Suit.HEARTS, Rank.EIGHT), "mule/view/cards/8_H.png");
        map.add(new Card(Suit.HEARTS, Rank.NINE), "mule/view/cards/9_H.png");
        map.add(new Card(Suit.HEARTS, Rank.TEN), "mule/view/cards/10_H.png");
        map.add(new Card(Suit.HEARTS, Rank.JACK), "mule/view/cards/J_H.png");
        map.add(new Card(Suit.HEARTS, Rank.QUEEN), "mule/view/cards/Q_H.png");
        map.add(new Card(Suit.HEARTS, Rank.KING), "mule/view/cards/K_H.png");
        map.add(new Card(Suit.SPADES, Rank.ACE), "mule/view/cards/A_S.png");
        map.add(new Card(Suit.SPADES, Rank.TWO), "mule/view/cards/2_S.png");
        map.add(new Card(Suit.SPADES, Rank.THREE), "mule/view/cards/3_S.png");
        map.add(new Card(Suit.SPADES, Rank.FOUR), "mule/view/cards/4_S.png");
        map.add(new Card(Suit.SPADES, Rank.FIVE), "mule/view/cards/5_S.png");
        map.add(new Card(Suit.SPADES, Rank.SIX), "mule/view/cards/6_S.png");
        map.add(new Card(Suit.SPADES, Rank.SEVEN), "mule/view/cards/7_S.png");
        map.add(new Card(Suit.SPADES, Rank.EIGHT), "mule/view/cards/8_S.png");
        map.add(new Card(Suit.SPADES, Rank.NINE), "mule/view/cards/9_S.png");
        map.add(new Card(Suit.SPADES, Rank.TEN), "mule/view/cards/10_S.png");
        map.add(new Card(Suit.SPADES, Rank.JACK), "mule/view/cards/J_S.png");
        map.add(new Card(Suit.SPADES, Rank.QUEEN), "mule/view/cards/Q_S.png");
        map.add(new Card(Suit.SPADES, Rank.KING), "mule/view/cards/K_S.png");
        map.add(new Card(Suit.DIAMONDS, Rank.ACE), "mule/view/cards/A_D.png");
        map.add(new Card(Suit.DIAMONDS, Rank.TWO), "mule/view/cards/2_D.png");
        map.add(new Card(Suit.DIAMONDS, Rank.THREE), "mule/view/cards/3_D.png");
        map.add(new Card(Suit.DIAMONDS, Rank.FOUR), "mule/view/cards/4_D.png");
        map.add(new Card(Suit.DIAMONDS, Rank.FIVE), "mule/view/cards/5_D.png");
        map.add(new Card(Suit.DIAMONDS, Rank.SIX), "mule/view/cards/6_D.png");
        map.add(new Card(Suit.DIAMONDS, Rank.SEVEN), "mule/view/cards/7_D.png");
        map.add(new Card(Suit.DIAMONDS, Rank.EIGHT), "mule/view/cards/8_D.png");
        map.add(new Card(Suit.DIAMONDS, Rank.NINE), "mule/view/cards/9_D.png");
        map.add(new Card(Suit.DIAMONDS, Rank.TEN), "mule/view/cards/10_D.png");
        map.add(new Card(Suit.DIAMONDS, Rank.JACK), "mule/view/cards/J_D.png");
        map.add(new Card(Suit.DIAMONDS, Rank.QUEEN), "mule/view/cards/Q_D.png");
        map.add(new Card(Suit.DIAMONDS, Rank.KING), "mule/view/cards/K_D.png");
    }

    public static String getFileName(Card c) {
        return map.get(c);
    }
}
