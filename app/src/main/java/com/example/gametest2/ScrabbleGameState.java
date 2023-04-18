package com.example.gametest2;

import android.graphics.Paint;

import androidx.annotation.NonNull;

import com.example.GameFramework.infoMessage.GameState;
import com.example.gametest2.views.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class ScrabbleGameState extends GameState {
    //instance variables
    private int playerID;
    private int playerOneScore;
    private int playerTwoScore;
    private int playerThreeScore;
    private int playerFourScore;
    Set<String> spellCheckTable;
    File dictionary;
    Scanner scan;
     //TODO: These in theory should have tiles rather than the board class, this way we can have access to them
    public Tile[] player1Tiles = new Tile[7];
    public Tile[] player2Tiles;
    public Tile[] player3Tiles;
    public Tile[] player4Tiles;
    public Tile[][] boardTiles;
    int player1TileCount = 7;
    int player2TileCount = 7;
    int player3TileCount = 7;
    int player4TileCount = 7;

    int TileCounter = 0;

    String wordMade;

    public static final int player1Id = 1;
    public static final int player2Id = 2;
    public static final int player3Id = 3;
    public static final int player4Id = 4;

    public ScrabbleGameState() {
        playerOneScore = 0;
        playerTwoScore = 0;
        playerThreeScore = 0;
        playerFourScore = 0;
        playerID = 1;

        /*dictionary = new File("words_alpha.txt");
        try {
            scan = new Scanner(dictionary);
        }
        catch (FileNotFoundException fnfe){
            throw new RuntimeException(fnfe);
        }

        while (scan.hasNextLine()){
            String word = scan.nextLine();

            if(word.length() > 1 && word.length() < 16) {
                spellCheckTable.add(word);
            }
        }*/
    }

    public ScrabbleGameState(ScrabbleGameState orig) {
        playerID = orig.playerID;
        playerOneScore = orig.playerOneScore;
        playerTwoScore = orig.playerTwoScore;
        playerThreeScore = orig.playerThreeScore;
        playerFourScore = orig.playerFourScore;

        /*
        dictionary = new File("words_alpha.txt");
        try {
            scan = new Scanner(dictionary);
        }
        catch (FileNotFoundException fnfe){
            throw new RuntimeException(fnfe);
        }

        while (scan.hasNextLine()){
            String word = scan.nextLine();

            if(word.length() > 1 && word.length() < 16) {
                spellCheckTable.add(word);
            }
        }
        */

        for (int i = 0; i < 7; i++) {
            Tile tile = new Tile(0,0,0,0,false);
            tile.setLetters();
            player1Tiles[i] = tile;
        }
    }

    public int getPlayerID(){return playerID;}

    public int getPlayerOneScore(){return playerOneScore;}

    public int getPlayerTwoScore(){return playerTwoScore;}

    public int getPlayerThreeScore(){return playerThreeScore;}

    public int getPlayerFourScore(){return playerFourScore;}

    public void setPlayerID(int id){playerID = id;}

    public void setPlayerOneScore(int p1score){playerOneScore = p1score;}

    public void setPlayerTwoScore(int p2score){playerTwoScore = p2score;}

    public void setPlayerThreeScore(int p3score){playerThreeScore = p3score;}

    public void setPlayerFourScore(int p4score){playerFourScore = p4score;}

    public int getPlayer1TileCount(){
        return player1TileCount;
    }

    public int getPlayer2TileCount(){
        return player2TileCount;
    }

    public int getPlayer3TileCount(){
        return player3TileCount;
    }

    public int getPlayer4TileCount(){
        return player4TileCount;
    }

}