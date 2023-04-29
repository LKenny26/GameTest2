package com.example.gametest2.players;


import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;

import com.example.GameFramework.Game;
import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.LocalGame;
import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.GameFramework.players.GameComputerPlayer;
import com.example.GameFramework.utilities.Logger;
import com.example.gametest2.MainActivity;
import com.example.gametest2.R;
import com.example.gametest2.ScrabbleGameState;
import com.example.gametest2.ScrabbleLocalGame;
import com.example.gametest2.Square;
import com.example.gametest2.Tile;
import com.example.gametest2.actions.PlayWordAction;
import com.example.gametest2.actions.ScrabbleComputerAction;
import com.example.gametest2.actions.SkipAction;
import com.example.gametest2.views.Board;
import com.example.gametest2.views.ScoreBoard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ScrabbleComputerPlayer extends GameComputerPlayer {
    ScrabbleGameState sgs;
    ArrayList<Point> tilePoints = new ArrayList<>();
    Board bd;
    ScoreBoard sb;

    public ScrabbleComputerPlayer(String name) {

        super(name);

        //sgs = new ScrabbleGameState();
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof NotYourTurnInfo) {
            return;
        }

        Log.i("Comp", "my turn");
        if (info instanceof ScrabbleGameState) {
            //bd = ((ScrabbleGameState)super.game.getGameState()).getBoard();
            if (bd == null) {
                System.out.println("uh oh");
            }
            else {
                System.out.println("yay");
            }
        }


        sgs = (ScrabbleGameState)info;
        Log.i("debug", "computer is gonna take a turn");
        ArrayList<Tile> placeT = new ArrayList<Tile>();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (sgs.getPlayerID() == playerNum) {


            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (bd.boardTiles[i][j].getChar() != ' ') {
                        Tile place = null;
                        if (!(j + 1 > 14 || i + 1 > 14)) {
                            if (bd.boardTiles[i + 1][j].getChar() == ' ' && bd.boardTiles[i - 1][j].getChar() == ' ') {

                                if (place != null) {
                                    placeT.add(place);
                                    tilePoints.add(new Point(i + 1, j));
                                    game.sendAction(new ScrabbleComputerAction(this, placeT, tilePoints));
                                    return;
                                }

                                if (place != null) {
                                    placeT.add(place);
                                    tilePoints.add(new Point(i - 1, j));
                                    game.sendAction(new ScrabbleComputerAction(this, placeT, tilePoints));
                                    return;
                                } else if (bd.boardTiles[i][j + 1].getChar() == ' ' && bd.boardTiles[i][j - 1].getChar() == ' ') {


                                    if (place != null) {
                                        placeT.add(place);
                                        tilePoints.add(new Point(i, j - 1));
                                        game.sendAction(new ScrabbleComputerAction(this, placeT, tilePoints));
                                        return;
                                    }

                                    if (place != null) {
                                        placeT.add(place);
                                        tilePoints.add(new Point(i, j - 1));
                                        game.sendAction(new ScrabbleComputerAction(this, placeT, tilePoints));
                                        return;

                                    }
                                    game.sendAction(new PlayWordAction(this,false, sgs.getPlayerTwoScore()));
                                }
                            }

                        }
                        //if(sgs.boardTiles[i][j].getLetter() != ' '){


                    }
                }
                //game.sendAction(new SkipAction(this));
            }

        }



    }




    /*if(info instanceof NotYourTurnInfo) return;
    int x = (int)(10*Math.random());
    int y = (int)(10*Math.random());
    //dont need a try catch theres a sleep method
    sleep(1);
    ScrabbleGameState sgs = new ScrabbleGameState((ScrabbleGameState)info);

    /*if(sgs.getPlayerID() != this.playerNum){
        return;
    }//iterate through letter through l]
    //method for given board that gives all letters
    //6
    else{
        //needs to place a tile
        Logger.log("computer player", "sending move");
        for(int i = 1; i <= 3; i++) {
            //place tiles

            //spell-check:
            super.game.sendAction(new SkipAction(this));
        }
       // game.sendAction(new PlayWordAction(this, sgs.getPlayerID()));
        game.sendAction(new PlayWordAction(this, sgs.getPlayerID()));
    }
}

     */

    /*
    public List<Character> getLettersOnBoard(Tile[][] t, int row, int col, Tile[] Ti) {
        List<Character> letters = new ArrayList<>();
        for(char[]row:board){
            for(char cell : row){
                if(Character.isLetter(cell)){
                    letters.add(cell);
                }
            }
        }
        return letters;

     */

    public String getName(){
        return name;
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        bd = activity.findViewById(R.id.Board);
    }
}


