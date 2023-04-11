package com.example.gametest2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Tile {
    private Rect rect;
    private Paint paint;
    private Paint outline;
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
    }

    public void onDraw(Canvas canvas){
        paint.setColor(Color.rgb(244, 248, 181));
        canvas.drawRect(rect, paint);
        canvas.drawLine(l,t,l,b,outline);
        canvas.drawLine(l,t,r,t,outline);
        canvas.drawLine(r,t,r,b,outline);
        canvas.drawLine(l,b,r,b,outline);
    }
}
