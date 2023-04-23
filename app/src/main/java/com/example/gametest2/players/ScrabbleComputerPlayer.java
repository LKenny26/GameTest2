package com.example.gametest2.players;

import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameComputerPlayer;
import com.example.GameFramework.utilities.Logger;
import com.example.gametest2.ScrabbleGameState;
import com.example.gametest2.actions.CompMoveAction;
import com.example.gametest2.actions.PlayWordAction;

public class ScrabbleComputerPlayer extends GameComputerPlayer {

    public ScrabbleComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        int x = (int)(10*Math.random());
        int y = (int)(10*Math.random());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ScrabbleGameState sgs = new ScrabbleGameState((ScrabbleGameState)info);
        if(sgs.getPlayerID() != this.playerNum){
            return;
        }
        else{

            //needs to place a tile
            Logger.log("computer player", "sending move");
            for(int i = 1; i <= 3; i++) {
                game.sendAction(new CompMoveAction(this, x*i, y*i));
            }
            game.sendAction(new PlayWordAction(this, sgs.getPlayerID()));
        }
    }
}
