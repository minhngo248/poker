package com.ms.poker.domain.state;

import com.ms.poker.domain.Deck;
import com.ms.poker.domain.game.Game;
import com.ms.poker.domain.user.GameObserver;

public class Flop implements GameState {
    @Override
    public void handle(Game game) {
        System.out.println("Burning a card and dealing the flop...");

        // Burn a card (remove the top card from the deck)
        Deck.getCards().remove(0); // Burn the top card

        // Deal the flop (3 community cards)
        game.addCommunityCard(Deck.getCards().remove(0));
        game.addCommunityCard(Deck.getCards().remove(0));
        game.addCommunityCard(Deck.getCards().remove(0));

        System.out.println("Flop dealt: " +
            game.getCommunityCards().get(0) + ", " +
            game.getCommunityCards().get(1) + ", " +
            game.getCommunityCards().get(2));

        // Players act hereafter
        System.out.println("Players have acted.");

        // reset the bet amount for all users
        // and for the game
        for (GameObserver user : game.getUsers()) {
            user.setBetAmount(0);
        }
        game.setBetAmount(0);

        // Set the game state to Turn
        game.setGameState(new Turn());
    }
}
