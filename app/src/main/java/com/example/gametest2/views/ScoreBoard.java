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
        super(context, attrs);
        setWillNotDraw(false);
        lines.setColor(Color.WHITE);
        lines.setStyle(Paint.Style.STROKE);
        lines.setStrokeWidth(5);
        text.setColor(Color.WHITE);
        text.setTextSize(45);
        playerID = 0;
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        startX = 50;
        endX= 1050;
        startY = 10;
        endY = 10;
        threeQuartersX = 800;
        text.setColor(Color.WHITE);
        int i;
        canvas.drawText("Players", startX+10, startY+40, text);
        canvas.drawText("Scores", threeQuartersX+10, startY+40, text);
        for(i = 1; i <= 6; i++){
            canvas.drawLine(startX, (i*50)+startY, endX, (i*50)+endY, lines);
        }
        canvas.drawLine(startX, startY, startX, endY+(6*50), lines);
        canvas.drawLine(endX, startY, endX, endY+(6*50), lines);
        canvas.drawLine(threeQuartersX, startY, threeQuartersX, endY+(6*50), lines);

        if(playerID == 0){
            canvas.drawText("OPPONENT", (25)+startX, startY+138, text);
            canvas.drawText(playerTwoScore + "", (800)+startX, startY + 138, text);
            text.setColor(Color.YELLOW);
            text.setStrokeWidth(8);
            canvas.drawText("YOU", (25)+startX, startY+85, text);
            canvas.drawText(playerOneScore + "", (800)+startX, startY+85, text);
        }
        else if(playerID == 1){
            canvas.drawText("YOU", (25)+startX, startY+85, text);
            canvas.drawText(playerOneScore + "", (800)+startX, startY+85, text);
            text.setColor(Color.YELLOW);
            text.setStrokeWidth(8);
            canvas.drawText("OPPONENT", (25)+startX, startY+138, text);
            canvas.drawText(playerTwoScore + "", (800)+startX, startY + 138, text);
        }
        else{
            System.out.println("ids not working correctly");
        }
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
