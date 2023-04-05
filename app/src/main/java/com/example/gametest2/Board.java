package com.example.gametest2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Board extends View {
    // Set the dimensions of the board
    private static final int NUM_ROWS = 15;
    private static final int NUM_COLS = 15;
    private static final int CELL_SIZE = 60;

    // Set the colors for different elements on the board
    private Paint red = new Paint();
    private Paint lblue = new Paint();
    private Paint black = new Paint();
    private Paint white = new Paint();
    private Paint dBlue = new Paint();
    private Paint pink = new Paint();

    public Board(Context context) {
        super(context);

        // Set the colors for different elements on the board
        red.setColor(Color.RED);
        lblue.setColor(Color.BLUE);
        black.setColor(Color.BLACK);
        white.setColor(Color.WHITE);
        dBlue.setColor(Color.DKGRAY);
        pink.setColor(Color.MAGENTA);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the squares on the board
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;
                canvas.drawRect(x, y, x + CELL_SIZE, y + CELL_SIZE, white);
                canvas.drawRect(x + 2, y + 2, x + CELL_SIZE - 2, y + CELL_SIZE - 2, lblue);
            }
        }

        // Draw the special squares on the board
        canvas.drawRect(CELL_SIZE * 0, CELL_SIZE * 0, CELL_SIZE * 3, CELL_SIZE * 3, red);
        canvas.drawRect(CELL_SIZE * 0, CELL_SIZE * 7, CELL_SIZE * 3, CELL_SIZE * 10, dBlue);
        canvas.drawRect(CELL_SIZE * 0, CELL_SIZE * 14, CELL_SIZE * 3, CELL_SIZE * 15, red);
        canvas.drawRect(CELL_SIZE * 7, CELL_SIZE * 0, CELL_SIZE * 8, CELL_SIZE * 3, dBlue);
        canvas.drawRect(CELL_SIZE * 7, CELL_SIZE * 7, CELL_SIZE * 8, CELL_SIZE * 8, pink);
        canvas.drawRect(CELL_SIZE * 7, CELL_SIZE * 14, CELL_SIZE * 8, CELL_SIZE * 15, dBlue);
        canvas.drawRect(CELL_SIZE * 14, CELL_SIZE * 0, CELL_SIZE * 15, CELL_SIZE * 3, red);
        canvas.drawRect(CELL_SIZE * 14, CELL_SIZE * 7, CELL_SIZE * 15, CELL_SIZE * 10, dBlue);
        canvas.drawRect(CELL_SIZE * 14, CELL_SIZE * 14, CELL_SIZE * 15, CELL_SIZE * 15, red);
    }
}