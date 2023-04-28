package com.example.gametest2.players;

import android.util.Log;

import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.GameFramework.players.GameComputerPlayer;
import com.example.GameFramework.utilities.Logger;
import com.example.gametest2.ScrabbleGameState;
import com.example.gametest2.actions.PlayWordAction;

import java.util.ArrayList;
import java.util.List;

public class ScrabbleComputerPlayer extends GameComputerPlayer {

    public ScrabbleComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        //ArrayList<Tile> placeT = new ArrayList<Tile>();
        if(info instanceof NotYourTurnInfo) {
            Log.i("debug", "not my turn");
            return;
        }
        Log.i("debug", "computer is gonna take a turn");
        int x = (int)(10*Math.random());
        int y = (int)(10*Math.random());
        //dont need a try catch theres a sleep method
        sleep(1);
        ScrabbleGameState sgs = new ScrabbleGameState((ScrabbleGameState)info);

        if(sgs.getPlayerID() != this.playerNum){
            return;
        }//iterate through letter through l]
        //method for given board that gives all letters
        //6
        else{
            //needs to place a tile
            Logger.log("computer player", "sending move");
            for(int i = 1; i <= 3; i++) {
                //place tiles
                //spell-check:
            }
            game.sendAction(new PlayWordAction(this, sgs.getPlayerID()));
        }
    }
    public List<Character> getLettersOnBoard(char[][]board){
        List<Character> letters = new ArrayList<>();
        for(char[]row:board){
            for(char cell : row){
                if(Character.isLetter(cell)){
                    letters.add(cell);
                }
            }
        }
        return letters;
    }

}
