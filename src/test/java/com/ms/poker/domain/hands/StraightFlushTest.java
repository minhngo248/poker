package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Suit;
import com.ms.poker.domain.Value;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StraightFlushTest {
    @Test
    void testStraightFlushLowAce() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.EIGHT, Suit.HEARTS),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.SEVEN, Suit.DIAMONDS)
        ));
        StraightFlush straightFlush = new StraightFlush();
        assertEquals(List.of(
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.ACE, Suit.CLUBS)
        ), straightFlush.getHighestHands(cards), "Should identify Straight Flush with low Ace");
    }

    @Test
    void testStraightFlushHearts() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.SEVEN, Suit.HEARTS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.SIX, Suit.HEARTS),
                new Card(Value.FOUR, Suit.HEARTS),
                new Card(Value.THREE, Suit.HEARTS),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.ACE, Suit.DIAMONDS)
        ));
        StraightFlush straightFlush = new StraightFlush();
        assertEquals(List.of(
                new Card(Value.SEVEN, Suit.HEARTS),
                new Card(Value.SIX, Suit.HEARTS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.HEARTS),
                new Card(Value.THREE, Suit.HEARTS)
        ), straightFlush.getHighestHands(cards), "Should identify Straight Flush in Hearts");
    }

    @Test
    void testStraightFlush6Spades() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.NINE, Suit.SPADES),
                new Card(Value.SEVEN, Suit.SPADES),
                new Card(Value.EIGHT, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.THREE, Suit.DIAMONDS)
        ));
        StraightFlush straightFlush = new StraightFlush();
        assertEquals(List.of(
                new Card(Value.NINE, Suit.SPADES),
                new Card(Value.EIGHT, Suit.SPADES),
                new Card(Value.SEVEN, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.FIVE, Suit.SPADES)
        ), straightFlush.getHighestHands(cards), "Should identify Straight Flush in Spades");
    }

    @Test
    void testRoyalFlush7Hearts() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.ACE, Suit.HEARTS),
                new Card(Value.SIX, Suit.HEARTS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.HEARTS),
                new Card(Value.THREE, Suit.HEARTS),
                new Card(Value.TWO, Suit.HEARTS)
        ));
        StraightFlush straightFlush = new StraightFlush();
        assertEquals(List.of(
                new Card(Value.SIX, Suit.HEARTS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.HEARTS),
                new Card(Value.THREE, Suit.HEARTS),
                new Card(Value.TWO, Suit.HEARTS)
        ), straightFlush.getHighestHands(cards), "Should identify Straight Flush in Hearts");
    }

    @Test
    void testNoStraightFlush() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.NINE, Suit.HEARTS),
                new Card(Value.EIGHT, Suit.HEARTS),
                new Card(Value.FOUR, Suit.HEARTS),
                new Card(Value.SEVEN, Suit.HEARTS),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.HEARTS)
        ));
        StraightFlush straightFlush = new StraightFlush();
        assertNull(straightFlush.getHighestHands(cards), "Should return null for no Straight Flush");
    }
}