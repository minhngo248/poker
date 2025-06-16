package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Suit;
import com.ms.poker.domain.Value;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoyalFlushTest {

    @Test
    void testRoyalFlush6Hearts() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.JACK, Suit.HEARTS),
                new Card(Value.KING, Suit.HEARTS),
                new Card(Value.ACE, Suit.HEARTS),
                new Card(Value.TWO, Suit.DIAMONDS),
                new Card(Value.NINE, Suit.HEARTS),
                new Card(Value.QUEEN, Suit.HEARTS)
        ));
        RoyalFlush royalFlush = new RoyalFlush();
        assertEquals(List.of(
                new Card(Value.ACE, Suit.HEARTS),
                new Card(Value.KING, Suit.HEARTS),
                new Card(Value.QUEEN, Suit.HEARTS),
                new Card(Value.JACK, Suit.HEARTS),
                new Card(Value.TEN, Suit.HEARTS)
        ), royalFlush.getHighestHands(cards), "Should identify Royal Flush in Hearts");
    }

    @Test
    void testRoyalFlushSpades() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TEN, Suit.SPADES),
                new Card(Value.JACK, Suit.SPADES),
                new Card(Value.QUEEN, Suit.SPADES),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.FOUR, Suit.HEARTS),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.FIVE, Suit.DIAMONDS)
        ));
        RoyalFlush royalFlush = new RoyalFlush();
        assertEquals(List.of(
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.QUEEN, Suit.SPADES),
                new Card(Value.JACK, Suit.SPADES),
                new Card(Value.TEN, Suit.SPADES)
        ), royalFlush.getHighestHands(cards), "Should identify Royal Flush in Spades");
    }

    @Test
    void testNoRoyalFlush() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.JACK, Suit.HEARTS),
                new Card(Value.QUEEN, Suit.HEARTS),
                new Card(Value.KING, Suit.HEARTS),
                new Card(Value.NINE, Suit.HEARTS),
                new Card(Value.TWO, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.CLUBS)
        ));
        RoyalFlush royalFlush = new RoyalFlush();
        assertNull(royalFlush.getHighestHands(cards), "Should return null for no Royal Flush");
    }
}