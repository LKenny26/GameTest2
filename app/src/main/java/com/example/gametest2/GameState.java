package com.example.gametest2;

import android.graphics.Paint;

import java.util.ArrayList;

public class GameState {
    ArrayList<Character> tiles;
    //points per tile
    ArrayList <Integer> numbers;
    //maybe put into a hashable? might be easier to assign a letter to a tile
    //counter
    int score = 0;
    //state of resources
    boolean tilePlayed = false;
    boolean spellCheck = false;
    //taking turns
    boolean firstPlayerTurn = true;
    boolean aiTurn = false;
    boolean secondPlayerTurn = false;
    boolean thirdPlayerTurn = false;
    boolean fourthPlayerTurn = false;
    boolean playerVisible = true;
    //paint obj for playing board
    Paint[][] board = new Paint[15][15];
    //players cant see another players letter
    boolean tilesVisible = false;
    boolean scoreboardVisible = true;

    Paint tileToPlay;
}
