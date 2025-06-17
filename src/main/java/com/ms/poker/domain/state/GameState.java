package com.ms.poker.domain.state;

import com.ms.poker.domain.game.Game;

public interface GameState {
    void handle(Game game);
}
