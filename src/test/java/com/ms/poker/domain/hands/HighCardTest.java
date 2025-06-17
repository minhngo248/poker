package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Value;
import com.ms.poker.domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HighCardTest {

    @Test
    void testValidHighCard() {
        HighCard highCard = new HighCard();
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.HEARTS)
        ));
        List<Card> result = highCard.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.HEARTS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS)
        ), result);
    }

    @Test
    void testLessThanSevenCards() {
        HighCard highCard = new HighCard();
        List<Card> cards = List.of(
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.ACE, Suit.SPADES)
        );
        List<Card> result = highCard.getHighestHands(cards);
        assertNull(result);
    }

    @Test
    void testNullInput() {
        HighCard highCard = new HighCard();
        List<Card> result = highCard.getHighestHands(null);
        assertNull(result);
    }
}