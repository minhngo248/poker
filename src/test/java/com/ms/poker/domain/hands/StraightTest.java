package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Suit;
import com.ms.poker.domain.Value;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StraightTest {

    @Test
    void testStraightValidCase1() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.KING, Suit.HEARTS)
        ));
        Straight straight = new Straight();
        List<Card> result = straight.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.TWO, Suit.CLUBS)
        ), result, "Should identify Straight from Six to Two");
    }

    @Test
    void testStraightValidCase2() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.CLUBS)
        ));
        Straight straight = new Straight();
        List<Card> result = straight.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.TWO, Suit.CLUBS)
        ), result, "Should identify Straight from Six to Two");
    }

    @Test
    void testStraightValidCase3() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.NINE, Suit.CLUBS),
                new Card(Value.NINE, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.SPADES),
                new Card(Value.SEVEN, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS)
        ));
        Straight straight = new Straight();
        List<Card> result = straight.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.NINE, Suit.CLUBS),
                new Card(Value.EIGHT, Suit.SPADES),
                new Card(Value.SEVEN, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS)
        ), result, "Should identify Straight from Ten to Six");
    }

    @Test
    void testStraightLowAce() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.HEARTS)
        ));
        Straight straight = new Straight();
        List<Card> result = straight.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS)
        ), result, "Should identify Straight from Ace to Five");
    }

    @Test
    void testStraightLowAce2() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.TWO, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.HEARTS)
        ));
        Straight straight = new Straight();
        List<Card> result = straight.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.TWO, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.CLUBS)
        ), result, "Should identify Straight from Ace to Five");
    }

    @Test
    void testNoStraightCase1() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.QUEEN, Suit.HEARTS)
        ));
        Straight straight = new Straight();
        List<Card> result = straight.getHighestHands(cards);
        assertNull(result, "Should not identify Straight when missing consecutive cards");
    }

    @Test
    void testNoStraightCase2() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.SEVEN, Suit.CLUBS),
                new Card(Value.NINE, Suit.DIAMONDS),
                new Card(Value.JACK, Suit.SPADES),
                new Card(Value.KING, Suit.CLUBS),
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.HEARTS)
        ));
        Straight straight = new Straight();
        List<Card> result = straight.getHighestHands(cards);
        assertNull(result, "Should not identify Straight when no valid combination is present");
    }
}