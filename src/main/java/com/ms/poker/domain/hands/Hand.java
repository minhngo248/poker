package com.ms.poker.domain.hands;

import com.ms.poker.domain.Card;

import java.util.List;

public abstract class Hand {
    protected final String name;

    /**
     * Constructor for the Hand class.
     *
     * @param name the name of the hand
     */
    protected Hand(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the hand.
     *
     * @return the name of the hand
     */
    public String toString() {
        return name;
    }

    /**
     * Returns a combination of 5 highest cards in the hand.
     * if the hand is not valid, it returns null.
     *
     * @param cards the list of 7 cards
     * @return the name of the hand with the highest card, or null if the hand is not valid
     */
    public abstract List<Card> getHighestHands(List<Card> cards);

}
