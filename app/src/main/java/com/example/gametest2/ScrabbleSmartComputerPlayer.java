package com.example.gametest2;

import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameComputerPlayer;

public class ScrabbleSmartComputerPlayer extends GameComputerPlayer {

    public ScrabbleSmartComputerPlayer(String name) {
        super(name);
    }
    @Override
    protected void receiveInfo(GameInfo info) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}