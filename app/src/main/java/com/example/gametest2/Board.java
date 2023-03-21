package com.example.gametest2;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;

public class Board extends GameView{
    //here is the board class for the basic-basic make of the game
    Paint red = new Paint();
    Paint blue = new Paint();
    Paint black = new Paint();
    Paint white = new Paint();
    Paint dBlue = new Paint();
    Paint pink = new Paint();

    gameState gs;
    GameModel gm;
    public Board(Context context, AttributeSet attrs){
        super(context, attrs);
        gs = new gameState();
        gm = new GameModel();

        red.setColor(0xFFFF0000);

    }


}

