package com.ms.poker.domain.state;

import com.ms.poker.domain.game.Game;
import com.ms.poker.domain.user.GameObserver;

import java.util.List;

public class PreFlop implements GameState {
    @Override
    public void handle(Game game) {
        List<GameObserver> users = game.getUsers();
        int indexOfDealer = users.indexOf(game.getDealer());

        // We want to get the 3-next player after the dealer
        int afterBigBlindIndex = (indexOfDealer + 3) % users.size();

        // All players from the player `afterBigBlindIndex`
        // to the `bigBlindIndex` act
        // need to implement the listener for user action

        /*for (int i = afterBigBlindIndex; i != indexOfDealer; i = (i + 1) % users.size()) {
            GameObserver user = users.get(i);
            // Reset the bet amount for each user
            user.setBetAmount(0);
            // Notify the user to act
            System.out.println(user.getName() + ", it's your turn to act.");
        } */

        // After all players have acted, we burn a card
        // and deal the flop (3 community cards)
        System.out.println("Players have acted.");

        // reset the bet amount for all users
        // and for the game
        for (GameObserver user : users) {
            user.setBetAmount(0);
        }
        game.setBetAmount(0);

        // Set the game state to Flop
        game.setGameState(new Flop());
    }
}
