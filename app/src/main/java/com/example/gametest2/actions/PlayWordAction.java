package com.example.gametest2.actions;

import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;
import com.example.gametest2.Tile;
import com.example.gametest2.players.ScrabbleHumanPlayer;

import java.util.Hashtable;

public class PlayWordAction extends GameAction {

    private int score;
    private boolean spellCheck;
    private int length;

    public PlayWordAction(GamePlayer player, boolean bool, int currScore, int l){
        super(player);
        spellCheck = bool;
        score = currScore;
        length = l;
    }

    public boolean getSpellCheck(){
        return spellCheck;
    }

    public int getScore() {
        return score;
    }
    public int getLength() {return length;}
}
