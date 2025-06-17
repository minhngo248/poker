package com.ms.poker.domain.state;

import com.ms.poker.domain.Deck;
import com.ms.poker.domain.game.Game;
import com.ms.poker.domain.user.GameObserver;

import java.util.List;

public class GameStart implements GameState {
    @Override
    public void handle(Game game) {
        // Initialize the game state, set up players, blinds, etc.
        System.out.println("Game is starting...");
        List<GameObserver> users = game.getUsers();

        // Dealer should be set before starting the game
        GameObserver dealer = game.getDealer();
        if (dealer == null) {
            System.out.println("Please set a dealer before starting the game.");
            return;
        }

        // set the small blind and big blind
        int indexOfDealer = users.indexOf(dealer);
        int indexOfSmallBlind = (indexOfDealer + 1) % users.size();
        int indexOfBigBlind = (indexOfDealer + 2) % users.size();

        // Set small blind and big blind
        game.setSmallBind(users.get(indexOfSmallBlind));
        game.setBigBind(users.get(indexOfBigBlind));

        // Get deck and shuffle it
        Deck.shuffle();

        // Deal cards to players
        for (GameObserver user : users) {
            user.addToHand(Deck.getCards().remove(0)); // Deal first card
            user.addToHand(Deck.getCards().remove(0)); // Deal second card
        }

        // Print the hands of each player
        for (GameObserver user : users) {
            System.out.println(user.getName() + "'s hand: " + user.getHand());
        }

        // Set the bet amount of game
        game.setBetAmount(users.get(0).getTable().getBigBlind());

        // Set the game state to PreFlop
        game.setGameState(new PreFlop());
    }
}
