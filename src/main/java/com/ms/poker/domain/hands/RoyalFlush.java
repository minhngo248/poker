package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Value;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RoyalFlush extends Hand {
    private List<Card> listClubs;
    private List<Card> listDiamonds;
    private List<Card> listHearts;
    private List<Card> listSpades;
    
    /**
     * Constructor for the RoyalFlush class.
     */
    public RoyalFlush() {
        super("Royal Flush");
        listClubs = new ArrayList<>();
        listDiamonds = new ArrayList<>();
        listHearts = new ArrayList<>();
        listSpades = new ArrayList<>();
    }

    /**
     * Checks if the given list of 7 cards represents a Royal Flush from 5 cards.
     *
     * @param cards the list of 7 cards to check
     * @return true if the hand is a Royal Flush, false otherwise
     */
    @Override
    public List<Card> getHighestHands(List<Card> cards) {
        // A Royal Flush consists of the Ace, King, Queen, Jack, and 10 of the same suit.
        // This method should implement the logic to check if a hand is a Royal Flush.

        if (cards == null || cards.size() < 7) {
            System.out.println("We need at least 7 cards to check for a Royal Flush.");
            return null;
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
        List<Card> royalFlushCards = null;
        if (hasRoyalFlush(listClubs)) {
            royalFlushCards = new ArrayList<>(listClubs.subList(0, 5));
        } else if (hasRoyalFlush(listDiamonds)) {
            royalFlushCards = new ArrayList<>(listDiamonds.subList(0, 5));
        } else if (hasRoyalFlush(listHearts)) {
            royalFlushCards = new ArrayList<>(listHearts.subList(0, 5));
        } else if (hasRoyalFlush(listSpades)) {
            royalFlushCards = new ArrayList<>(listSpades.subList(0, 5));
        }
        flushLists(); // Clear the lists for the next validation
        return royalFlushCards;
    }

    /**
     * Checks if the given list of cards contains a Royal Flush.
     *
     * @param cards the list of cards to check
     * @return true if the list contains a Royal Flush, false otherwise
     */
    private boolean hasRoyalFlush(List<Card> cards) {
        if (cards.size() < 5) {
            return false; // Not enough cards to form a Royal Flush
        }

        // Check for the presence of Ace, King, Queen, Jack, and 10
        return cards.stream().anyMatch(card -> card.getValue() == Value.ACE) &&
                cards.stream().anyMatch(card -> card.getValue() == Value.KING) &&
                cards.stream().anyMatch(card -> card.getValue() == Value.QUEEN) &&
                cards.stream().anyMatch(card -> card.getValue() == Value.JACK) &&
                cards.stream().anyMatch(card -> card.getValue() == Value.TEN);
    }

    /**
     * Flushes the lists of cards for the next validation.
     */
    private void flushLists() {
        listClubs.clear();
        listDiamonds.clear();
        listHearts.clear();
        listSpades.clear();
    }
}
