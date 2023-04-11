package com.example.gametest2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class Tile implements View.OnTouchListener{
    private Rect rect;
    private Paint paint;
    private Paint outline;
    boolean selected;
    private Paint let;
    char letter;
    int points;
    int l;
    int r;
    int t;
    int b;

    Random rand = new Random();
    //int tileNumber = rand.nextInt(98);

    public Tile(int left, int top, int right, int bottom, char initLetter, int initPoints) {
        rect = new Rect(left, top, right, bottom);

        points = initPoints;
        letter = initLetter;
        paint = new Paint();
        let = new Paint();
        paint.setStyle(Paint.Style.FILL);
        l = left;
        r = right;
        t = top;
        b = bottom;
        outline = new Paint();
        outline.setColor(Color.BLACK);
        outline.setStrokeWidth(4);
        selected = false;
        let.setColor(Color.BLACK);
        let.setTextSize(115);
    }

    public void setLetters(char letter, int point){
        this.letter = letter;
        int tileNumber;
        //leaves out blank tiles
        for(int i = 0; i <= 100; i++){
            tileNumber = rand.nextInt(98);
            if(tileNumber <= 8){
                letter = 'A';
                point = 1;
            }
            else if(tileNumber <= 10){
                letter = 'B';
                point = 3;
            }
            else if(tileNumber <= 12){
                letter = 'C';
                point = 3;
            }
            else if(tileNumber <= 16){
                letter = 'D';
                point = 2;
            }
            else if(tileNumber <= 28){
                letter = 'E';
                point = 1;
            }
            else if(tileNumber <= 30){
                letter = 'F';
                point = 4;
            }
            else if(tileNumber <= 33){
                letter = 'G';
                point = 2;
            }
            else if(tileNumber <= 35){
                letter = 'H';
                point = 4;
            }
            else if(tileNumber <=44){
                letter = 'I';
                point = 1;
            }
            else if(tileNumber <= 45){
                letter = 'J';
                point = 8;
            }
            else if(tileNumber <= 46){
                letter = 'K';
                point = 5;
            }
            else if(tileNumber <= 50){
                letter = 'L';
                point = 1;
            }
            else if(tileNumber <= 52){
                letter = 'M';
                point = 3;
            }
            else if(tileNumber <= 58){
                letter = 'N';
                point = 1;
            }
            else if(tileNumber <= 66){
                letter = 'O';
                point = 1;
            }
            else if(tileNumber <= 68){
                letter = 'P';
                point = 3;
            }
            else if(tileNumber <= 69){
                letter = 'Q';
                point = 10;
            }
            else if(tileNumber <= 75){
                letter = 'R';
                point = 1;
            }
            else if(tileNumber <= 79){
                letter = 'S';
                point = 1;
            }
            else if(tileNumber <= 85){
                letter = 'T';
                point = 1;
            }
            else if(tileNumber <= 89){
                letter = 'U';
                point = 3;
            }
            else if(tileNumber <= 91){
                letter = 'V';
                point = 4;
            }
            else if(tileNumber <= 93){
                letter = 'W';
                point = 4;
            }
            else if(tileNumber <= 94){
                letter = 'X';
                point = 8;
            }
            else if(tileNumber <= 96){
                letter = 'Y';
                point = 4;
            }
            else if(tileNumber <= 97){
                letter = 'Z';
                point = 10;
            }
        }
        //return letter;
    }
    public String getLetter(){
        String lett = String.valueOf(letter);
        return lett.toUpperCase();
    }

    public void onDraw(Canvas canvas){
        paint.setColor(Color.rgb(244, 248, 181));
        if (selected){
            paint.setColor(Color.rgb(255,255,0));
        }
        canvas.drawRect(rect, paint);
        canvas.drawText(this.getLetter(), l+20, b - (b - t)/4, let);
        //issue: draws the same value on every letter, even though it iterates through the entire
        //for-loop, need to find a way to put different letters on the tiles
        canvas.drawLine(l,t,l,b,outline);
        canvas.drawLine(l,t,r,t,outline);
        canvas.drawLine(r,t,r,b,outline);
        canvas.drawLine(l,b,r,b,outline);

    }

    public boolean isSelected(){
        return selected;
    }

    public void setSelected(boolean bool){
        selected = bool;
    }

    public int getL(){
        return l;
    }

    public int getR(){
        return r;
    }

    public int getT(){
        return t;
    }

    public int getB(){
        return b;
    }

    public int getPoints(){
        return points;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (x < r && x > l && y > t && y < b) {
            setSelected(true);
        }
        v.invalidate();
        return true;
    }
}

