package com.ms.poker.domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards;

    private Deck() {}

    public static Deck getInstance() {
        Deck deck = new Deck();
        deck.cards = createDeck();
        return deck;
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
}
