package com.example.gametest2.actions;

import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;
import com.example.gametest2.Tile;
import com.example.gametest2.players.ScrabbleHumanPlayer;

import java.util.Hashtable;

public class PlayWordAction extends GameAction {

    //Hashtable <Character, Integer> hable = new Hashtable<Character, Integer>();
    //Tile t;
    //private int id;
    private boolean spellCheck;

    public PlayWordAction(GamePlayer player, boolean bool){
        super(player);
        //t = new Tile();
        //hable.put(t.getChar(), t.getPoints());
        //this.id = idx;
        spellCheck = bool;

    }

    public boolean getSpellCheck(){
        return spellCheck;
    }
}
