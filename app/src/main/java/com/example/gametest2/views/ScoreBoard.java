package com.example.gametest2.views;

import com.example.gametest2.R;
import com.example.gametest2.ScrabbleGameState;
import com.example.gametest2.players.*;
import com.example.gametest2.MainActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import com.example.GameFramework.players.GameHumanPlayer;

public class ScoreBoard extends SurfaceView {

    Paint lines = new Paint();
    Paint text = new Paint();
    private int startX;
    private int endX;
    private int startY;
    private int endY;
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private int playerID;
    private int threeQuartersX;

    public ScoreBoard(Context context, AttributeSet attrs) {
        //CTOR
        super(context, attrs);
        setWillNotDraw(false);
        lines.setColor(Color.WHITE);
        lines.setStyle(Paint.Style.STROKE);
        lines.setStrokeWidth(5);
        text.setColor(Color.WHITE);
        playerID = 0;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //set the stuff for the drawing
        startX = w/50;
        endX = w - startX;
        startY = h/50;
        endY = h - startY;
        threeQuartersX = 3*(endX - startX)/4;
        text.setTextSize(w / 40);
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int fifth = (endY - startY)/5;
        int textSpace = (endY - startY)/8;

        text.setColor(Color.WHITE);
        canvas.drawText("Players", startX+textSpace, startY+textSpace, text);
        canvas.drawText("Scores", threeQuartersX+textSpace, startY+textSpace, text);

        //draw the box
        for(int i = 0; i < 6; i++){
            canvas.drawLine(startX, (i*fifth)+startY, endX, (i*fifth)+startY, lines);
        }
        canvas.drawLine(startX, startY, startX, endY, lines);
        canvas.drawLine(endX, startY, endX, endY, lines);
        canvas.drawLine(threeQuartersX, startY, threeQuartersX, endY, lines);


        //highlight the player
        if(playerID == 1){
            text.setColor(Color.YELLOW);
        }
        canvas.drawText("OPPONENT", (textSpace)+startX, startY + 2 * fifth + textSpace, text);
        canvas.drawText(playerTwoScore + "", (threeQuartersX)+textSpace, startY + 2 * fifth + textSpace, text);
        if(playerID == 0) {
            text.setColor(Color.YELLOW);
        }
        else {
            text.setColor(Color.WHITE);
        }
        text.setStrokeWidth(8);
        canvas.drawText("YOU", (textSpace)+startX, startY + fifth + textSpace, text);
        canvas.drawText(playerOneScore + "", (threeQuartersX)+textSpace, startY + fifth + textSpace, text);

    }

    public void setPlay1Score(int score) {
        playerOneScore = playerOneScore + score;
    }

    public void setPlayerTwoScore(int score){
        playerTwoScore = playerTwoScore + score;
    }

    public void setPlayerID(int id){
        playerID = id;
    }
}
