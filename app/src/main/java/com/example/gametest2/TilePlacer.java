package com.example.gametest2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class TilePlacer extends androidx.appcompat.widget.AppCompatImageView implements View.OnClickListener {
    Board brd;
    private int letterValue; // point value of the letter on the tile
    private int x; // The x-coordinate of the tile on the board
    private int y; // The y-coordinate of the tile on the board

    public TilePlacer(Context context, AttributeSet attrs) {
        super(context, attrs);
        brd = new Board(context, attrs);
        setOnClickListener(this);
    }

    public void onClick(View v) {
        if (this.x == -1 || this.y == -1) {
            return;
        }

        brd.placeTile(this,this.x,this.y);

    }

    public void placeTileOnBoard(int x, int y) {
        this.x = x;
        this.y = y;
        //place tile on the board
    }

}