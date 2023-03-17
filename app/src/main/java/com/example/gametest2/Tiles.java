package com.example.gametest2;

import java.util.Hashtable;

public class Tiles implements TileDataBase{

    GameState gs = new GameState();
    private Hashtable<String, Integer> tile = new Hashtable<String, Integer>();

    @Override
    public boolean addTile(Character letter, int key) {
        //this is the users personal set of letters
        String addLetter = letter.toString();
        if(tile.get(addLetter) != null){
            return false;
        }
        else{
            Pieces tl = new Pieces(letter, key);
            tile.put(addLetter, key);
            return true;
        }
        //return true;
    }

    @Override
    public boolean playTile(Character letter, int key) {
        return true;
    }

    @Override
    public boolean removeTile(Character letter, int key) {
        return true;
    }
}
