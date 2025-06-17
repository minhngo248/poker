package com.ms.poker.domain.user;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.game.Game;
import com.ms.poker.domain.table.Table;

import java.util.ArrayList;
import java.util.List;

public class User implements TableObserver, GameObserver {
    private final String name;
    private Table table;
    private Game game;

    // User's hand of 2 cards
    private List<Card> hand = new ArrayList<>();
    private int chips;
    private int buyIn;
    private int betAmount = 0;
    private boolean isSmallBlind = false;
    private boolean isBigBlind = false;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Table getTable() {
        return table;
    }

    @Override
    public List<Card> getHand() {
        return hand;
    }

    @Override
    public void addToHand(Card card) {
        this.hand.add(card);
    }

    @Override
    public void resetHand() {
        this.hand.clear();
    }

    @Override
    public int getChips() {
        return chips;
    }

    @Override
    public void setChips(int chips) {
        this.chips = chips;
    }

    public int getBuyIn() {
        return buyIn;
    }

    public void setBuyIn(int buyIn) {
        this.buyIn = buyIn;
    }

    public int getBetAmount() {
        return betAmount;
    }

    @Override
    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public boolean isSmallBlind() {
        return isSmallBlind;
    }

    @Override
    public void setSmallBlind(boolean smallBlind) {
        isSmallBlind = smallBlind;
    }

    public boolean isBigBlind() {
        return isBigBlind;
    }

    @Override
    public void setBigBlind(boolean bigBlind) {
        isBigBlind = bigBlind;
    }

    public void sitAtTable(Table table) {
        this.table = table;
        table.addUser(this);
    }

    public void leaveTable() {
        if (table != null) {
            table.removeUser(this);
            table = null;
        }
    }

    public void joinGame(Game game) {
        this.game = game;
        game.addUser(this);
    }

    public void leaveGame() {
        if (game != null) {
            game.removeUser(this);
            game = null;
        }
    }

    @Override
    public void notifyResult() {
        System.out.println(name + " has " + (chips-buyIn) + " chips after the game.");
    }
}
