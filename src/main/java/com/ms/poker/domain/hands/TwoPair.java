package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;

import java.util.*;

public class TwoPair extends Hand {
    private HashMap<Integer, Integer> cardOccurrences;
    private PriorityQueue<Map.Entry<Integer, Integer>> sortedOccurrences;

    /**
     * Constructor for the TwoPair class.
     */
    public TwoPair() {
        super("Two Pair");
        cardOccurrences = new HashMap<>();
        // Initialize a priority queue to sort occurrences by frequency and then by value in descending order
        sortedOccurrences = new PriorityQueue<>(
                (a, b) -> a.getValue().equals(b.getValue()) ? b.getKey().compareTo(a.getKey()) : b.getValue().compareTo(a.getValue())
        );
    }

    @Override
    public List<Card> getHighestHands(List<Card> cards) {
        if (cards == null || cards.size() < 7) {
            System.out.println("We need at least 7 cards to check for a Three of a Kind.");
            return null; // Not enough cards to form a Three of a Kind
        }

        // sort the cards by value in descending order
        // and then by suit
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

        // Get the first two entries (highest frequency) for two pairs
        List<Card> highestHand = new ArrayList<>();
        Map.Entry<Integer, Integer> firstPairEntry = sortedOccurrences.poll();
        Map.Entry<Integer, Integer> secondPairEntry = sortedOccurrences.poll();
        if (firstPairEntry == null || firstPairEntry.getValue() < 2 ||
            secondPairEntry == null || secondPairEntry.getValue() < 2) {
            resetOccurrences();
            return null; // No two pairs found
        }

        // Add two pair cards to the highest hand
        highestHand.addAll(cards.stream()
                .filter(card -> card.getValue().getValue() == firstPairEntry.getKey())
                .limit(2)
                .toList());
        highestHand.addAll(cards.stream()
                .filter(card -> card.getValue().getValue() == secondPairEntry.getKey())
                .limit(2)
                .toList());

        // Add the highest remaining card to complete the hand
        // using `cards`
        highestHand.add(cards.stream()
                .filter(card -> !highestHand.contains(card))
                .findFirst()
                .orElse(null));

        // Reset occurrences for the next hand evaluation
        resetOccurrences();
        return highestHand.size() == 5 ? highestHand : null; // Ensure we return exactly 5 cards
    }

    private void resetOccurrences() {
        cardOccurrences.clear();
        sortedOccurrences.clear();
    }
}
