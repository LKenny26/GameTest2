package com.example.gametest2;

import android.graphics.Paint;

import java.util.ArrayList;

public class GameState {
    ArrayList<Character> tiles;
    //points per tile
    ArrayList<Integer> numbers;
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
    //copy class
    GameState copyGameState = new GameState();
    //constructor
    public GameState(int score, boolean tilePlayed, boolean spellCheck, boolean playerVisible, boolean tilesVisible, boolean scoreboardVisible) {
        this.score = score;
        this.tilePlayed = tilePlayed;
        this.spellCheck = spellCheck;
        this.playerVisible = playerVisible;
        this.tilesVisible = tilesVisible;
        this.scoreboardVisible = scoreboardVisible;
    }

    //copy constructor
    public GameState (GameState game , boolean firstPlayerTurn ){
        this.firstPlayerTurn = firstPlayerTurn ;
        this.score =  game.score;

    }

    //getter methods
    public boolean getFirstPlayerTurn(){
        return firstPlayerTurn;
    }

    public int  getScore(){
        return score;
    }



    //setter methods

}
