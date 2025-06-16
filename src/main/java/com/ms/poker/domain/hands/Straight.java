package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.Value;

import java.util.Comparator;
import java.util.List;

public class Straight extends Hand {
    /**
     * Constructor for the Straight class.
     */
    public Straight() {
        super("Straight");
    }

    /**
     * Checks if the given list of 7 cards represents a Straight from 5 cards.
     *
     * @param cards the list of 7 cards to check
     * @return a list of 5 cards representing the highest Straight if found, otherwise returns null
     */
    @Override
    public List<Card> getHighestHands(List<Card> cards) {
        // A Straight consists of 5 consecutive cards of different suits.
        if (cards == null || cards.size() < 7) {
            System.out.println("We need at least 7 cards to check for a Straight.");
            return null; // Not enough cards to form a Straight
        }

        // sort the cards by value in descending order
        // and then by suit
        cards.sort(Comparator.comparing(Card::getValue, Comparator.reverseOrder())
                .thenComparing(Card::getSuit));

        // Use a Set to track unique card by value
        Card[] uniqueCards = cards.toArray(new Card[0]);
        int nbUniqueCards = uniqueCardCount(uniqueCards);
        if (nbUniqueCards < 5) {
            return null; // Not enough unique cards to form a Straight
        }

        // Check for a Straight
        int firstCardIndex = hasStraight(uniqueCards, nbUniqueCards);
        if (firstCardIndex != -1 && firstCardIndex != -2) {
            // If a Straight is found, return the highest 5 cards
            return List.of(uniqueCards[firstCardIndex], uniqueCards[firstCardIndex+1],
                    uniqueCards[firstCardIndex+2], uniqueCards[firstCardIndex+3],
                    uniqueCards[firstCardIndex+4]);
        }

        if (firstCardIndex == -2) {
            // If a Straight with low Ace is found, return the highest 5 cards
            return List.of(uniqueCards[nbUniqueCards-4], uniqueCards[nbUniqueCards-3],
                    uniqueCards[nbUniqueCards-2], uniqueCards[nbUniqueCards-1],
                    uniqueCards[0]);
        }
        return null; // No Straight found
    }

    /**
     * Counts the number of unique cards in the array and
     * rearranges them.
     *
     * @param cards the array of cards to process
     * @return the count of unique cards
     */
    private int uniqueCardCount(Card[] cards) {
        int left = 0;
        int right = 0;
        while (right < cards.length) {
            if (cards[left].getValue() != cards[right].getValue()) {
                // swap the right card with the (left + 1) card
                Card temp = cards[left + 1];
                cards[left + 1] = cards[right];
                cards[right] = temp;
                left++;
            }
            right++;
        }
        return left + 1; // Return the count of unique cards
    }

    /**
     * Checks if the given array of cards contains a Straight.
     *
     * @param cards the array of cards to check
     * @param nbUniqueCards the number of unique cards in the array
     * @return the index of the first card in the Straight if found, otherwise -1
     */
    private int hasStraight(Card[] cards, int nbUniqueCards) {
        if (nbUniqueCards < 5) {
            return -1; // Not enough unique cards to form a Straight
        }

        // Check for consecutive values
        for (int i = 0; i <= nbUniqueCards - 5; i++) {
            if (cards[i].getValue().getValue() - cards[i + 4].getValue().getValue() == 4) {
                return i; // Found a Straight
            }
        }

        // Ace is low
        if (cards[0].getValue() == Value.ACE &&
                cards[nbUniqueCards-1].getValue() == Value.TWO &&
                cards[nbUniqueCards-2].getValue() == Value.THREE &&
                cards[nbUniqueCards-3].getValue() == Value.FOUR &&
                cards[nbUniqueCards-4].getValue() == Value.FIVE) {
            return -2; // Found a Straight with low Ace
        }
        return -1; // No Straight found
    }
}
