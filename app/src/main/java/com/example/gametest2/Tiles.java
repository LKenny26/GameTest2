package com.example.gametest2;

import java.util.Hashtable;

public class Tiles implements TileDataBase{

    GameState gs = new GameState();
    private Hashtable<String, Integer> tile = new Hashtable<String, Integer>();
    @Override
    public boolean addTile(String letter, int key) {
        return true;
    }

    @Override
    public boolean playTile(String letter, int key) {
        return true;
    }

    @Override
    public boolean removeTile(String letter, int key) {
        return true;
    }
}
