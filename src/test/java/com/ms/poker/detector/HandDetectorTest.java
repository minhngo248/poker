package com.ms.poker.detector;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Suit;
import com.ms.poker.domain.Value;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandDetectorTest {

    @Test
    void testRoyalFlushHearts() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.JACK, Suit.HEARTS),
                new Card(Value.QUEEN, Suit.HEARTS),
                new Card(Value.KING, Suit.HEARTS),
                new Card(Value.ACE, Suit.HEARTS),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS)
        ));
        List<Card> result = HandDetector.getHandValues(cards);
        assertEquals(List.of(
                new Card(Value.ACE, Suit.HEARTS),
                new Card(Value.KING, Suit.HEARTS),
                new Card(Value.QUEEN, Suit.HEARTS),
                new Card(Value.JACK, Suit.HEARTS),
                new Card(Value.TEN, Suit.HEARTS)
        ), result, "Should identify Royal Flush in Hearts");
    }

    @Test
    void testRoyalFlushSpades() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TEN, Suit.SPADES),
                new Card(Value.JACK, Suit.SPADES),
                new Card(Value.QUEEN, Suit.SPADES),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.FIVE, Suit.DIAMONDS)
        ));
        List<Card> result = HandDetector.getHandValues(cards);
        assertEquals(List.of(
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.QUEEN, Suit.SPADES),
                new Card(Value.JACK, Suit.SPADES),
                new Card(Value.TEN, Suit.SPADES)
        ), result, "Should identify Royal Flush in Spades");
    }

    @Test
    void testRoyalFlushMissingAce() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.JACK, Suit.HEARTS),
                new Card(Value.QUEEN, Suit.HEARTS),
                new Card(Value.KING, Suit.HEARTS),
                new Card(Value.EIGHT, Suit.HEARTS),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS)
        ));
        List<Card> result = HandDetector.getHandValues(cards);
        assertNull(result, "Should return null when Royal Flush is not complete (missing Ace)");
    }

    @Test
    void testStraightFlushDiamonds() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TEN, Suit.DIAMONDS),
                new Card(Value.NINE, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.TWO, Suit.HEARTS)
        ));
        List<Card> result = HandDetector.getHandValues(cards);
        assertEquals(List.of(
                new Card(Value.TEN, Suit.DIAMONDS),
                new Card(Value.NINE, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.DIAMONDS)
        ), result, "Should identify Straight Flush in Diamonds");
    }

    @Test
    void testStraightFlushClubs() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.SEVEN, Suit.CLUBS),
                new Card(Value.EIGHT, Suit.CLUBS),
                new Card(Value.NINE, Suit.CLUBS),
                new Card(Value.TWO, Suit.HEARTS),
                new Card(Value.THREE, Suit.DIAMONDS)
        ));
        List<Card> result = HandDetector.getHandValues(cards);
        assertEquals(List.of(
                new Card(Value.NINE, Suit.CLUBS),
                new Card(Value.EIGHT, Suit.CLUBS),
                new Card(Value.SEVEN, Suit.CLUBS),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.FIVE, Suit.CLUBS)
        ), result, "Should identify Straight Flush in Clubs");
    }

    @Test
    void testFourOfAKindKings() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.KING, Suit.HEARTS),
                new Card(Value.KING, Suit.CLUBS),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEARTS)
        ));
        List<Card> result = HandDetector.getHandValues(cards);
        assertEquals(List.of(
                new Card(Value.KING, Suit.CLUBS),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.KING, Suit.HEARTS),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.FOUR, Suit.HEARTS)
        ), result, "Should identify Four of a Kind for Kings");
    }

    @Test
    void testFourOfAKindFives() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.HEARTS)
        ));
        List<Card> result = HandDetector.getHandValues(cards);
        assertEquals(List.of(
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.ACE, Suit.HEARTS)
        ), result, "Should identify Four of a Kind for Fives");
    }

    @Test
    void testNullHandNotEnoughCards() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.SEVEN, Suit.DIAMONDS)
        ));
        List<Card> result = HandDetector.getHandValues(cards);
        assertNull(result, "Should return null when not enough cards are provided");
    }

    @Test
    void testNullHandNoValidHand() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.SPADES),
                new Card(Value.NINE, Suit.CLUBS),
                new Card(Value.TEN, Suit.DIAMONDS),
                new Card(Value.JACK, Suit.HEARTS)
        ));
        List<Card> result = HandDetector.getHandValues(cards);
        assertNull(result, "Should return null when no valid hand is found");
    }
}