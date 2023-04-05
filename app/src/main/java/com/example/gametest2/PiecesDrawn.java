package com.example.gametest2;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class PiecesDrawn extends SurfaceView {

    public PiecesDrawn(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void ScoreBoard(Canvas canvas){
        //method that draws the scoreboard
    }

    protected void PlayerPieces(Canvas canvas, int x, int y) {
        //players 7 tiles that will be on view Pieces
    }
    protected void PiecesPlayed(Canvas canvas, int x, int y){
        //takes the x, y position of the tile and places on board accordingly
    }
}
