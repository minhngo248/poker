package com.ms.poker;

import com.ms.poker.domain.game.Game;
import com.ms.poker.domain.table.Table;
import com.ms.poker.domain.user.User;

public class Main {
    public static void main(String[] args) {
        // This is the entry point of the application.
        System.out.println("Welcome to the Poker Game!");

        // Initialize game components here
        Table table = new Table(10, 20, 1000);
        Game game = new Game();

        // 5 users are created and added to the table
        // and then to the game
        for (int i = 1; i <= 5; i++) {
            String userName = "User" + i;
            User user = new User(userName);
            user.sitAtTable(table);
            user.joinGame(game);
            user.setChips(table.getDefaultBuyIn()); // Set initial chips for the user
            user.setBuyIn(table.getDefaultBuyIn()); // Set initial buy-in for the user
            if (i == 1) {
                // Set the first user as the dealer
                game.setDealer(user);
            }
        }

        // Start the game
        // will now proceed through its states
        while (game.getGameState() != null) {
            game.stateHandle();
        }
    }
}
