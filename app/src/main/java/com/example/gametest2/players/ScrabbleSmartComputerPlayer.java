package com.example.gametest2.players;

import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameComputerPlayer;
import com.example.GameFramework.utilities.Logger;
import com.example.gametest2.ScrabbleGameState;
import com.example.gametest2.ScrabbleLocalGame;
import com.example.gametest2.Square;
import com.example.gametest2.Tile;
import com.example.gametest2.actions.PlayWordAction;
import com.example.gametest2.actions.SkipAction;
import com.example.gametest2.views.Board;
import com.example.gametest2.views.ScoreBoard;

import java.util.ArrayList;
import java.util.HashSet;

public class ScrabbleSmartComputerPlayer extends GameComputerPlayer {

    private ScrabbleGameState sgs;
    private Board bd;
    private ScoreBoard sb;
    private String message = "";

    public ScrabbleSmartComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof ScrabbleGameState) {
            //make sure the info is a game state
            sgs = (ScrabbleGameState) info;
        } else {
            return;
        }
        if (sgs.getPlayerID() == playerNum) {
            //make sure the ID is correct

            //variables
            int score = 0;
            boolean doubleWord = false;
            boolean tripleWord = false;
            int col;
            int row;
            String word = "";
            boolean realWord = false;
            HashSet<String> saver = ((ScrabbleLocalGame) game).getHash();

            //sleep for the comp
            sleep(2);
            message = "Computer is playing...";
            bd.setMessage(message);
            sleep(2);

            //loop through each tile the comp has and try to place it
            //if it makes a word going to the right play the word
            //otherwise skip turn
            //got help from libby
            ArrayList<String> across = new ArrayList<>();
            ArrayList<String> below = new ArrayList<>();
            for(int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    int Across;
                    int Down;
                    if (bd.boardTiles[i][j].getChar() != ' ') {
                        Across = SpacesAcross(i, j);
                        Down = SpacesBelow(i, j);

                        //ArrayList<String> possibilities = saver.stream().findAny("" + bd.boardTiles)
                    }
                }
            }

            for (int k = 0; k < 7; k++) {
                for (int i = 0; i < Board.BOARD_SIZE; i++) {
                    for (int j = 0; j < Board.BOARD_SIZE; j++) {
                        //set the variables to default
                        row = i;
                        col = j;
                        word = "";
                        score = 0;



                        //go through each space that has at least one tile to the right
                        if (col != Board.BOARD_SIZE - 1 && !bd.boardTiles[row][col + 1].getEmpty() && bd.boardTiles[row][col].getEmpty()) {
                            if (row > 0 && bd.boardTiles[row - 1][col].getEmpty()) {
                                if (row < Board.BOARD_SIZE - 1 && bd.boardTiles[row + 1][col].getEmpty()) {
                                    //start to make the word starting with the tile the comp has
                                    //make sure there is nothing above or below it
                                    word = "" + bd.computerTiles[k].getChar();
                                    int tempCol = col + 1;
                                    int tempScore = bd.computerTiles[k].getPoints();

                                    //check to see if the tile that would be placed is in a special square
                                    if (bd.squares[row][col].getType() == Square.DW) {
                                        doubleWord = true;
                                    }
                                    if (bd.squares[row][col].getType() == Square.TW) {
                                        tripleWord = true;
                                    }
                                    if (bd.squares[row][col].getType() == Square.DL) {
                                        tempScore = tempScore * 2;
                                    }
                                    if (bd.squares[row][col].getType() == Square.TL) {
                                        tempScore = tempScore * 2;
                                    }
                                    score = score + tempScore;

                                    while (tempCol != Board.BOARD_SIZE && !bd.boardTiles[row][tempCol].getEmpty()) {
                                        //go through all the way to the right to see if it is a word
                                        int tileScore = bd.boardTiles[row][tempCol].getPoints();

                                        //check for any specialties
                                        if (bd.squares[row][tempCol].getType() == Square.DL) {
                                            tileScore = tileScore * 2;
                                        }
                                        if (bd.squares[row][tempCol].getType() == Square.TL) {
                                            tileScore = tileScore * 3;
                                        }
                                        if (bd.squares[row][tempCol].getType() == Square.DW) {
                                            doubleWord = true;
                                        }
                                        if (bd.squares[row][tempCol].getType() == Square.TW) {
                                            tripleWord = true;
                                        }

                                        //set the score and the char
                                        score = score + tileScore;
                                        word = word + bd.boardTiles[row][tempCol].getChar();
                                        tempCol++;
                                    }


                                    //see if the word is a real word
                                    if (saver.contains(word.toLowerCase())) {
                                        //swap the tiles
                                        Tile.swap(bd.boardTiles[row][col], bd.computerTiles[k]);

                                        //get the correct score
                                        if (doubleWord) {
                                            score = score * 2;
                                        }
                                        if (tripleWord) {
                                            score = score * 3;
                                        }
                                        realWord = true;
                                        sb.setPlayerTwoScore(score);

                                        //go through the board and set the unconfirmed tiles to confirmed
                                        int newTiles = 0;
                                        for (int m = 0; m < Board.BOARD_SIZE; m++) {
                                            for (int o = 0; o < Board.BOARD_SIZE; o++) {
                                                if (!bd.boardTiles[m][o].getConfirmed() && !bd.boardTiles[m][o].getEmpty()) {
                                                    bd.boardTiles[m][o].setConfirmed(true);
                                                    newTiles++;
                                                }
                                            }
                                        }

                                        //go through to give comp new tiles
                                        int tilesLeft = 7;
                                        if (100 - bd.getTileCounter() < 7) {
                                            tilesLeft = tilesLeft - (100 - bd.getTileCounter());
                                        }
                                        for (int n = 0; n < tilesLeft; n++) {
                                            if (bd.computerTiles[n].getEmpty()) {
                                                bd.computerTiles[n] = new Tile(bd.computerTiles[n].getL(), bd.computerTiles[n].getT(), bd.computerTiles[n].getR(), bd.computerTiles[n].getB(), false);
                                            }
                                        }

                                        //send the action
                                        message = "Computer played " + word + " Points: " + score;
                                        bd.setMessage(message);
                                        bd.addToTileCounter(word.length());
                                        game.sendAction(new PlayWordAction(this, true, score, newTiles));
                                        return;
                                    }

                                }
                            }
                        }
                    }
                }
            }
            //if no word then skip turn
            message = "Computer skipped turn";
            bd.setMessage(message);
            game.sendAction(new SkipAction(this));

        }
    }
    public int SpacesAcross(int row, int col) {
        int count = 0;
        while (col+count+1 < 15 && row-1 > 0 && row+1 < 15 && bd.boardTiles[row][col+count+1].getChar() == ' ' && bd.boardTiles[row+1][col+count+1].getChar() == ' ' && bd.boardTiles[row-1][col+count+1].getChar() == ' ') {
            count++;
        }

        if (col-1 < 0 || bd.boardTiles[row][col-1].getChar() != ' ') {
            count = 0;
        }
        return count;
    }
    public int SpacesBelow(int row, int col) {
        int count = 0;
        while (row+count+1 < 15 && col+1 < 15 && col-1 > 0 && bd.boardTiles[row+count+1][col].getChar() == ' ' && bd.boardTiles[row+count+1][col+1].getChar() == ' ' && bd.boardTiles[row+count+1][col-1].getChar() == ' ') {
            count++;
        }
        if (row-1 < 0 || bd.boardTiles[row-1][col].getChar() != ' ') {
            count = 0;
        }
        return count;
    }
}