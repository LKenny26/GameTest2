package com.example.gametest2.actions;

import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;

public class SkipAction extends GameAction {
    private boolean skippedTwice;

    public SkipAction(GamePlayer player, boolean skippedTwice){
        super(player);
        this.skippedTwice = skippedTwice;
    }

    public boolean getSkippedTwice() {
        return skippedTwice;
    }
}

