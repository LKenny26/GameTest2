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
    private boolean lastActionSkip = false;
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
        //TODO: bug that tiles that make multiple words can't be checked
        //this will not be fixed since the code would have to check each checked tile in every direction
        if(sgs.getPlayerID() != this.playerNum) {
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
            int startCol = -1;
            int startRow = -1;

            if (direction == up || direction == down) {
                while (row > 0 && !bd.boardTiles[row - 1][col].getEmpty()) {
                    //finds the up most tile of the word
                    row = row - 1;
                }
                startCol = col;
                startRow = row;

                //goes from up to down to get the words and the points
                while (row < Board.BOARD_SIZE && !bd.boardTiles[row][col].getEmpty()) {
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
                while(col > 0 && !bd.boardTiles[row][col -1].getEmpty()){
                    //go to the most left point of the word
                    col = col - 1;

                }
                startCol = col;
                startRow = row;

                //goes left to right to get the score and the word
                while (col < Board.BOARD_SIZE && !bd.boardTiles[row][col].getEmpty()){
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
                bd.setMessage(message);
                bd.invalidate();
                game.sendAction(new PlayWordAction(this, false, 0, 0));
                return;
            }

            //check to see if the word is in the hashset and therefore a real word
            if (saver.contains(word.toLowerCase())) {
                //this is a valid word
                Logger.log("TAG", "valid word!");
                int newTiles = 0;
                int confirmCol = startCol;
                int confirmRow = startRow;
                boolean confirmedCross = false;

                //check for one already confirmed letter in a horizontal word
                if (direction == right || direction == left) {
                    while(confirmCol != -1 && confirmCol < Board.BOARD_SIZE && !bd.boardTiles[confirmRow][confirmCol].getEmpty()) {
                        if(bd.boardTiles[confirmRow][confirmCol].getConfirmed()){
                            confirmedCross = true;
                        }
                        confirmCol++;
                    }
                }

                //check for one already confirmed letter in a vert word
                if(direction == up || direction == down){
                    while(confirmRow != -1 && confirmRow < Board.BOARD_SIZE && !bd.boardTiles[confirmRow][confirmCol].getEmpty()){
                        if(bd.boardTiles[confirmRow][confirmCol].getConfirmed()){
                            confirmedCross = true;
                        }
                        confirmRow++;
                    }
                }

                if(firstTurn || confirmedCross) {
                    //go to the left of the word that the player played and confirm it
                    if (direction == right || direction == left) {
                        while(startCol != -1 && startCol < Board.BOARD_SIZE && !bd.boardTiles[startRow][startCol].getEmpty()) {
                            bd.boardTiles[startRow][startCol].setConfirmed(true);
                            startCol++;
                        }
                    }

                    //if the word is vertical go down until the whole word is confirmed
                    if(direction == up || direction == down){
                        while(startRow != -1 && startRow < Board.BOARD_SIZE && !bd.boardTiles[startRow][startCol].getEmpty()){
                            bd.boardTiles[startRow][startCol].setConfirmed(true);
                            startRow++;
                        }
                    }

                    //take away the unconfirmed tiles
                    for (int i = 0; i < Board.BOARD_SIZE; i++) {
                        for (int j = 0; j < Board.BOARD_SIZE; j++) {
                            if (!bd.boardTiles[i][j].getConfirmed()) {
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


                    //go through the players tiles to give them new tiles
                    int tilesLeft = 7;
                    if (100 - bd.getTileCounter() < 7) {
                        tilesLeft = tilesLeft - (100 - bd.getTileCounter());
                    }
                    for (int i = 0; i < tilesLeft; i++) {
                        if (bd.playerTiles[i].getEmpty()) {
                            bd.playerTiles[i] = new Tile(bd.playerTiles[i].getL(), bd.playerTiles[i].getT(), bd.playerTiles[i].getR(), bd.playerTiles[i].getB(), false);
                        }
                    }

                    //checks to see if the word specialties are played upon
                    if (doubleWord) {
                        score = score * 2;
                    }
                    if (tripleWord) {
                        score = score * 3;
                    }
                    firstTurn = false;
                    lastActionSkip = false;
                    bd.addToTileCounter(word.length());
                    message = "Valid move! Word Played: " + word + " Points: " + score;
                    bd.setMessage(message);
                    //set the score for the scoreboard
                    sb.setPlay1Score(score);
                    //redraw the board
                    bd.invalidate();
                    //redraw the scoreboard
                    sb.invalidate();
                    //send the action
                    game.sendAction(new PlayWordAction(this, true, score, newTiles));
                }
                else {
                    message = "Error: The word does not cross an already placed tile";
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
                    bd.setMessage(message);
                    bd.invalidate();
                    game.sendAction(new PlayWordAction(this, false, 0, 0));
                }
            } else {
                //word does not exist or is invalid
                Logger.log("TAG", "invalid word");
                message = "This is not a valid word, try again";

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
                bd.setMessage(message);
                bd.invalidate();

                //set the score to zero since no word was played
                score = 0;
                game.sendAction(new PlayWordAction(this, false, score, 0));
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
            message = "Skipped Player One's turn";

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
            boolean skippedTwice = false;
            if(lastActionSkip) {
                skippedTwice = true;
            }
            lastActionSkip = true;

            //redraw
            bd.setMessage(message);
            bd.invalidate();
            game.sendAction(new SkipAction(this, skippedTwice));
        } else if (button.getId() == R.id.spellcheck) {
            int row = -1;
            int col = -1;
            int direction = -1; //1 up, 2 down, 3 left, 4 right, -1 error
            String word = "";

            //go through the board array
            for (int i = 0; i < Board.BOARD_SIZE; i++) {
                for (int j = 0; j < Board.BOARD_SIZE; j++) {
                    //check the tiles around it to see where the word goes
                    if (!bd.boardTiles[i][j].getConfirmed() && !bd.boardTiles[i][j].getEmpty()) {
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
                while (!bd.boardTiles[row][col].getEmpty() && row > 0) {
                    //go all the way up of the word
                    row = row - 1;
                }

                while (!bd.boardTiles[row][col].getEmpty() && row < Board.BOARD_SIZE - 1) {
                    //go all the way down and set the word
                    word = word + bd.boardTiles[row][col].getChar();
                    row = row + 1;
                }

            }

            if (direction == right || direction == left) {
                while(!bd.boardTiles[row][col].getEmpty() && col > 0){
                    //go all the way left until you cant
                    col = col - 1;
                }
                while (!bd.boardTiles[row][col].getEmpty() && col < Board.BOARD_SIZE - 1){
                    //go all the way right until you can't to set the word
                    word = word + bd.boardTiles[row][col].getChar();
                    col = col + 1;
                }
            }
            System.out.println(word + " is the word found");
            HashSet<String> saver = ((ScrabbleLocalGame) game).getHash();
            if (saver.contains(word.toLowerCase())) {
                Logger.log("TAG", "valid word!");
                message = "This is a valid word!";
                bd.setMessage(message);
            } else {
                Logger.log("TAG", "invalid word");
                message = "This is not a valid word.";
                bd.setMessage(message);
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

            if(sgs.getPlayerID() == playerNum){
                sb.setPlayerID(0);
                bd.setPlayerID(0);
                sb.invalidate();
            }
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
