package com.example.gametest2;

import com.example.GameFramework.LocalGame;
import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;

public class ScrabbleLocalGame extends LocalGame {
    ScrabbleGameState sgs;
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        sgs = new ScrabbleGameState();
    }

    @Override
    protected boolean canMove(int playerIdx) {
        return true;
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return true;
    }
}
