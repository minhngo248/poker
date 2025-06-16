package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Suit;
import com.ms.poker.domain.Value;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlushTest {

    @Test
    void testFlushHearts() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TWO, Suit.HEARTS),
                new Card(Value.FOUR, Suit.HEARTS),
                new Card(Value.SIX, Suit.HEARTS),
                new Card(Value.EIGHT, Suit.HEARTS),
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.FIVE, Suit.DIAMONDS)
        ));
        Flush flush = new Flush();
        List<Card> result = flush.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.EIGHT, Suit.HEARTS),
                new Card(Value.SIX, Suit.HEARTS),
                new Card(Value.FOUR, Suit.HEARTS),
                new Card(Value.TWO, Suit.HEARTS)
        ), result, "Flush should consist of 5 cards in Hearts");
    }

    @Test
    void testFlushSpades() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.EIGHT, Suit.SPADES),
                new Card(Value.TEN, Suit.SPADES),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.FIVE, Suit.DIAMONDS)
        ));
        Flush flush = new Flush();
        List<Card> result = flush.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.TEN, Suit.SPADES),
                new Card(Value.EIGHT, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.TWO, Suit.SPADES)
        ), result, "Flush should consist of 5 cards in Spades");
    }

    @Test
    void testFlushDiamonds() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TWO, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.DIAMONDS),
                new Card(Value.TEN, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.FIVE, Suit.HEARTS)
        ));
        Flush flush = new Flush();
        List<Card> result = flush.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.TEN, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.TWO, Suit.DIAMONDS)
        ), result, "Flush should consist of 5 cards in Diamonds");
    }

    @Test
    void testFlushClubs() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.EIGHT, Suit.CLUBS),
                new Card(Value.TEN, Suit.CLUBS),
                new Card(Value.THREE, Suit.HEARTS),
                new Card(Value.FIVE, Suit.DIAMONDS)
        ));
        Flush flush = new Flush();
        List<Card> result = flush.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.TEN, Suit.CLUBS),
                new Card(Value.EIGHT, Suit.CLUBS),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.TWO, Suit.CLUBS)
        ), result, "Flush should consist of 5 cards in Clubs");
    }

    @Test
    void testNoFlush() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TWO, Suit.HEARTS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.SPADES),
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.FIVE, Suit.DIAMONDS)
        ));
        Flush flush = new Flush();
        List<Card> result = flush.getHighestHands(cards);
        assertNull(result, "Should not identify Flush when no 5 cards of the same suit are present");
    }
}