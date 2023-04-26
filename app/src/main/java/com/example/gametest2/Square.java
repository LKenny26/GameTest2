package com.example.gametest2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Square implements View.OnTouchListener {


    // Constants for square types
    public static final int REGULAR = 0;
    public static final int CENTER = 1;
    public static final int TW = 2;
    public static final int DW = 3;
    public static final int TL = 4;
    public static final int DL = 5;
    public static final int STAR = 6;

    // Square position and dimensions
    private Rect rect;

    // Square type
    private int type;

    // Paint object for coloring the square
    private Paint paint;

    public Square(int left, int top, int right, int bottom, int type) {
        rect = new Rect(left, top, right, bottom);
        this.type = type;

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);

        if (type == STAR) {
            // Draw a star in the center of the square
            Paint starPaint = new Paint();
            starPaint.setStyle(Paint.Style.STROKE);
            starPaint.setStrokeWidth(4);
            starPaint.setColor(Color.WHITE);

            int cx = rect.centerX();
            int cy = rect.centerY();
            int r = rect.width() / 10;

            canvas.drawLine(cx - r, cy, cx + r, cy, starPaint);
            canvas.drawLine(cx, cy - r, cx, cy + r, starPaint);
            canvas.drawLine(cx - r, cy - r, cx + r, cy + r, starPaint);
            canvas.drawLine(cx - r, cy + r, cx + r, cy - r, starPaint);
        }
        if(type == TW){
            Paint black = new Paint();
            black.setStyle(Paint.Style.STROKE);
            black.setStrokeWidth(4);
            black.setColor(Color.BLACK);

            int cx = rect.centerX();
            int cy = rect.centerY();


            canvas.drawText("TW", cx,cy,black);
        }
    }
    public void setText(String word){

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

}