package com.example.gametest2;

public interface TileDataBase {

    public boolean addTile(String letter, int key);
    public boolean playTile(String letter, int key);
    public boolean removeTile(String letter, int key);
}
