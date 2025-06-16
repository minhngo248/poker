package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class FourOfAKind extends Hand {
    private HashMap<Integer, Integer> cardOccurrences;

    public FourOfAKind() {
        super("Four of a Kind");
        cardOccurrences = new HashMap<>();
    }

    @Override
    public List<Card> getHighestHands(List<Card> cards) {
        // A Four of a Kind consists of four cards of the same rank and one other card.
        if (cards == null || cards.size() < 7) {
            System.out.println("We need at least 7 cards to check for a Four of a Kind.");
            return null; // Not enough cards to form a Four of a Kind
        }

        // sort the cards by value in descending order
        cards.sort(Comparator.comparing(Card::getValue, Comparator.reverseOrder())
                .thenComparing(Card::getSuit));

        // Count occurrences of each card value
        for (Card card : cards) {
            int value = card.getValue().getValue();
            cardOccurrences.put(value, cardOccurrences.getOrDefault(value, 0) + 1);
        }

        List<Card> highestHand = new ArrayList<>();
        for (Integer value : cardOccurrences.keySet()) {
            if (cardOccurrences.get(value) == 4) {
                // Add the four of a kind cards to the highest hand
                highestHand.addAll(cards.stream()
                        .filter(card -> card.getValue().getValue() == value)
                        .limit(4)
                        .toList());
            }
        }

        // Add the highest remaining card to the hand
        if (highestHand.size() == 4) {
            cards.stream()
                .filter(card -> !highestHand.contains(card))
                .findFirst()
                .ifPresent(highestHand::add);
        }

        // Reset occurrences for the next check
        resetOccurrences();
        return highestHand.size() == 5 ? highestHand : null; // Return null if not a valid Four of a Kind
    }

    private void resetOccurrences() {
        cardOccurrences.clear();
    }
}
