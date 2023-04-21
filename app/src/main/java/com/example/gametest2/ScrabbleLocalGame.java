package com.example.gametest2;

import com.example.GameFramework.LocalGame;
import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;
import com.example.gametest2.actions.PlayWordAction;
import com.example.gametest2.actions.*;
import com.example.gametest2.players.ScrabbleHumanPlayer;

public class ScrabbleLocalGame extends LocalGame {
    ScrabbleGameState sgs;

public ScrabbleLocalGame(){
    super();
    super.state = new ScrabbleGameState();
}

public ScrabbleLocalGame(ScrabbleGameState scrstate){
    super();
    super.state= new ScrabbleGameState(scrstate);
}

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
    public void start(GamePlayer[]players){
        super.start(players);
    }

    @Override
    protected String checkIfGameOver() {
        String gameOver;
        gameOver = "game not over";
        if(sgs.TileCounter == 100){
            gameOver = "game is over, identifying winner...";
           // return gameOver;
            //need a checkScore of some sort here
        }
        return gameOver;

    }

    @Override
    protected boolean makeMove(GameAction action) {
        int players = super.players.length;
        System.out.println("goes into make move");
        Tile t = new Tile();
        if(action instanceof PlayWordAction){
            if(sgs.getPlayerID() == 1) {
                sgs.setPlayerID(2);
                sgs.setPlayerOneScore(t.getPoints());
            }
            else if (sgs.getPlayerID() == 2){
                sgs.setPlayerID(1);
                sgs.setPlayerTwoScore(t.getPoints());
            }
            return true;
        }

        else if(action instanceof RemoveTilesAction){
            return true;
        }

        else if(action instanceof ShuffleAction){
            return true;
        }

        else if(action instanceof SkipAction){
            if(sgs.getPlayerID() == 1){
                sgs.setPlayerID(2);
            }
            else if (sgs.getPlayerID() == 2) {
                if(players > 2) {
                    sgs.setPlayerID(3);
                }
                else {
                    sgs.setPlayerID(1);
                }
            }
            else if (sgs.getPlayerID() == 3) {
                if(players > 3) {
                    sgs.setPlayerID(4);
                }
                else {
                    sgs.setPlayerID(1);
                }
            }
            else if (sgs.getPlayerID() == 4) {
                sgs.setPlayerID(1);
            }
            else {return false;}
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
