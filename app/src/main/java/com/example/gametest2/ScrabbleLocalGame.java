package com.example.gametest2;

import com.example.GameFramework.LocalGame;
import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;
import com.example.gametest2.actions.PlayWordAction;
import com.example.gametest2.actions.*;
import com.example.gametest2.players.ScrabbleHumanPlayer;

import java.util.HashSet;

public class ScrabbleLocalGame extends LocalGame {
    ScrabbleGameState sgs;
    HashSet<String> hashet;

    public ScrabbleLocalGame(HashSet<String> hash){
        super();
        super.state = new ScrabbleGameState();
        hashet = hash;
    }

    public ScrabbleLocalGame(ScrabbleGameState scrstate, HashSet<String> hash){
        super();
        super.state= new ScrabbleGameState(scrstate);
        hashet = hash;
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new ScrabbleGameState((ScrabbleGameState) state));
    }

    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == ((ScrabbleGameState)state).getPlayerID();
    }
    @Override
    public void start(GamePlayer[]players){
        super.start(players);
    }

    @Override
    protected String checkIfGameOver() {
        //TODO: something is wrong null should be pushed if game is not over
        String gameOver;
        gameOver = "game not over";
        if(sgs.TileCounter == 100){
            gameOver = "game is over, identifying winner...";
           // return gameOver;
            //need a checkScore of some sort here
        }
        return null;
        //return gameOver;

    }
    public boolean valid(String string){
        return hashet.contains(string);
    }
    public HashSet<String> getHash(){
        return hashet;
    }


    @Override
    protected boolean makeMove(GameAction action) {
        sgs = (ScrabbleGameState) super.state;
        int players = super.players.length;

        if(action instanceof PlayWordAction) {
            if (sgs.getPlayerID() == 0) {
                //current player is 0
                if(((PlayWordAction) action).getSpellCheck()){
                    //only change players and set score if the word is valid
                    sgs.setPlayerOneScore(((PlayWordAction) action).getScore());
                    sgs.setPlayerID(1);
                }
            } else if (sgs.getPlayerID() == 1) {
                //current player is 1
                if(((PlayWordAction) action).getSpellCheck()) {
                    //only change players and set score if the word is valid
                    sgs.setPlayerTwoScore(((PlayWordAction) action).getScore());
                    sgs.setPlayerID(0);
                }
            }
            return true;

        }

        else if(action instanceof SkipAction){
            //TODO: technically only have two players working rn

            //changes the player id based on who is the current player and how many there are
            if(sgs.getPlayerID() == 0){
                sgs.setPlayerID(1);
            }
            else if (sgs.getPlayerID() == 1) {
                if(players > 2) {
                    sgs.setPlayerID(2);
                }
                else {
                    sgs.setPlayerID(0);
                }
            }
            else if (sgs.getPlayerID() == 2) {
                if(players > 3) {
                    sgs.setPlayerID(3);
                }
                else {
                    sgs.setPlayerID(0);
                }
            }
            else if (sgs.getPlayerID() == 3) {
                sgs.setPlayerID(0);
            }
            else {
                //player id is wrong
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }
}
