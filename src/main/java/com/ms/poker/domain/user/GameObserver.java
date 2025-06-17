package com.ms.poker.domain.user;

import com.ms.poker.domain.Card;
import com.ms.poker.domain.table.Table;

import java.util.List;

public interface GameObserver {
    void setSmallBlind(boolean smallBlind);
    void setBigBlind(boolean bigBlind);
    void setBetAmount(int betAmount);
    void setChips(int chips);
    int getChips();

    String getName();
    Table getTable();
    List<Card> getHand();
    void addToHand(Card card);
    void resetHand();
}
