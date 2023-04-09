package com.example.gametest2;

import com.example.GameFramework.LocalGame;
import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;

public class ScrabbleLocalGame extends LocalGame {
    ScrabbleGameState sgs;



    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        sgs = new ScrabbleGameState();
        ScrabbleGameState cp = new ScrabbleGameState(sgs);
        p.sendInfo(cp);
    }

    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == sgs.getPlayerID()){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    protected String checkIfGameOver() {
        String gameOver;
        gameOver = "game not over";
        if(sgs.TileCounter == 100 && (sgs.getPlayer1TileCount() == 0 || sgs.getPlayer2TileCount() == 0
         || sgs.getPlayer3TileCount() == 0 || sgs.getPlayer4TileCount() == 0)){
            gameOver = "game is over, identifying winner...";
            return gameOver;
            //need a checkScore of some sort here
        }
        return gameOver;

    }

    @Override
    protected boolean makeMove(GameAction action) {
        int players = super.players.length;
        if(action instanceof PlayWordAction){
            return true;
        }

        else if(action instanceof GetNewTilesAction){
            return true;
        }

        else if(action instanceof ShuffleAction){
            return true;
        }

        else if(action instanceof SkipAction){
            return true;
        }

        else if(action instanceof SpellCheckAction){
            return true;
        }

        //return true;
        else {
            return false;
        }
    }
}
