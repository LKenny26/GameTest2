package com.example.gametest2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Square implements View.OnTouchListener {


    // Constants for square types
    public static final int REGULAR = 0;
    public static final int CENTER = 1;
    public static final int DL = 2;
    public static final int TL = 3;
    public static final int DW = 4;
    public static final int TW = 5;
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
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(12);
            paint.setColor(Color.WHITE);
            paint.setAntiAlias(true);

            Path path = new Path();
            float midX = rect.centerX();
            float midY = rect.centerY();
            float outerRadius = rect.width() / 10f;
            float innerRadius = outerRadius / 2.5f;
            float degreesPerPoint = 360f / 5;

            // Calculate the points of the star
            for (int i = 0; i < 5; i++) {
                float outerX = (float) (midX + outerRadius * Math.cos(Math.toRadians(i * degreesPerPoint - 90)));
                float outerY = (float) (midY + outerRadius * Math.sin(Math.toRadians(i * degreesPerPoint - 90)));
                float innerX = (float) (midX + innerRadius * Math.cos(Math.toRadians((i + 0.5f) * degreesPerPoint - 90)));
                float innerY = (float) (midY + innerRadius * Math.sin(Math.toRadians((i + 0.5f) * degreesPerPoint - 90)));

                if (i == 0) {
                    path.moveTo(outerX, outerY);
                } else {
                    path.lineTo(outerX, outerY);
                }

                path.lineTo(innerX, innerY);
            }

            path.close();
            canvas.drawPath(path, paint);
        }
        else if(type == TL){
            Paint textPaint = new Paint();
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(rect.width()/2);
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("TL", rect.centerX(), rect.centerY()+rect.width()/6, textPaint);
        }
        else if(type == DL){
            Paint textPaint = new Paint();
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(rect.width()/2);
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("DL", rect.centerX(), rect.centerY()+rect.width()/6, textPaint);
        }
        else if(type == DW){
            Paint textPaint = new Paint();
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(rect.width()/2);
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("DW", rect.centerX(), rect.centerY()+rect.width()/6, textPaint);
        }
        else if(type == TW){
            Paint textPaint = new Paint();
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(rect.width()/2);
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("TW", rect.centerX(), rect.centerY()+rect.width()/6, textPaint);
        }
    }
    public void setText(String word){

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

}