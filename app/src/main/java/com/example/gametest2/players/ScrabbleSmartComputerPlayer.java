package com.example.gametest2.players;

import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameComputerPlayer;

public class ScrabbleSmartComputerPlayer extends GameComputerPlayer {

    public ScrabbleSmartComputerPlayer(String name) {
        super(name);
    }
    @Override
    protected void receiveInfo(GameInfo info) {
        sleep(2);
    }
}