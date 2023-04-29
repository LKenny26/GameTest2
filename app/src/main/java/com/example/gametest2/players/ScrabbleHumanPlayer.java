package com.example.gametest2.players;

import android.icu.text.Edits;
import android.view.View;
import android.widget.Button;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.GameFramework.players.GameHumanPlayer;
import com.example.GameFramework.utilities.Logger;
import com.example.gametest2.MainActivity;
import com.example.gametest2.R;
import com.example.gametest2.ScrabbleGameState;
import com.example.gametest2.ScrabbleLocalGame;
import com.example.gametest2.Square;
import com.example.gametest2.Tile;
import com.example.gametest2.actions.*;
import com.example.gametest2.views.Board;
import com.example.gametest2.views.ScoreBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class ScrabbleHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    private static final String TAG = "ScrabbleHumanPlayer1";

    public static final int up = 1;
    public static final int down = 2;
    public static final int right = 3;
    public static final int left = 4;
    private boolean firstTurn = true;
    private boolean center = false;
    private String message = "";

    //buttons
    private Button playword = null;
    private Button shuffle = null;
    private Button removeTiles = null;
    private Button skip = null;
    private Button spellcheck = null;

    private Board bd;
    private ScoreBoard sb;
    private ScrabbleGameState sgs;

    public ScrabbleHumanPlayer(String name) {super(name); }

    @Override
    public void onClick(View button) {
        SkipAction ska = new SkipAction(this);
        sgs.setBoard(bd);
        if(sgs.getPlayerID() != this.playerNum) {
            //TODO: make sure that a player cannot swap pieces (the onTouch in board prob)
            return;
        }

        if (button.getId() == R.id.playword) {
            int row = -1;
            int col = -1;
            int direction = -1; //1 up, 2 down, 3 left, 4 right, -1 error
            int score = 0;
            boolean doubleWord = false;
            boolean tripleWord = false;
            String word = "";

            //go through the board and find an unconfirmed tile and not empty tile
            for (int i = 0; i < Board.BOARD_SIZE; i++) {
                for (int j = 0; j < Board.BOARD_SIZE; j++) {
                    if (!bd.boardTiles[i][j].getConfirmed() && !bd.boardTiles[i][j].getEmpty()) {
                        //set the row and col of the tile found
                        row = i;
                        col = j;

                        //check each of the directions to see which way the word goes
                        if (i > 0 && !bd.boardTiles[i - 1][j].getEmpty()) {
                            //up
                            direction = up;
                        } else if (i < Board.BOARD_SIZE - 1 && !bd.boardTiles[i + 1][j].getEmpty()) {
                            direction = down;
                        } else if (j > 0 && !bd.boardTiles[i][j - 1].getEmpty()) {
                            direction = left;
                        } else if (j < Board.BOARD_SIZE - 1 && !bd.boardTiles[i][j + 1].getEmpty()) {
                            direction = right;
                        }
                        //break after a direction is found
                        break;
                    }
                }
            }

            if (direction == up || direction == down) {
                while (!bd.boardTiles[row - 1][col].getEmpty() || row < 0) {
                    //finds the up most tile of the word
                    row = row - 1;
                }

                //goes from up to down to get the words and the points
                while (!bd.boardTiles[row][col].getEmpty() || row > Board.BOARD_SIZE) {
                    if (firstTurn && bd.squares[row][col].getType() == Square.STAR){
                        center = true;
                    }
                    word = word + bd.boardTiles[row][col].getChar();
                    int tileScore = 0;
                    tileScore = tileScore + bd.boardTiles[row][col].getPoints();

                    //checking if there is a special square
                    if (bd.squares[row][col].getType() == Square.DL) {
                        tileScore = tileScore * 2;
                    }
                    if (bd.squares[row][col].getType() == Square.TL) {
                        tileScore = tileScore * 3;
                    }
                    if (bd.squares[row][col].getType() == Square.TW) {
                        tripleWord = true;
                    }
                    if (bd.squares[row][col].getType() == Square.DW) {
                        doubleWord = true;
                    }

                    //update the score with the current score + tile's score
                    score = score + tileScore;
                    row = row + 1;
                }

            }

            if (direction == right || direction == left) {
                while(!bd.boardTiles[row][col -1].getEmpty() || col < 0){
                    //go to the most left point of the word
                    col = col - 1;
                }

                //goes left to right to get the score and the word
                while (!bd.boardTiles[row][col].getEmpty() || col > Board.BOARD_SIZE){
                    if (firstTurn && bd.squares[row][col].getType() == Square.STAR){
                        center = true;
                    }
                    word = word + bd.boardTiles[row][col].getChar();
                    int tileScore = 0;
                    tileScore = tileScore + bd.boardTiles[row][col].getPoints();

                    //checks if there are any special spaces
                    if (bd.squares[row][col].getType() == Square.DL) {
                        tileScore = tileScore * 2;
                    }
                    if (bd.squares[row][col].getType() == Square.TL) {
                        tileScore = tileScore * 3;
                    }
                    if (bd.squares[row][col].getType() == Square.TW) {
                        tripleWord = true;
                    }
                    if (bd.squares[row][col].getType() == Square.DW) {
                        doubleWord = true;
                    }

                    //update score with the appropriate tile score
                    score = score + tileScore;
                    col = col + 1;
                }
            }

            //get the hashset
            HashSet<String> saver = ((ScrabbleLocalGame) game).getHash();
            if(firstTurn && !center){
                message = "Error first word has to be placed in the center!";
                for(int i = 0; i < Board.BOARD_SIZE; i++) {
                    for (int j = 0; j < Board.BOARD_SIZE; j++) {
                        //iterate through the rows and cols
                        if (!bd.boardTiles[i][j].getConfirmed()) {
                            //check if a tile is confirmed or not
                            Tile temp = null;
                            for (int k = 0; k < 7; k++) {
                                //find the tile that is empty in a player's hand
                                if (bd.playerTiles[k].getEmpty()) {
                                    temp = bd.playerTiles[k];
                                }
                            }
                            //if a tile that is empty is found swap it with an unconfirmed tile
                            if (temp != null) {
                                Tile.swap(bd.boardTiles[i][j], temp);
                            }
                        }
                    }
                }
                bd.invalidate();
                game.sendAction(new PlayWordAction(this, false, 0));
                return;
            }
            //check to see if the word is in the hashset and therefore a real word
            if (saver.contains(word.toLowerCase())) {
                //this is a valid word
                Logger.log("TAG", "valid word!");

                //go through the board and set the unconfirmed tiles to confirmed
                for(int i = 0; i < Board.BOARD_SIZE; i++) {
                    for(int j = 0; j < Board.BOARD_SIZE; j++){
                        if (!bd.boardTiles[i][j].getConfirmed() && !bd.boardTiles[i][j].getEmpty()) {
                            bd.boardTiles[i][j].setConfirmed(true);
                        }
                    }
                }

                //go through the players tiles to give them new tiles
                for(int i = 0; i < 7; i++) {
                    if (bd.playerTiles[i].getEmpty()){
                        bd.playerTiles[i] = new Tile(bd.playerTiles[i].getL(), bd.playerTiles[i].getT(), bd.playerTiles[i].getR(), bd.playerTiles[i].getB(), false);
                    }
                }

                //redraw board
                bd.invalidate();

                //checks to see if the word specialties are played upon
                if (doubleWord) {
                    score = score * 2;
                }
                if (tripleWord) {
                    score = score * 3;
                }

                message = "Valid move! Word Played: " + word + " Points: " + score;
                //set the score for the scoreboard
                sb.setPlay1Score(score);
                //redraw the board
                sb.invalidate();
                //send the action
                game.sendAction(new PlayWordAction(this, true, score));
            } else {
                //word does not exist or is invalid
                Logger.log("TAG", "invalid word");
                //remove tiles from the board and puts them back into player's tiles
                for(int i = 0; i < Board.BOARD_SIZE; i++) {
                    for (int j = 0; j < Board.BOARD_SIZE; j++) {
                        //iterate through the rows and cols
                        if (!bd.boardTiles[i][j].getConfirmed()) {
                            //check if a tile is confirmed or not
                            Tile temp = null;
                            for (int k = 0; k < 7; k++) {
                                //find the tile that is empty in a player's hand
                                if (bd.playerTiles[k].getEmpty()) {
                                    temp = bd.playerTiles[k];
                                }
                            }
                            //if a tile that is empty is found swap it with an unconfirmed tile
                            if (temp != null) {
                                Tile.swap(bd.boardTiles[i][j], temp);
                            }
                        }
                    }
                }
                //redraw
                bd.invalidate();
                //set the score to zero since no word was played
                score = 0;
                game.sendAction(new PlayWordAction(this, false, score));
            }
        } else if (button.getId() == R.id.shuffle) {
            //make random to shuffle tiles
            Random rand = new Random();

            //iterate through the player tiles and swap
            for (int i = 0; i < 7; i++) {
                Tile.swap(bd.playerTiles[i], bd.playerTiles[rand.nextInt(7)]);
            }
            //redraw
            bd.invalidate();

        } else if (button.getId() == R.id.skip) {
            //remove tiles from the board and puts them back into player's tiles
            for(int i = 0; i < Board.BOARD_SIZE; i++) {
                for (int j = 0; j < Board.BOARD_SIZE; j++) {
                    //iterate through the rows and cols
                    if (!bd.boardTiles[i][j].getConfirmed()) {
                        //check if a tile is confirmed or not
                        Tile temp = null;
                        for (int k = 0; k < 7; k++) {
                            //find the tile that is empty in a player's hand
                            if (bd.playerTiles[k].getEmpty()) {
                                temp = bd.playerTiles[k];
                            }
                        }
                        //if a tile that is empty is found swap it with an unconfirmed tile
                        if (temp != null) {
                            Tile.swap(bd.boardTiles[i][j], temp);
                        }
                    }
                }
            }
            //redraw
            bd.invalidate();
            game.sendAction(ska);
        } else if (button.getId() == R.id.spellcheck) {
            int row = -1;
            int col = -1;
            int direction = -1; //1 up, 2 down, 3 left, 4 right, -1 error
            String word = "";

            //go through the board array
            for (int i = 0; i < Board.BOARD_SIZE; i++) {
                for (int j = 0; j < Board.BOARD_SIZE; j++) {
                    //check the tiles around it to see where the word goes
                    if (bd.boardTiles[i][j].getConfirmed() == false && !bd.boardTiles[i][j].getEmpty()) {
                        //get the row and the col of the tile found
                        row = i;
                        col = j;
                        if (i > 0 && !bd.boardTiles[i - 1][j].getEmpty()) {
                            direction = up;
                        } else if (i < Board.BOARD_SIZE - 1 && !bd.boardTiles[i + 1][j].getEmpty()) {
                            direction = down;
                        } else if (j > 0 && !bd.boardTiles[i][j - 1].getEmpty()) {
                            direction = right;
                        } else if (j < Board.BOARD_SIZE - 1 && !bd.boardTiles[i][j + 1].getEmpty()) {
                            direction = left;
                        }
                        break;
                    }
                }
            }

            if (direction == up || direction == down) {
                while (!bd.boardTiles[row - 1][col].getEmpty() || row < 0) {
                    //go all the way up of the word
                    row = row - 1;
                }

                while (!bd.boardTiles[row][col].getEmpty() || row > Board.BOARD_SIZE) {
                    //go all the way down and set the word
                    word = word + bd.boardTiles[row][col].getChar();
                    row = row + 1;
                }

            }

            if (direction == right || direction == left) {
                while(!bd.boardTiles[row][col -1].getEmpty() || col < 0){
                    //go all the way left until you cant
                    col = col - 1;
                }
                while (!bd.boardTiles[row][col].getEmpty() || col > Board.BOARD_SIZE){
                    //go all the way right until you can't to set the word
                    word = word + bd.boardTiles[row][col].getChar();
                    col = col + 1;
                }
            }

            //TODO: make something happen if it is valid
            System.out.println(word + " is the word found");
            HashSet<String> saver = ((ScrabbleLocalGame) game).getHash();
            if (saver.contains(word.toLowerCase())) {
                Logger.log("TAG", "valid word!");
            } else {
                Logger.log("TAG", "invalid word");
            }
        }
        else if(button.getId() == R.id.removeTiles){
            //remove tiles from the board and puts them back into player's tiles
            for(int i = 0; i < Board.BOARD_SIZE; i++) {
                for (int j = 0; j < Board.BOARD_SIZE; j++) {
                    //iterate through the rows and cols
                    if (!bd.boardTiles[i][j].getConfirmed()) {
                        //check if a tile is confirmed or not
                        Tile temp = null;
                        for (int k = 0; k < 7; k++) {
                            //find the tile that is empty in a player's hand
                            if (bd.playerTiles[k].getEmpty()) {
                                temp = bd.playerTiles[k];
                            }
                        }
                        //if a tile that is empty is found swap it with an unconfirmed tile
                        if (temp != null) {
                            Tile.swap(bd.boardTiles[i][j], temp);
                        }
                    }
                }
            }
            //redraw
            bd.invalidate();
        }

    }

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.human_player_view);
    }

    @Override
    public void receiveInfo(GameInfo info) {
        //dr libby helped here
        if(info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo){
            //bd.flash(Color.RED, 50);
            Logger.log(TAG, "wrong move");
        }
        else if(!(info instanceof ScrabbleGameState)){
            return;
        }
        else {
            bd.setState((ScrabbleGameState) info);
            bd.invalidate();
            sgs = (ScrabbleGameState) info;
            Logger.log(TAG, "receiving");
        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        myActivity = activity;
        activity.setContentView(R.layout.human_player_view);

        this.playword = (Button)activity.findViewById(R.id.playword);
        System.out.println("play word" + playword.getId() + " " + R.id.playword);
        this.shuffle = (Button)activity.findViewById(R.id.shuffle);
        System.out.println("play word" + shuffle.getId() + " " + R.id.shuffle);
        this.removeTiles = (Button)activity.findViewById(R.id.removeTiles);
        System.out.println("play word" + removeTiles.getId() + " " + R.id.removeTiles);
        this.skip = (Button)activity.findViewById(R.id.skip);
        System.out.println("play word" + skip.getId() + " " + R.id.skip);
        this.spellcheck = (Button)activity.findViewById(R.id.spellcheck);
        System.out.println("play word" + spellcheck.getId() + " " + R.id.spellcheck);

        playword.setOnClickListener(this);
        shuffle.setOnClickListener(this);
        removeTiles.setOnClickListener(this);
        skip.setOnClickListener(this);
        spellcheck.setOnClickListener(this);

        bd = (Board)myActivity.findViewById(R.id.Board);
        sb = (ScoreBoard)myActivity.findViewById(R.id.ScoreBoard);
        Logger.log("setting listeners", "onClick");
    }


    public String getName(){
        return name;
    }
}
