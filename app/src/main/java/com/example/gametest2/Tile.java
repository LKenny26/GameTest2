package com.example.gametest2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class Tile{
    private Rect rect;
    private Paint paint;
    private Paint outline;
    boolean selected;
    boolean empty;
    boolean confirmed;
    private Paint let;
    private Paint pts;
    char letter;
    int points;
    int l;
    int r;
    int t;
    int b;
    Random rand = new Random();

    public Tile(int left, int top, int right, int bottom, boolean emp) {
        rect = new Rect(left, top, right, bottom);
        pts = new Paint();
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
        let.setTextSize(3*(right-left)/4);
        pts.setColor(Color.BLACK);
        pts.setTextSize((right-left)/4);
        empty = emp;
        confirmed = false;
        this.setLetters();
    }

    public void setLetters(){
        int tileNumber;
        char letter = '!';
        int point = 0;
        //leaves out blank tiles
        tileNumber = rand.nextInt(100);
        if (tileNumber <= 6) {
            letter = 'A';
            point = 1;
        } else if (tileNumber <= 9) {
            letter = 'B';
            point = 3;
        } else if (tileNumber <= 12) {
            letter = 'C';
            point = 3;
        } else if (tileNumber <= 16) {
            letter = 'D';
            point = 2;
        } else if (tileNumber <= 21) {
            letter = 'E';
            point = 1;
        } else if (tileNumber <= 25) {
            letter = 'F';
            point = 4;
        } else if (tileNumber <= 30) {
            letter = 'G';
            point = 2;
        } else if (tileNumber <= 35) {
            letter = 'H';
            point = 4;
        } else if (tileNumber <= 41) {
            letter = 'I';
            point = 1;
        } else if (tileNumber <= 46) {
            letter = 'J';
            point = 8;
        } else if (tileNumber <= 50) {
            letter = 'K';
            point = 5;
        } else if (tileNumber <= 55) {
            letter = 'L';
            point = 1;
        } else if (tileNumber <= 60) {
            letter = 'M';
            point = 3;
        } else if (tileNumber <= 65) {
            letter = 'N';
            point = 1;
        } else if (tileNumber <= 70) {
            letter = 'O';
            point = 1;
        } else if (tileNumber <= 75) {
            letter = 'P';
            point = 3;
        } else if (tileNumber <= 80) {
            letter = 'Q';
            point = 10;
        } else if (tileNumber <= 85) {
            letter = 'R';
            point = 1;
        } else if (tileNumber <= 90) {
            letter = 'S';
            point = 1;
        } else if (tileNumber <= 85) {
            letter = 'T';
            point = 1;
        } else if (tileNumber <= 88) {
            letter = 'U';
            point = 3;
        } else if (tileNumber <= 91) {
            letter = 'V';
            point = 4;
        } else if (tileNumber <= 94) {
            letter = 'W';
            point = 4;
        } else if (tileNumber <= 97) {
            letter = 'X';
            point = 8;
        } else if (tileNumber <= 98) {
            letter = 'Y';
            point = 4;
        } else if (tileNumber <= 100) {
            letter = 'Z';
            point = 10;
        }
        this.letter = letter;
        this.points = point;
    }
    public String getLetter(){
        String lett = String.valueOf(letter);
        return lett.toUpperCase();
    }

    public char getChar() {
        return letter;
    }

    public void onDraw(Canvas canvas) {
        //dont draw an empty tile
        if (empty) {
            return;
        }

        //set the color to a nice pale yellow
        paint.setColor(Color.rgb(244, 248, 181));
        if (selected) {
            //selected tiles get a bright yellow
            paint.setColor(Color.rgb(255, 255, 0));
        }

        //draw the rect
        canvas.drawRect(rect, paint);

        //draws the letter
        canvas.drawText(this.getLetter(), l + (r-l) / 6, b - (b - t) / 4, let);
        canvas.drawText(Integer.toString(this.getPoints()), l + 3 * (r-l) / 4, b - (b-t)/4, pts);

        //draw the outline
        canvas.drawLine(l, t, l, b, outline);
        canvas.drawLine(l, t, r, t, outline);
        canvas.drawLine(r, t, r, b, outline);
        canvas.drawLine(l, b, r, b, outline);

    }

    public void setCoords(int left, int top, int right, int bottom) {
        l = left;
        t = top;
        r = right;
        b = bottom;
    }
    public boolean isSelected(){
        return selected;
    }

    public void setSelected(boolean bool){
        selected = bool;
    }

    public void setLet(char l) {
        letter = l;
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

    public void setPoints(int p) {
        points = p;
    }


    public boolean getEmpty(){
        return empty;
    }
    public void setEmpty(boolean bool){
        empty = bool;
    }

    public static void swap(Tile tile1, Tile tile2) {
        char tempLetter = tile1.getChar();
        tile1.setLet(tile2.getChar());
        tile2.setLet(tempLetter);

        int tempPoints = tile1.getPoints();
        tile1.setPoints(tile2.getPoints());
        tile2.setPoints(tempPoints);

        boolean tempEmpty = tile1.getEmpty();
        tile1.setEmpty(tile2.getEmpty());
        tile2.setEmpty(tempEmpty);

        if (tile1.getEmpty() || tile2.getEmpty()) {

        }
        else {
            boolean tempSelected = tile1.getSelected();
            tile1.setSelected(tile2.getSelected());
            tile2.setSelected(tempSelected);
        }


    }
    public boolean getConfirmed() {return confirmed;}
    public void setConfirmed(boolean bool) {
        confirmed = bool;
    }
    public boolean getSelected() {return selected;}

}

