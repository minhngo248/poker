package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Value;
import com.ms.poker.domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TwoPairTest {

    @Test
    void testValidTwoPair() {
        TwoPair twoPair = new TwoPair();
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.KING, Suit.CLUBS),
                new Card(Value.TWO, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.HEARTS)
        ));
        List<Card> result = twoPair.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.KING, Suit.CLUBS)
        ), result);
    }

    @Test
    void testNoTwoPair() {
        TwoPair twoPair = new TwoPair();
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.TWO, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.HEARTS)
        ));
        List<Card> result = twoPair.getHighestHands(cards);
        assertNull(result);
    }

    @Test
    void testLessThanSevenCards() {
        TwoPair twoPair = new TwoPair();
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FIVE, Suit.SPADES)
        ));
        List<Card> result = twoPair.getHighestHands(cards);
        assertNull(result);
    }

    @Test
    void testNullInput() {
        TwoPair twoPair = new TwoPair();
        List<Card> result = twoPair.getHighestHands(null);
        assertNull(result);
    }

    @Test
    void testMultiplePairs() {
        TwoPair twoPair = new TwoPair();
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.KING, Suit.CLUBS),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.HEARTS)
        ));
        List<Card> result = twoPair.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.KING, Suit.CLUBS),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.SIX, Suit.HEARTS)
        ), result);
    }
}