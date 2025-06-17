package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Pair extends Hand {
    private HashMap<Integer, Integer> cardOccurrences;

    public Pair() {
        super("Pair");
        this.cardOccurrences = new HashMap<>();
    }

    @Override
    public List<Card> getHighestHands(List<Card> cards) {
        if (cards == null || cards.size() < 7) {
            System.out.println("We need at least 7 cards to check for a Pair.");
            return null; // Not enough cards to form a Pair
        }

        cards.sort(Comparator.comparing(Card::getValue, Comparator.reverseOrder())
                .thenComparing(Card::getSuit));

        // Count occurrences of each card value
        for (Card card : cards) {
            int value = card.getValue().getValue();
            cardOccurrences.put(value, cardOccurrences.getOrDefault(value, 0) + 1);
        }

        // Find the first pair
        List<Card> highestHand = new ArrayList<>();
        for (Integer value : cardOccurrences.keySet()) {
            if (cardOccurrences.get(value) == 2) {
                // Add the pair cards to the highest hand
                highestHand.addAll(cards.stream()
                        .filter(card -> card.getValue().getValue() == value)
                        .limit(2)
                        .toList());
                break; // Only need one pair
            }
        }

        // Add 3 remaining highest cards to the hand
        if (highestHand.size() < 2) {
            resetOccurrences();
            return null; // No pair found
        }

        highestHand.addAll(cards.stream()
                .filter(card -> !highestHand.contains(card))
                .limit(3)
                .toList());
        resetOccurrences();
        return highestHand.size() == 5 ? highestHand : null;
    }

    private void resetOccurrences() {
        cardOccurrences.clear();
    }
}
