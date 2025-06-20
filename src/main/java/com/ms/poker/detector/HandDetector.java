package com.ms.poker.detector;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.hands.*;

import java.util.List;

public class HandDetector {
    private static final List<Hand> hands = List.of(
            new RoyalFlush(),
            new StraightFlush(),
            new FourOfAKind(),
            new FullHouse(),
            new Flush(),
            new Straight(),
            new ThreeOfAKind(),
            new TwoPair(),
            new Pair(),
            new HighCard()
    );

    /**
     * Detects the highest poker hand from a list of cards.
     *
     * @param cards the list of 7 cards to check
     * @return a list of 5 cards representing the highest hand, or null if no valid hand is found
     */
    public static List<Card> getHandValues(List<Card> cards) {
        if (cards == null || cards.size() < 7) {
            System.out.println("We need at least 7 cards to check for a hand.");
            return null; // Not enough cards to form a valid hand
        }

        for (Hand hand : hands) {
            List<Card> highestHand = hand.getHighestHands(cards);
            if (highestHand != null) {
                System.out.println("Detected hand: " + hand.getClass().getSimpleName());
                return highestHand; // Return the first valid hand found
            }
        }

        return null; // No valid hand found
    }
}
