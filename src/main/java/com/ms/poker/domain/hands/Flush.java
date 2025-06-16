package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Suit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Flush extends Hand {
    private List<Card> listClubs;
    private List<Card> listDiamonds;
    private List<Card> listHearts;
    private List<Card> listSpades;

    /**
     * Constructor for the Flush class.
     */
    public Flush() {
        super("Flush");
        listClubs = new ArrayList<>();
        listDiamonds = new ArrayList<>();
        listHearts = new ArrayList<>();
        listSpades = new ArrayList<>();
    }

    @Override
    public List<Card> getHighestHands(List<Card> cards) {
        // A Flush consists of 5 cards of the same suit, not in sequence.
        if (cards == null || cards.size() < 7) {
            System.out.println("We need at least 7 cards to check for a Flush.");
            return null; // Not enough cards to form a Flush
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

        List<Card> flushCards = null;
        if (listClubs.size() >= 5) {
            flushCards = cards.stream()
                    .filter(card -> card.getSuit() == Suit.CLUBS)
                    .limit(5)
                    .toList();
        } else if (listDiamonds.size() >= 5) {
            flushCards = cards.stream()
                    .filter(card -> card.getSuit() == Suit.DIAMONDS)
                    .limit(5)
                    .toList();
        } else if (listHearts.size() >= 5) {
            flushCards = cards.stream()
                    .filter(card -> card.getSuit() == Suit.HEARTS)
                    .limit(5)
                    .toList();
        } else if (listSpades.size() >= 5) {
            flushCards = cards.stream()
                    .filter(card -> card.getSuit() == Suit.SPADES)
                    .limit(5)
                    .toList();
        }
        flushLists(); // Clear the lists for the next validation
        return flushCards;
    }

    private void flushLists() {
        listClubs.clear();
        listDiamonds.clear();
        listHearts.clear();
        listSpades.clear();
    }
}
