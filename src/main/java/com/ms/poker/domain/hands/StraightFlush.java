package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Suit;
import com.ms.poker.domain.Value;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StraightFlush extends Hand {
    private List<Card> listClubs;
    private List<Card> listDiamonds;
    private List<Card> listHearts;
    private List<Card> listSpades;

    /**
     * Constructor for the StraightFlush class.
     */
    public StraightFlush() {
        super("Straight Flush");
        listClubs = new ArrayList<>();
        listDiamonds = new ArrayList<>();
        listHearts = new ArrayList<>();
        listSpades = new ArrayList<>();
    }

    /**
     * Checks if the given list of 7 cards represents a Straight Flush from 5 cards.
     *
     * @param cards the list of 7 cards to check
     * @return true if the hand is a Straight Flush, false otherwise
     */
    @Override
    public List<Card> getHighestHands(List<Card> cards) {
        // A Straight Flush consists of 5 consecutive cards of the same suit.
        // This method should implement the logic to check if a hand is a Straight Flush.

        if (cards == null || cards.size() < 7) {
            System.out.println("We need at least 7 cards to check for a Straight Flush.");
            return null; // Not enough cards to form a Straight Flush
        }

        // sort the cards by suit and then by value in descending order
        // Compare by suit first
        // Then compare by value
        cards.sort(Comparator.comparing(Card::getSuit)
                .thenComparing(Card::getValue, Comparator.reverseOrder()));

        for (Card card : cards) {
            switch (card.getSuit()) {
                case CLUBS:
                    listClubs.add(card);
                    break;
                case DIAMONDS:
                    listDiamonds.add(card);
                    break;
                case HEARTS:
                    listHearts.add(card);
                    break;
                case SPADES:
                    listSpades.add(card);
                    break;
            }
        }

        List<Card> straightFlushCards = null;
        int firstClubIndex = hasStraight(listClubs);
        int firstDiamondIndex = hasStraight(listDiamonds);
        int firstHeartIndex = hasStraight(listHearts);
        int firstSpadeIndex = hasStraight(listSpades);
        if (firstClubIndex != -1 && firstClubIndex != -2) {
            straightFlushCards = new ArrayList<>(listClubs.subList(firstClubIndex, firstClubIndex + 5));
        } else if (firstDiamondIndex != -1 && firstClubIndex != -2) {
            straightFlushCards = new ArrayList<>(listDiamonds.subList(firstDiamondIndex, firstDiamondIndex + 5));
        } else if (firstHeartIndex != -1 && firstClubIndex != -2) {
            straightFlushCards = new ArrayList<>(listHearts.subList(firstHeartIndex, firstHeartIndex + 5));
        } else if (firstSpadeIndex != -1 && firstClubIndex != -2) {
            straightFlushCards = new ArrayList<>(listSpades.subList(firstSpadeIndex, firstSpadeIndex + 5));
        }

        // logic for low Ace Straight Flush
        if (firstClubIndex == -2) {
            straightFlushCards = List.of(
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.ACE, Suit.CLUBS)
            );
        } else if (firstDiamondIndex == -2) {
            straightFlushCards = List.of(
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.TWO, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.DIAMONDS)
            );
        } else if (firstHeartIndex == -2) {
            straightFlushCards = List.of(
                new Card(Value.FIVE, Suit.HEARTS),
                new Card(Value.FOUR, Suit.HEARTS),
                new Card(Value.THREE, Suit.HEARTS),
                new Card(Value.TWO, Suit.HEARTS),
                new Card(Value.ACE, Suit.HEARTS)
            );
        } else if (firstSpadeIndex == -2) {
            straightFlushCards = List.of(
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.ACE, Suit.SPADES)
            );
        }
        flushLists(); // Clear the lists for the next check
        return straightFlushCards;
    }

    private int hasStraight(List<Card> cards) {
        if (cards.size() < 5) {
            return -1; // Not enough cards to form a Straight Flush
        }

        // Check for consecutive values
        for (int i = 0; i <= cards.size() - 5; i++) {
            if (cards.get(i).getValue().getValue() - cards.get(i+4).getValue().getValue() == 4) {
                return i; // Found a Straight
            }
        }
        int size = cards.size();
        // Ace is low
        if (cards.get(0).getValue() == Value.ACE &&
            cards.get(size-1).getValue() == Value.TWO &&
            cards.get(size-2).getValue() == Value.THREE &&
            cards.get(size-3).getValue() == Value.FOUR &&
            cards.get(size-4).getValue() == Value.FIVE) {
            return -2; // Found a Straight with low Ace
        }
        return -1; // No Straight found
    }

    private void flushLists() {
        listClubs.clear();
        listDiamonds.clear();
        listHearts.clear();
        listSpades.clear();
    }
}
