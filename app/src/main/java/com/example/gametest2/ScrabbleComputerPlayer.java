package com.example.gametest2;

import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameComputerPlayer;

public class ScrabbleComputerPlayer extends GameComputerPlayer {
    ShuffleAction shf = new ShuffleAction(this);
    SkipAction skip = new SkipAction(this);
    SpellCheckAction sca = new SpellCheckAction(this);
    PlayWordAction pwa = new PlayWordAction(this);
    public ScrabbleComputerPlayer(String name){
        super(name);
    }

    public void receiveInfo(GameInfo info){
    //gameState gs = new gameState((gameState) info);
    //complete dumb ai here
    }
}
