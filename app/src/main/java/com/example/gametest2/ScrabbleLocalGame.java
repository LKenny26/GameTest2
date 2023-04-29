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
    private boolean playerOneSkippedTwice = false;
    private boolean playerTwoSkippedTwice = false;

    public ScrabbleLocalGame(HashSet<String> hash){
        //dr libby helped
        super();
        super.state = new ScrabbleGameState();
        hashet = hash;
    }

    public ScrabbleLocalGame(ScrabbleGameState scrstate, HashSet<String> hash){
        //dr. libby helped
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
        String gameOver;
        if(sgs.getTileCounter() == 100 || (playerOneSkippedTwice && playerTwoSkippedTwice)){
            if(sgs.getPlayerOneScore() > sgs.getPlayerTwoScore()) {
                gameOver = "Game is over, Player One Wins!";
            }
            else {
                gameOver = "Game is over, Computer Wins";
            }

            return gameOver;
        }
        else {
            return null;
        }

    }
    public HashSet<String> getHash(){
        return hashet;
    }


    @Override
    protected boolean makeMove(GameAction action) {
        //dr libby helped
        sgs = (ScrabbleGameState) super.state;
        int players = super.players.length;

        //if the action is playword
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
            sgs.addToTileCounter(((PlayWordAction) action).getLength());
            return true;

        }

        else if(action instanceof SkipAction){
            //TODO: technically only have two players working rn

            //changes the player id based on who is the current player and how many there are
            if(sgs.getPlayerID() == 0){
                playerOneSkippedTwice = ((SkipAction) action).getSkippedTwice();
                sgs.setPlayerID(1);
            }
            else if (sgs.getPlayerID() == 1) {
                if(players > 2) {
                    sgs.setPlayerID(2);
                }
                else {
                    playerTwoSkippedTwice = ((SkipAction) action).getSkippedTwice();
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
