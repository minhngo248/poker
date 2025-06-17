package com.ms.poker.domain.state;

import com.ms.poker.domain.Deck;
import com.ms.poker.domain.game.Game;
import com.ms.poker.domain.user.GameObserver;

public class Turn implements GameState {
    @Override
    public void handle(Game game) {
        System.out.println("Burning a card and dealing the turn...");

        Deck.getCards().remove(0); // Burn the top card

        // Deal the turn (4th community card)
        game.addCommunityCard(Deck.getCards().remove(0));

        System.out.println("Turn dealt: " + game.getCommunityCards().get(3));

        // Players act hereafter
        System.out.println("Players have acted.");

        // Reset the bet amount for all users
        for (GameObserver user : game.getUsers()) {
            user.setBetAmount(0);
        }
        game.setBetAmount(0);

        // Set the game state to River
        game.setGameState(new River());
    }
}
