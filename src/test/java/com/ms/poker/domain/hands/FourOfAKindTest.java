package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Suit;
import com.ms.poker.domain.Value;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FourOfAKindTest {

    @Test
    void testFourOfAKindValidCase1() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.HEARTS),
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.FIVE, Suit.DIAMONDS)
        ));
        FourOfAKind fourOfAKind = new FourOfAKind();
        assertEquals(List.of(
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.ACE, Suit.HEARTS)
        ), fourOfAKind.getHighestHands(cards), "Should identify Four of a Kind for Fives");
    }

    @Test
    void testFourOfAKindValidCase2() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.KING, Suit.HEARTS),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.KING, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.FOUR, Suit.HEARTS),
                new Card(Value.KING, Suit.DIAMONDS)
        ));
        FourOfAKind fourOfAKind = new FourOfAKind();
        assertEquals(List.of(
                new Card(Value.KING, Suit.CLUBS),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.KING, Suit.HEARTS),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.FOUR, Suit.HEARTS)
        ), fourOfAKind.getHighestHands(cards), "Should identify Four of a Kind for Kings");
    }

    @Test
    void testFourOfAKindValidCase3() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.TEN, Suit.CLUBS),
                new Card(Value.TEN, Suit.DIAMONDS),
                new Card(Value.TEN, Suit.SPADES),
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.HEARTS)
        ));
        FourOfAKind fourOfAKind = new FourOfAKind();
        assertEquals(List.of(
                new Card(Value.TEN, Suit.CLUBS),
                new Card(Value.TEN, Suit.DIAMONDS),
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.TEN, Suit.SPADES),
                new Card(Value.SEVEN, Suit.HEARTS)
        ), fourOfAKind.getHighestHands(cards), "Should identify Four of a Kind for Tens");
    }

    @Test
    void testNoFourOfAKindCase1() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.HEARTS)
        ));
        FourOfAKind fourOfAKind = new FourOfAKind();
        assertNull(fourOfAKind.getHighestHands(cards), "Should not identify Four of a Kind when only three cards of the same rank are present");
    }

    @Test
    void testNoFourOfAKindCase2() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.SPADES),
                new Card(Value.NINE, Suit.CLUBS),
                new Card(Value.TEN, Suit.DIAMONDS),
                new Card(Value.JACK, Suit.HEARTS)
        ));
        FourOfAKind fourOfAKind = new FourOfAKind();
        assertNull(fourOfAKind.getHighestHands(cards), "Should not identify Four of a Kind when no cards of the same rank are present");
    }
}