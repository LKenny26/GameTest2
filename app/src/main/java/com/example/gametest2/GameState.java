package com.example.gametest2;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameState {
    //ArrayList<Character> tiles;
    //points per tile
    //ArrayList <Integer> numbers;
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
    public boolean playTile(){
        tilePlaced = true;
        tileCountPlaced ++;
        //need to remove tile from the two arrays and attach character to wordMade
        return tilePlaced;
    }

    public boolean spelling(){
        //need to complete
        Set<String> dictionary = new HashSet<>();
        if(dictionary.contains(wordMade)){
            spellCheck = true;
        }
        return spellCheck;
    }

    @Override
    public String toString() {
        String gameStateString;
        gameStateString = "Score: " + score + "\n";
        gameStateString = gameStateString + "Spell Check Worked? " + Boolean.toString(spellCheck) + "\n";
        gameStateString = gameStateString + "First Player Turn? " + Boolean.toString(firstPlayerTurn) + "\n";
        gameStateString = gameStateString + "Second Player Turn? " + Boolean.toString(secondPlayerTurn) + "\n";
        gameStateString = gameStateString + "Third Player Turn? " + Boolean.toString(thirdPlayerTurn) + "\n";
        gameStateString = gameStateString + "Fourth Player Turn? " + Boolean.toString(fourthPlayerTurn) + "\n";
        gameStateString = gameStateString + "AI Player Turn? " + Boolean.toString(aiTurn) + "\n";
        gameStateString = gameStateString + "Correct Player Playing? " + Boolean.toString(playerVisible) + "\n";
        gameStateString = gameStateString + "Was a tile placed? " + Boolean.toString(tilePlaced) + "\n";
        gameStateString = gameStateString + "How many tiles placed? " + tileCountPlaced + "\n";

        return gameStateString;
    }
}
