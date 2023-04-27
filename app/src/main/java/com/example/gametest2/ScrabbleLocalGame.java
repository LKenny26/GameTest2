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
        //ScrabbleGameState cp = new ScrabbleGameState(sgs);
        //p.sendInfo(cp);
        p.sendInfo(new ScrabbleGameState((ScrabbleGameState) state));
    }

    @Override
    protected boolean canMove(int playerIdx) {
        //it needs to be this way
        return playerIdx == ((ScrabbleGameState)state).getPlayerID();
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
        System.out.println("goes into make move");
        Tile t = new Tile();
        if(action instanceof PlayWordAction /*&& canMove(((PlayWordAction) action).getID())*/) {
            if (sgs.getPlayerID() == 0) {
                sgs.setPlayerID(1);
                sgs.setPlayerOneScore(t.getPoints());
            } else if (sgs.getPlayerID() == 1) {
                sgs.setPlayerID(0);
                sgs.setPlayerTwoScore(t.getPoints());
            }
            return true;

        }

        else if(action instanceof SkipAction){
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
            else {return false;}
            return true;
        }



        //return true;
        else {
            return false;
        }
    }
}
