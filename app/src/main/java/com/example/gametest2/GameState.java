package com.example.gametest2;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GameState {
    ArrayList<Character> tiles;
    //points per tile
    ArrayList<Character> lettersOfUserWord;
    //word they make
    ArrayList <Integer> numbers;
    //ArrayList <Integer> numbers;
    //maybe put into a hashable? might be easier to assign a letter to a tile
    //counter
    ArrayList<Character> lettersInHand;

    int score = 0;
    //state of resources

    //instance of tile class
    Tiles tiler = new Tiles();
    boolean spellCheck;
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

    //holder string for word
    //need to make this from a scanner

    GameState copy = new GameState();
// have to make this equal to each character in lettersOfUserWord combined
    boolean tilesVisible;
    boolean scoreboardVisible;
    boolean tilePlaced;
    int tileCountPlaced;
    ArrayList<String> wordMade;

    Paint tileToPlay;
// testing to see if it is a legitimate word

    public GameState(){

    }


    //players:
    public static final int player1Id = 1;
    public static final int player2Id = 2;
    public static final int player3Id = 3;
    public static final int player4Id = 4;

    public static final int ai_stupid_Id = 2;
    public static final int ai_smart_Id = 2;
    public boolean isTilePlaced(int player) {//checks if its placed
        tilePlaced = true;
        tileCountPlaced++;
        tiles.remove(tiler);
        return true;
    }
    public boolean isWordInHashtable(String word, int player) {
        if (tiler.getHashtable().containsKey(word)) {
            return true;//if its in the hashtable
        } else {
            return false;
        }
    }

    public ArrayList<Character> ShuffleLettersInHand(ArrayList<Character> replace, int player) {
        Collections.shuffle(replace);
        return replace;//move around thing-a-majig
    }
    //skipppppp
    public void skipPlayerTurn(ArrayList<String> players, int player) {
        player = (player + 1) % players.size();
    }//goes to next turn


    //action methods
    public boolean playTile(){
        tilePlaced = true;
        tileCountPlaced ++;

        //need to remove tile from the two arrays and attach character to wordMade
        return tilePlaced;
    }
    //checks if word is in the hashtable

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
