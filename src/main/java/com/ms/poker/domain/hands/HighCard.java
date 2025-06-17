package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;

import java.util.Comparator;
import java.util.List;

public class HighCard extends Hand {
    public HighCard() {
        super("High Card");
    }

    @Override
    public List<Card> getHighestHands(List<Card> cards) {
        if (cards == null || cards.size() < 7) {
            System.out.println("We need at least 7 cards to check for a High Card.");
            return null; // Not enough cards to form a High Card
        }

        // Sort the cards by value in descending order, then by suit
        cards.sort(Comparator.comparing(Card::getValue, Comparator.reverseOrder())
                .thenComparing(Card::getSuit));

        // Select the top 5 cards as the highest hand
        List<Card> highestHand = cards.stream().limit(5).toList();

        return highestHand;
    }
}
