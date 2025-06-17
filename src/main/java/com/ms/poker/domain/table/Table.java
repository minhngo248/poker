package com.ms.poker.domain.table;

import com.ms.poker.constants.NumConstant;
import com.ms.poker.domain.user.TableObserver;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<TableObserver> users = new ArrayList<>();
    private final int smallBlind;
    private final int bigBlind;
    private final int defaultBuyIn;

    public Table(int smallBlind, int bigBlind, int defaultBuyIn) {
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
        this.defaultBuyIn = defaultBuyIn;
    }

    public List<TableObserver> getUsers() {
        return users;
    }

    public int getSmallBlind() {
        return smallBlind;
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public int getDefaultBuyIn() {
        return defaultBuyIn;
    }

    public void addUser(TableObserver tableObserver) {
        // Check if the tableObserver is already registered
        if (tableObserver == null || users.contains(tableObserver)) {
            return;
        }
        // Check if table is full of 9 users
        if (users.size() >= NumConstant.MAX_USERS) {
            System.out.println("Table is full. Cannot register more users.");
            return;
        }
        users.add(tableObserver);
    }

    public void removeUser(TableObserver tableObserver) {
        // Check if the tableObserver is registered
        if (tableObserver == null || !users.contains(tableObserver)) {
            return;
        }
        users.remove(tableObserver);
    }

    public void notifyUsers() {
        // Notify all registered observers
        for (TableObserver tableObserver : users) {
            if (tableObserver != null) {
                tableObserver.notifyResult();
            }
        }
    }
}
