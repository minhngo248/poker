package com.ms.poker.domain.state;

import com.ms.poker.detector.HandDetector;
import com.ms.poker.domain.Card;
import com.ms.poker.domain.Deck;
import com.ms.poker.domain.game.Game;
import com.ms.poker.domain.user.GameObserver;

import java.util.ArrayList;
import java.util.List;

public class GameEnd implements GameState {
    @Override
    public void handle(Game game) {
        System.out.println("The game has ended. Calculating results...");

        // Print hand results for each player
        for (GameObserver user : game.getUsers()) {
            System.out.println("User: " + user.getName());
            List<Card> hand = new ArrayList<>(user.getHand());
            hand.addAll(game.getCommunityCards());
            List<Card> finalHand = HandDetector.getHandValues(hand);

            // Print all cards in the final hand
            System.out.println("Final Hand: " + finalHand);
        }

        // Reset deck
        Deck.resetDeck();

        // Reset bet amount, pot and community cards
        game.setBetAmount(0);
        game.setPot(0);
        game.resetCommunityCards();

        // Reset each user's hand, small blind, big blind, and bet amount
        for (GameObserver user : game.getUsers()) {
            user.resetHand();
            user.setSmallBlind(false);
            user.setBigBlind(false);
            user.setBetAmount(0);
        }
        game.setGameState(null);
    }
}
