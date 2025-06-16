package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;

import java.util.*;

public class FullHouse extends Hand {
    private HashMap<Integer, Integer> cardOccurrences;
    private PriorityQueue<Map.Entry<Integer, Integer>> sortedOccurrences;

    public FullHouse() {
        super("Full House");
        cardOccurrences = new HashMap<>();
        // Initialize a priority queue to sort occurrences by frequency and then by value in descending order
        sortedOccurrences = new PriorityQueue<>(
                (a, b) -> a.getValue().equals(b.getValue()) ? b.getKey().compareTo(a.getKey()) : b.getValue().compareTo(a.getValue())
        );
    }

    /**
     * Returns the highest Full House hand from the given list of cards.
     * A Full House consists of three cards of one rank and two cards of another rank.
     *
     * @param cards the list of 7 cards to check
     * @return a list of 5 cards representing the highest Full House hand, or null if no valid hand is found
     */
    @Override
    public List<Card> getHighestHands(List<Card> cards) {
        // A Full House consists of three cards of one rank and two cards of another rank.
        if (cards == null || cards.size() < 7) {
            System.out.println("We need at least 7 cards to check for a Full House.");
            return null; // Not enough cards to form a Full House
        }

        // sort the cards by value in descending order
        cards.sort(Comparator.comparing(Card::getValue, Comparator.reverseOrder())
                .thenComparing(Card::getSuit));

        // Count occurrences of each card value
        for (Card card : cards) {
            int value = card.getValue().getValue();
            cardOccurrences.put(value, cardOccurrences.getOrDefault(value, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : cardOccurrences.entrySet()) {
            sortedOccurrences.offer(entry);
        }

        // Get the first entry (highest frequency) for three of a kind
        Map.Entry<Integer, Integer> threeOfAKindEntry = sortedOccurrences.poll();
        assert threeOfAKindEntry != null;
        if (threeOfAKindEntry.getValue() < 3) {
            resetOccurrences();
            return null; // No three of a kind found
        }

        // Add three of a kind cards to the highest hand
        List<Card> highestHand = new ArrayList<>(cards.stream()
                .filter(card -> card.getValue().getValue() == threeOfAKindEntry.getKey())
                .limit(3)
                .toList());

        // Get the second entry (next highest frequency) for the pair
        Map.Entry<Integer, Integer> pairEntry = sortedOccurrences.poll();
        if (pairEntry == null || pairEntry.getValue() < 2) {
            resetOccurrences();
            return null; // No pair found
        }

        // Add pair cards to the highest hand
        highestHand.addAll(cards.stream()
                .filter(card -> card.getValue().getValue() == pairEntry.getKey())
                .limit(2)
                .toList());
        // Reset occurrences for the next check
        resetOccurrences();
        return highestHand.size() == 5 ? highestHand : null; // Return null if not a valid Full House
    }

    private void resetOccurrences() {
        cardOccurrences.clear();
        sortedOccurrences.clear();
    }
}
