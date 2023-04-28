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
import com.example.gametest2.Tile;
import com.example.gametest2.actions.*;
import com.example.gametest2.views.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class ScrabbleHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    private static final String TAG = "ScrabbleHumanPlayer1";
    private Button playword = null;
    private Button shuffle = null;
    private Button removeTiles = null;
    private Button skip = null;
    private Button spellcheck = null;

    private Board bd;
    //Tile t;
    private MainActivity myA = new MainActivity();

    public ScrabbleHumanPlayer(String name) {super(name); }

    @Override
    public void onClick(View button) {
        //int x = button.getId();
        //PlayWordAction pwa; //new PlayWordAction(this);
        //t = new Tile();

        ScrabbleGameState sgs = new ScrabbleGameState();
        //PlayWordAction pwa = new PlayWordAction(this);
        //ShuffleAction sha = new ShuffleAction(this);
        SkipAction ska = new SkipAction(this);
        //SpellCheckAction sca = new SpellCheckAction(this);
        //we need an onject has a row collumn and a letter , get the letter and implementcomparable  compareTO ,
       // Compare to method ,
               // when tile is placed
        //RemoveTilesAction rta = new RemoveTilesAction(this);

        if (button.getId() == R.id.playword) {
            //pwa = new PlayWordAction(this, sgs.getPlayerID());
            //bd.getAR().clear();
            //bd.getSB().setLength(0);
            System.out.println("Made it to play word");
            int row = -1;
            int col = -1;
            int direction = -1; //1 up, 2 down, 3 left, 4 right, -1 error
            String word = "";
            for (int i = 0; i < Board.BOARD_SIZE; i++) {
                for (int j = 0; j < Board.BOARD_SIZE; j++) {
                    //System.out.println(i + ", " + j);
                    if (!bd.boardTiles[i][j].getConfirmed() && !bd.boardTiles[i][j].getEmpty()) {
                        //System.out.println(i + ", " + j + " is not confirmed aka just placed");
                        row = i;
                        col = j;
                        if (i > 0 && !bd.boardTiles[i - 1][j].getEmpty()) {
                            direction = 1;
                        } else if (i < Board.BOARD_SIZE - 1 && !bd.boardTiles[i + 1][j].getEmpty()) {
                            direction = 2;
                        } else if (j > 0 && !bd.boardTiles[i][j - 1].getEmpty()) {
                            direction = 3;
                        } else if (j < Board.BOARD_SIZE - 1 && !bd.boardTiles[i][j + 1].getEmpty()) {
                            direction = 4;
                        } else {
                            //return;
                        }
                        break;
                    }
                }
            }
            System.out.println(row + ", " + col + ", " + direction);
            if (direction == 1 || direction == 2) {
                while (!bd.boardTiles[row - 1][col].getEmpty() || row < 0) {
                    row = row - 1;
                }

                while (!bd.boardTiles[row][col].getEmpty() || row > Board.BOARD_SIZE) {
                    word = word + bd.boardTiles[row][col].getChar();
                    row = row + 1;
            }

            }
            if (direction == 3 || direction == 4) {
                while(!bd.boardTiles[row][col -1].getEmpty() || col < 0){
                    col = col - 1;
                }
                while (!bd.boardTiles[row][col].getEmpty() || col > Board.BOARD_SIZE){
                    word = word + bd.boardTiles[row][col].getChar();
                    col = col + 1;
                }
            }
            System.out.println(word + " is the word found");
            HashSet<String> saver = ((ScrabbleLocalGame) game).getHash();
            if (saver.contains(word.toLowerCase())) {
                Logger.log("TAG", "valid word!");
                for(int i = 0; i < Board.BOARD_SIZE; i++) {
                    for(int j = 0; j < Board.BOARD_SIZE; j++){
                        if (!bd.boardTiles[i][j].getConfirmed() && !bd.boardTiles[i][j].getEmpty()) {
                            bd.boardTiles[i][j].setConfirmed(true);
                        }
                    }
                }
                for(int i = 0; i < 7; i++) {
                    if (bd.playerTiles[i].getEmpty()){
                        bd.playerTiles[i] = new Tile(bd.playerTiles[i].getL(), bd.playerTiles[i].getT(), bd.playerTiles[i].getR(), bd.playerTiles[i].getB(), false);
                    }
                }
                bd.invalidate();
                game.sendAction(new PlayWordAction(this, true));
            } else {
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
                game.sendAction(new PlayWordAction(this, false));
            }
            //game.sendAction(pwa);
            //bd.invalidate();
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

            System.out.println("Made it to spell check");
            int row = -1;
            int col = -1;
            int direction = -1; //1 up, 2 down, 3 left, 4 right, -1 error
            String word = "";
            for (int i = 0; i < Board.BOARD_SIZE; i++) {
                for (int j = 0; j < Board.BOARD_SIZE; j++) {
                    //System.out.println(i + ", " + j);
                    if (bd.boardTiles[i][j].getConfirmed() == false && !bd.boardTiles[i][j].getEmpty()) {
                        //System.out.println(i + ", " + j + " is not confirmed aka just placed");
                        row = i;
                        col = j;
                        if (i > 0 && !bd.boardTiles[i - 1][j].getEmpty()) {
                            direction = 1;
                        } else if (i < Board.BOARD_SIZE && !bd.boardTiles[i + 1][j].getEmpty()) {
                            direction = 2;
                        } else if (j > 0 && !bd.boardTiles[i][j - 1].getEmpty()) {
                            direction = 3;
                        } else if (j < Board.BOARD_SIZE && !bd.boardTiles[i][j + 1].getEmpty()) {
                            direction = 4;
                        } else {
                            //return;
                        }
                        break;
                    }
                }
            }
            System.out.println(row + ", " + col + ", " + direction);
            if (direction == 1 || direction == 2) {
                while (!bd.boardTiles[row - 1][col].getEmpty() || row < 0) {
                    row = row - 1;
                }

                while (!bd.boardTiles[row][col].getEmpty() || row > Board.BOARD_SIZE) {
                    word = word + bd.boardTiles[row][col].getChar();
                    row = row + 1;
                }

            }
            if (direction == 3 || direction == 4) {
                while(!bd.boardTiles[row][col -1].getEmpty() || col < 0){
                    col = col - 1;
                }
                while (!bd.boardTiles[row][col].getEmpty() || col > Board.BOARD_SIZE){
                    word = word + bd.boardTiles[row][col].getChar();
                    col = col + 1;
                }
            }
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
        Logger.log("setting listeners", "onClick");
    }

    public String getName(){
        return name;
    }
}
