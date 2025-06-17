package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Value;
import com.ms.poker.domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    @Test
    void testValidPair() {
        Pair pair = new Pair();
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.HEARTS)
        ));
        List<Card> result = pair.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.SEVEN, Suit.HEARTS),
                new Card(Value.SIX, Suit.DIAMONDS)
        ), result);
    }

    @Test
    void testNoPair() {
        Pair pair = new Pair();
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.HEARTS)
        ));
        List<Card> result = pair.getHighestHands(cards);
        assertNull(result);
    }

    @Test
    void testLessThanSevenCards() {
        Pair pair = new Pair();
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.KING, Suit.SPADES)
        ));
        List<Card> result = pair.getHighestHands(cards);
        assertNull(result);
    }

    @Test
    void testNullInput() {
        Pair pair = new Pair();
        List<Card> result = pair.getHighestHands(null);
        assertNull(result);
    }
}