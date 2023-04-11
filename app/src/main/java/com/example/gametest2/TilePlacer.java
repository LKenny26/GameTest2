package com.example.gametest2;

import com.example.gametest2.views.Board;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class TilePlacer extends androidx.appcompat.widget.AppCompatImageView implements View.OnTouchListener {


    private int letterValue; // point value of the letter on the tile
    Board brd;
    private int x; // The x-coordinate of the tile on the board
    private int y; // The y-coordinate of the tile on the board

    public TilePlacer(int initx, int inity, Context context, AttributeSet attrs) {
        super(context, attrs);
        brd = new Board(context, attrs);
        x = initx;
        y = inity;

        setOnTouchListener(this);
    }

    public boolean onTouch(View v, MotionEvent event,Context context, AttributeSet attrs) {
        if (this.x == -1 || this.y == -1) {
            return false;
        }

        brd.placeTile(this,this.x,this.y,context,attrs);
        return true;
    }

    public void placeTileOnBoard(int x, int y) {
        this.x = x;
        this.y = y;
        //place tile on the board
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}