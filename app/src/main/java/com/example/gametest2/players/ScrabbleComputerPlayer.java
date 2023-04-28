package com.example.gametest2.players;

import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.GameFramework.players.GameComputerPlayer;
import com.example.GameFramework.utilities.Logger;
import com.example.gametest2.ScrabbleGameState;
import com.example.gametest2.actions.PlayWordAction;
import com.example.gametest2.actions.SkipAction;

import java.util.ArrayList;

public class ScrabbleComputerPlayer extends GameComputerPlayer {

    public ScrabbleComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        //ArrayList<Tile> placeT = new ArrayList<Tile>();
        if(info instanceof NotYourTurnInfo) return;
        int x = (int)(10*Math.random());
        int y = (int)(10*Math.random());
        //dont need a try catch theres a sleep method
        sleep(1);
        ScrabbleGameState sgs = new ScrabbleGameState((ScrabbleGameState)info);

        if(sgs.getPlayerID() != this.playerNum){
            return;
        }
        else{
            //needs to place a tile
            Logger.log("computer player", "sending move");
            for(int i = 1; i <= 3; i++) {
                //place tiles

                //spell-check:
                super.game.sendAction(new SkipAction(this));
            }
        }
    }

}
