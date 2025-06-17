package com.ms.poker.domain.game;

import com.ms.poker.constants.NumConstant;
import com.ms.poker.domain.Card;
import com.ms.poker.domain.state.GameStart;
import com.ms.poker.domain.state.GameState;
import com.ms.poker.domain.user.GameObserver;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<GameObserver> users = new ArrayList<>();
    private GameObserver dealer;
    private GameState gameState;
    private int betAmount = 0;
    private int pot = 0;
    private List<Card> communityCards = new ArrayList<>();

    public Game() {
        // Initialize the game state to GameStart
        this.gameState = new GameStart();
    }

    public void stateHandle() {
        this.gameState.handle(this);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        if (betAmount < 0) {
            System.out.println("Bet amount cannot be negative.");
            return;
        }
        this.betAmount = betAmount;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        if (pot < 0) {
            System.out.println("Pot cannot be negative.");
            return;
        }
        this.pot = pot;
    }

    public List<Card> getCommunityCards() {
        return communityCards;
    }

    public void addCommunityCard(Card card) {
        if (card == null) {
            System.out.println("Cannot add a null card to community cards.");
            return;
        }
        this.communityCards.add(card);
    }

    public void resetCommunityCards() {
        this.communityCards.clear();
    }

    public List<GameObserver> getUsers() {
        return users;
    }

    public GameObserver getDealer() {
        return dealer;
    }

    public void setDealer(GameObserver dealer) {
        // Check if the dealer is already in the list of users
        if (dealer == null || !users.contains(dealer)) {
            System.out.println("Dealer must be a registered user.");
            return;
        }
        this.dealer = dealer;
    }

    public void addUser(GameObserver user) {
        // Check if the user is already in the list
        if (user == null || users.contains(user)) {
            return;
        }
        // Check if the user list is full
        if (users.size() >= NumConstant.MAX_USERS) {
            System.out.println("Game is full. Cannot register more users.");
            return;
        }
        users.add(user);
    }

    public void removeUser(GameObserver user) {
        // Check if the user is registered
        if (user == null || !users.contains(user)) {
            return;
        }
        users.remove(user);
    }

    public void setSmallBind(GameObserver user) {
        if (user == null || !users.contains(user)) {
            return;
        }
        int amountSmallBlind = user.getTable().getSmallBlind();
        user.setSmallBlind(true);
        user.setBetAmount(amountSmallBlind);
        user.setChips(user.getChips() - amountSmallBlind);
    }

    public void setBigBind(GameObserver user) {
        if (user == null || !users.contains(user)) {
            return;
        }
        int amountBigBlind = user.getTable().getBigBlind();
        user.setBigBlind(true);
        user.setBetAmount(amountBigBlind);
        user.setChips(user.getChips() - amountBigBlind);
    }
}
