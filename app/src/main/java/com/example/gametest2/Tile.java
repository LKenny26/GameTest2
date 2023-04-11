package com.example.gametest2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class Tile implements View.OnTouchListener{
    private Rect rect;
    private Paint paint;
    private Paint outline;
    boolean selected;
    char letter;
    int points;
    int l;
    int r;
    int t;
    int b;

    public Tile(int left, int top, int right, int bottom, char initLetter, int initPoints) {
        rect = new Rect(left, top, right, bottom);

        points = initPoints;
        letter = initLetter;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        l = left;
        r = right;
        t = top;
        b = bottom;
        outline = new Paint();
        outline.setColor(Color.BLACK);
        outline.setStrokeWidth(4);
        selected = false;
    }

    public void onDraw(Canvas canvas){
        paint.setColor(Color.rgb(244, 248, 181));
        if (selected){
            paint.setColor(Color.rgb(255,255,0));
        }
        canvas.drawRect(rect, paint);
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

