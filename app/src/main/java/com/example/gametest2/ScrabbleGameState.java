package com.example.gametest2;

import android.graphics.Paint;

import androidx.annotation.NonNull;

import com.example.GameFramework.infoMessage.GameState;
import com.example.gametest2.views.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class ScrabbleGameState extends GameState {
    //instance variables
    private int playerID;
    private int playerOneScore;
    private int playerTwoScore;

    private int tileCounter = 0;

    public ScrabbleGameState() {
        playerOneScore = 0;
        playerTwoScore = 0;
        playerID = 0;
    }

    public ScrabbleGameState(ScrabbleGameState orig) {
        playerID = orig.playerID;
        playerOneScore = orig.playerOneScore;
        playerTwoScore = orig.playerTwoScore;
        tileCounter = orig.tileCounter;
    }
    public int getPlayerID(){return playerID;}

    public int getPlayerOneScore(){return playerOneScore;}

    public int getPlayerTwoScore(){return playerTwoScore;}

    public void setPlayerID(int id){playerID = id;}

    public void setPlayerOneScore(int p1score){playerOneScore = p1score;}

    public void setPlayerTwoScore(int p2score){playerTwoScore = p2score;}

    public int getTileCounter() {return tileCounter;}
    public void addToTileCounter(int add) {
        tileCounter = tileCounter + add;
    }
}