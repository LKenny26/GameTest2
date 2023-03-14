package com.example.gametest2;

import android.graphics.Paint;

import java.util.ArrayList;

public class GameState {
    ArrayList<Character> tiles;
    //points per tile
    ArrayList <Integer> numbers;
    //maybe put into a hashable? might be easier to assign a letter to a tile
    //counter
    int score;
    //state of resources
    boolean tilePlayed;
    boolean spellCheck;
    //taking turns
    boolean firstPlayerTurn;
    boolean aiTurn;
    boolean secondPlayerTurn;
    boolean thirdPlayerTurn;
    boolean fourthPlayerTurn;
    boolean playerVisible;
    //paint obj for playing board
    Paint[][] board;
    //players cant see another players letter
    boolean tilesVisible;
    boolean scoreboardVisible;
    boolean tilePlaced;
    int tileCountPlaced;

    Paint tileToPlay;
    public GameState(){
        board = new Paint[15][15];
        score = 0;
        tilePlayed = true;
        spellCheck = false;
        firstPlayerTurn = true;
        aiTurn = false;
        secondPlayerTurn = false;
        thirdPlayerTurn = false;
        fourthPlayerTurn = false;
        playerVisible = true;
        tilesVisible = true;
        scoreboardVisible = true;
        tileToPlay = new Paint();
        tilePlaced = false;
        tileCountPlaced = 0;
    }
}
