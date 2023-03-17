package com.example.gametest2;

public interface TileDataBase {

    public boolean addTile(Character letter, int key);
    public boolean playTile(Character letter, int key);
    public boolean removeTile(Character letter, int key);
}
