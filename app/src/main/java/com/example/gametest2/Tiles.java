package com.example.gametest2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class Tiles extends SurfaceView {
    private Paint tile = new Paint();

    public Tiles(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        tile.setColor(Color.rgb(244,248,181));
        canvas.drawRect(0,0,50,50, tile);
    }
}
