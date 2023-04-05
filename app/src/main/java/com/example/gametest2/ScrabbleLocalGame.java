package com.example.gametest2;

import com.example.GameFramework.LocalGame;
import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;

public class ScrabbleLocalGame extends LocalGame {
    gameState gs;

    public ScrabbleLocalGame(){
        gs = new gameState();
    }


    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == gs.getPlayerId()){
            return true;
        }
        else{
            return false;
        }
    }

    protected boolean makeMove(GameAction action) {
        return false;
        //idk what to do for this method
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p){
        gameState copy = new gameState(gs);
        p.sendInfo(copy);
        //idk how to fix this error
    }

    @Override
    protected String checkIfGameOver() {
        String gameOver;
        if(gs.tileCountPlaced == 100){
            gameOver = "game is over, identifying winner...";
            //need a checkScore of some sort here
        }
    }



}
