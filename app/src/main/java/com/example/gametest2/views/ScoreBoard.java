package com.example.gametest2.views;

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

    //ScrabbleHumanPlayer sgp;
    Paint text = new Paint();
    private int startX;
    private int endX;
    private int startY;
    private int endY;

    ScrabbleHumanPlayer shm;
    MainActivity ma = new MainActivity();
    ScrabbleGameState sgs;
    private int threeQuartersX;

    Board bd;

    public ScoreBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        //lines = new Paint();
        lines.setColor(Color.WHITE);
        lines.setStyle(Paint.Style.STROKE);
        lines.setStrokeWidth(5);
        text.setColor(Color.WHITE);
        text.setTextSize(45);
        bd = new Board(context, attrs);
        sgs = new ScrabbleGameState();
    }

    //public void setName(String name){
      //  shm = new ScrabbleHumanPlayer(name);

    //}

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        startX = 50;
        endX= 1050;
        startY = 10;
        endY = 10;
        threeQuartersX = 800;
        int i;
        canvas.drawText("Players", startX+10, startY+40, text);
        canvas.drawText("Scores", threeQuartersX+10, startY+40, text);
        for(i = 1; i <= 6; i++){
            //canvas.drawLine(left , top + (i*10), right, bottom - (i*10), lines);
        canvas.drawLine(startX, (i*50)+startY, endX, (i*50)+endY, lines);

        }
        canvas.drawLine(startX, startY, startX, endY+(6*50), lines);
        canvas.drawLine(endX, startY, endX, endY+(6*50), lines);
        canvas.drawLine(threeQuartersX, startY, threeQuartersX, endY+(6*50), lines);

        //player names:
        //canvas.drawText("YOU", (25)+startX, startY+85, text);
       // canvas.drawText("OPPONENT", (25)+startX, startY+138, text);
        if(sgs.getPlayerID() == 0){
            canvas.drawText("OPPONENT", (25)+startX, startY+138, text);
            text.setColor(Color.YELLOW);
            text.setStrokeWidth(6);
            canvas.drawText("YOU", (25)+startX, startY+85, text);
        }
        else if(sgs.getPlayerID() == 1){
            canvas.drawText("YOU", (25)+startX, startY+85, text);
            text.setColor(Color.YELLOW);
            text.setStrokeWidth(6);
            canvas.drawText("OPPONENT", (25)+startX, startY+138, text);
        }
        else{
            System.out.println("ids not working correctly");
        }
    }

    public int calcScore(){
        if(sgs.getPlayerID() == 0){
            //shm.
        }
        return 0;
    }
}
