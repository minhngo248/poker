package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;

import java.util.*;

public class ThreeOfAKind extends Hand {
    private HashMap<Integer, Integer> cardOccurrences;
    private PriorityQueue<Map.Entry<Integer, Integer>> sortedOccurrences;

    /**
     * Constructor for the ThreeOfAKind class.
     */
    public ThreeOfAKind() {
        super("Three of a Kind");
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

        // Get the first entry (highest frequency) for three of a kind
        Map.Entry<Integer, Integer> threeOfAKindEntry = sortedOccurrences.poll();
        if (threeOfAKindEntry == null || threeOfAKindEntry.getValue() != 3) {
            resetOccurrences();
            return null; // No three of a kind found
        }

        // Add three of a kind cards to the highest hand
        List<Card> highestHand = new ArrayList<>(cards.stream()
                .filter(card -> card.getValue().getValue() == threeOfAKindEntry.getKey())
                .limit(3)
                .toList());

        // Get other cards to complete the hand
        Map.Entry<Integer, Integer> secondEntry = sortedOccurrences.poll();
        if (secondEntry != null && secondEntry.getValue() == 1) {
            // Add 1 more card from the second-highest entry
            highestHand.addAll(cards.stream()
                    .filter(card -> card.getValue().getValue() == secondEntry.getKey())
                    .limit(1)
                    .toList());
        }

        // Add the remaining cards to complete the hand
        Map.Entry<Integer, Integer> thirdEntry = sortedOccurrences.poll();
        if (thirdEntry != null && thirdEntry.getValue() == 1) {
            highestHand.addAll(cards.stream()
                    .filter(card -> card.getValue().getValue() == thirdEntry.getKey())
                    .limit(1)
                    .toList());
        }

        // Reset occurrences for the next check
        resetOccurrences();
        return highestHand.size() == 5 ? highestHand : null; // Return null if not a valid Three of a Kind
    }

    private void resetOccurrences() {
        cardOccurrences.clear();
        sortedOccurrences.clear();
    }
}
