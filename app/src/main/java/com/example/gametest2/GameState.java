package com.example.gametest2;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameState {
    ArrayList<Character> tiles;
    //points per tile
    ArrayList<Integer> numbers;
    //maybe put into a hashable? might be easier to assign a letter to a tile
    //counter
    int score;
    //state of resources
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
    ArrayList<String> wordMade;

    Paint tileToPlay;
    //players:
    public static final int player1Id = 1;
    public static final int player2Id = 2;
    public static final int player3Id = 3;
    public static final int player4Id = 4;

    public static final int ai_stupid_Id = 2;
    public static final int ai_smart_Id = 2;


    //action methods
    public boolean playTile() {
        tilePlaced = true;
        tileCountPlaced++;
        //need to remove tile from the two arrays and attach character to wordMade
        return tilePlaced;
    }

    //constructor
    public GameState(int score, boolean spellCheck, boolean tilesVisible, boolean scoreboardVisible, boolean firstPLayerTurn) {
        this.score = score;
        this.spellCheck = spellCheck;
        this.tilesVisible = tilesVisible;
        this.scoreboardVisible = scoreboardVisible;
        this.firstPlayerTurn = firstPLayerTurn;
    }

    public GameState(int score, boolean spellCheck, boolean tilesVisible, boolean firstPlayerTurn, boolean aiTurn, boolean
            secondPlayerTurn, boolean thirdPlayerTurn, boolean fourthPlayerTurn, boolean scoreboardVisible, GameState game) {
        this.firstPlayerTurn = firstPlayerTurn;
        this.score = game.score;
        this.secondPlayerTurn = secondPlayerTurn;
        this.thirdPlayerTurn = thirdPlayerTurn;
        this.firstPlayerTurn = fourthPlayerTurn;
    }


    // getter methods
    public boolean getFirstPlayerTurn() {
        return firstPlayerTurn;
    }

    public int getScore(){
        return score;
    }

    public boolean getSecondPlayerTurn() {
        return secondPlayerTurn;
    }

    public boolean getThirdPlayerTurn(){
        return thirdPlayerTurn;
    }

    public boolean getFourthPlayerTurn(){
        return fourthPlayerTurn;
    }



}
