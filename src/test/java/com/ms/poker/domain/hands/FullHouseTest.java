package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Suit;
import com.ms.poker.domain.Value;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FullHouseTest {

    @Test
    void testFullHouseValidCase1() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.THREE, Suit.HEARTS),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEARTS)
        ));
        FullHouse fullHouse = new FullHouse();
        List<Card> result = fullHouse.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEARTS)
        ), result, "Should identify Full House for Threes and Twos");
    }

    @Test
    void testFullHouseValidCase2() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.KING, Suit.HEARTS),
                new Card(Value.KING, Suit.CLUBS),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.QUEEN, Suit.SPADES),
                new Card(Value.QUEEN, Suit.CLUBS),
                new Card(Value.QUEEN, Suit.DIAMONDS),
                new Card(Value.TEN, Suit.HEARTS)
        ));
        FullHouse fullHouse = new FullHouse();
        List<Card> result = fullHouse.getHighestHands(cards);
        assertEquals(List.of(
                new Card(Value.KING, Suit.CLUBS),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.KING, Suit.HEARTS),
                new Card(Value.QUEEN, Suit.CLUBS),
                new Card(Value.QUEEN, Suit.DIAMONDS)
        ), result, "Should identify Full House for Kings and Queens");
    }

    @Test
    void testNoFullHouseCase1() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.THREE, Suit.HEARTS),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.TWO, Suit.DIAMONDS),
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.HEARTS)
        ));
        FullHouse fullHouse = new FullHouse();
        List<Card> result = fullHouse.getHighestHands(cards);
        assertNull(result, "Should not identify Full House when missing three cards of the same rank");
    }

    @Test
    void testNoFullHouseCase2() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Value.THREE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.SEVEN, Suit.CLUBS),
                new Card(Value.EIGHT, Suit.DIAMONDS),
                new Card(Value.NINE, Suit.HEARTS)
        ));
        FullHouse fullHouse = new FullHouse();
        List<Card> result = fullHouse.getHighestHands(cards);
        assertNull(result, "Should not identify Full House when no valid combination is present");
    }
}