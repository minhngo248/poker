package com.ms.poker.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static volatile List<Card> cards;

    private Deck() {}

    public static List<Card> getCards() {
        if (cards == null) {
            synchronized (Deck.class) {
                if (cards == null) {
                    cards = createDeck();
                }
            }
        }
        return cards;
    }

    private static List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                deck.add(new Card(value, suit));
            }
        }
        return deck;
    }

    public static void shuffle() {
        Collections.shuffle(getCards());
    }

    public static void resetDeck() {
        synchronized (Deck.class) {
            cards = createDeck();
        }
    }
}