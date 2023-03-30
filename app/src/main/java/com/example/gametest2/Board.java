package com.example.gametest2;

import android.graphics.Color;

public class Board{
    //here is the board class for the basic-basic make of the game
    //dimensions: 15x15
    gameState gs = new gameState();

}
    Paint red = new Paint();
    Paint lblue = new Paint();
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
        red.setStyle(Paint.Style.FILL);
        lblue.setColor(0xFF1FBED6);
        lblue.setStyle(Paint.Style.FILL);
        black.setColor(0xFF000000);
        black.setStyle(Paint.Style.FILL);
        white.setColor(0xFFFFFFFF);
        white.setStyle(Paint.Style.FILL);
        dBlue.setColor(0xFF84B1D0);
        dBlue.setStyle(Paint.Style.FILL);
        pink.setColor(0xFFFF1493);
        pink.setStyle(Paint.Style.FILL);

    }


}

