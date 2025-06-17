package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Value;
import com.ms.poker.domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreeOfAKindTest {

    @Test
    void testValidThreeOfAKind() {
        ThreeOfAKind threeOfAKind = new ThreeOfAKind();
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.HEARTS),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.HEARTS)
        ));
        List<Card> result = threeOfAKind.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.HEARTS),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.SIX, Suit.HEARTS)
        ), result);
    }

    @Test
    void testNoThreeOfAKind() {
        ThreeOfAKind threeOfAKind = new ThreeOfAKind();
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.HEARTS)
        ));
        List<Card> result = threeOfAKind.getHighestHands(cards);
        assertNull(result);
    }

    @Test
    void testNoThreeOfAKind2() {
        ThreeOfAKind threeOfAKind = new ThreeOfAKind();
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.TWO, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.KING, Suit.SPADES),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.HEARTS)
        ));
        List<Card> result = threeOfAKind.getHighestHands(cards);
        assertNull(result);
    }

    @Test
    void testLessThanSevenCards() {
        ThreeOfAKind threeOfAKind = new ThreeOfAKind();
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.HEARTS),
                new Card(Value.KING, Suit.SPADES)
        ));
        List<Card> result = threeOfAKind.getHighestHands(cards);
        assertNull(result);
    }

    @Test
    void testNullInput() {
        ThreeOfAKind threeOfAKind = new ThreeOfAKind();
        List<Card> result = threeOfAKind.getHighestHands(null);
        assertNull(result);
    }
}