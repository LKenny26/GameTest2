package com.example.gametest2;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import androidx.annotation.NonNull;

public class gameState{

    public gameState(){}
    ArrayList<Character> tiles;
    //points per tile
    ArrayList<Character> lettersOfUserWord;
    //word they make
    ArrayList<Integer> numbers;
    //maybe put into a hashable? might be easier to assign a letter to a tile
    //counter
    ArrayList<Character> lettersInHand;

    //instance of tile class
    Tiles tiler = new Tiles();
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
    Paint board[][];
    //players cant see another players letter
    boolean tilesVisible;
    boolean scoreboardVisible;
    boolean tilePlaced;
    int tileCountPlaced;

    String wordMade;

    int playerId;

    Paint tileToPlay;
    //players:
    public static final int player1Id = 1;
    public static final int player2Id = 2;
    public static final int player3Id = 3;
    public static final int player4Id = 4;

    public static final int ai_stupid_Id = 2;
    public static final int ai_smart_Id = 2;


    public boolean isTilePlaced() {
        tilePlaced = true;
        tileCountPlaced++;
        tiles.remove(tiler);
        return true;
    }
    public void closeApp(int player) {//closing the app
        System.exit(0);
    }
    //ends turn and goes to next if word is valid
    public void PlayAWord(ArrayList<String> players,String word,int player){
        if(isWordInHashtable(word,player)==true){
            player = (player + 1) % players.size();
        }
        else{
            player = player;
        }
    }
    public boolean isWordInHashtable(String word, int player) {
        return tiler.getHashtable().containsKey(word);
    }

    //skipppppp
    public void skipPlayerTurn(ArrayList<String> players, int player) {
        player = (player + 1) % players.size();
    }//goes to next turn


    public ArrayList<Character> ShuffleLettersInHand(ArrayList<Character> replace, int player) {
        Collections.shuffle(replace);
        return replace;
    }

    //setter methods

    //public int setScore(){

    //}



    public boolean playTile() {
        tilePlaced = true;
        tileCountPlaced++;
        return tilePlaced;
    }


    public boolean spelling(){
        spellCheck = gameModel.spellCheckTable.contains(wordMade);
        return spellCheck;
    }

    public GameView gameView;
    public GameModel gameModel;


    //constructor
    public gameState(GameView gv) {
        gameView = gv;
        gameModel = gv.getGameModel();
        score = 0;
        firstPlayerTurn = true;
        aiTurn = false;
        secondPlayerTurn = false;
        thirdPlayerTurn = false;
        fourthPlayerTurn = false;
        playerVisible = true;
        board = new Paint[15][15];

    }
    public gameState(gameState gs){
        this.spellCheck = gs.spellCheck;
        this.tilesVisible = gs.tilesVisible;
        this.scoreboardVisible = gs.scoreboardVisible;
        this.firstPlayerTurn = gs.firstPlayerTurn;
        this.aiTurn = gs.aiTurn;
        this.score = gs.score;
        this.secondPlayerTurn = gs.secondPlayerTurn;
        this.thirdPlayerTurn = gs.thirdPlayerTurn;
        this.fourthPlayerTurn = gs.fourthPlayerTurn;

    }

    /*public GameState(int score, boolean spellCheck, boolean tilesVisible, boolean initfirstPlayerTurn, boolean aiTurn, boolean
            secondPlayerTurn, boolean thirdPlayerTurn, boolean fourthPlayerTurn, boolean scoreboardVisible, GameView gv) {
        this.spellCheck = spellCheck;
        this.tilesVisible = tilesVisible;
        this.scoreboardVisible = scoreboardVisible;
        this.firstPlayerTurn = initfirstPlayerTurn;
        this.aiTurn = aiTurn;
        this.score = score;
        this.secondPlayerTurn = secondPlayerTurn;
        this.thirdPlayerTurn = thirdPlayerTurn;
        this.fourthPlayerTurn = fourthPlayerTurn;
        this.aiTurn = aiTurn;
        gameView = gv;
        gameModel = gv.getGameModel();
    }
*/







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
    public boolean getAiTurn(){
        return fourthPlayerTurn;
    }

    public void setPlayerId(int init){
        playerId = init;
    }
    public int getPlayerId(){
        return playerId;
    }

    @NonNull
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


