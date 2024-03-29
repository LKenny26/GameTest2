package com.example.gametest2.views;

import com.example.gametest2.ScrabbleGameState;
import com.example.gametest2.Square;
import com.example.gametest2.Tile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class Board extends SurfaceView implements View.OnTouchListener {
    // Set the dimensions of the board
    private String message;
    private int tileCounter;
    protected ScrabbleGameState state;
    public static final int BOARD_SIZE = 15;
    public Square[][] squares;
    public Tile[] playerTiles = new Tile[7];
    public Tile[] computerTiles = new Tile[7];

    public Tile[][] boardTiles = new Tile[BOARD_SIZE][BOARD_SIZE];
    private int squareSize;
    private int bottomTileSize = 150;
    private Paint text;
    private int playerID = 0;

    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        squares = new Square[BOARD_SIZE][BOARD_SIZE];
        message = "";
        text = new Paint();
        text.setColor(Color.WHITE);
        tileCounter = 14;

        //make the board an onTouchListener
        this.setOnTouchListener(this);

        //make the computer's tiles
        for (int i = 0; i < 7; i++) {
            computerTiles[i] = new Tile(0, 0, 0, 0, false);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //make the size of the bottom row tiles
        bottomTileSize = w / 7;
        squareSize = Math.min(w, h) / BOARD_SIZE;

        //text size
        text.setTextSize(w/25);

        //make the tiles
        for (int i = 0; i < 7; i++) {
            playerTiles[i] = new Tile(bottomTileSize * i, BOARD_SIZE * squareSize, bottomTileSize + bottomTileSize * i, BOARD_SIZE * squareSize + bottomTileSize, false);
            playerTiles[i].setCoords(bottomTileSize * i, BOARD_SIZE * squareSize, bottomTileSize + bottomTileSize * i, BOARD_SIZE * squareSize + bottomTileSize);
        }


        // Define special squares
        int[][] specialSquares = {
                {0, 0, Square.TW},
                {0, 3, Square.DL},
                {0, 7, Square.TW},
                {0, 11, Square.DL},
                {0, 14, Square.TW},
                {1, 1, Square.DW},
                {1, 5, Square.TL},
                {1, 9, Square.TL},
                {1, 13, Square.DW},
                {2, 2, Square.DW},
                {2, 6, Square.DL},
                {2, 8, Square.DL},
                {2, 12, Square.DW},
                {3, 0, Square.DL},
                {3, 3, Square.DW},
                {3, 7, Square.DL},
                {3, 11, Square.DW},
                {3, 14, Square.DL},
                {4, 4, Square.DW},
                {4, 10, Square.DW},
                {5, 1, Square.TL},
                {5, 5, Square.TL},
                {5, 9, Square.TL},
                {5, 13, Square.TL},
                {6, 2, Square.DL},
                {6, 6, Square.DL},
                {6, 8, Square.DL},
                {6, 12, Square.DL},
                {7, 0, Square.TW},
                {7, 3, Square.DL},
                {7, 7, Square.STAR},
                {7, 11, Square.DL},
                {7, 14, Square.TW},
                {8, 2, Square.DL},
                {8, 6, Square.DL},
                {8, 8, Square.DL},
                {8, 12, Square.DL},
                {9, 1, Square.TL},
                {9, 5, Square.TL},
                {9, 9, Square.TL},
                {9, 13, Square.TL},
                {10, 4, Square.DW},
                {10, 10, Square.DW},
                {11, 0, Square.DL},
                {11, 3, Square.DW},
                {11, 7, Square.DL},
                {11, 11, Square.DW},
                {11, 14, Square.DL},
                {12, 2, Square.DW},
                {12, 6, Square.DL},
                {12, 8, Square.DL},
                {12, 12, Square.DW},
                {13, 1, Square.DW},
                {13, 5, Square.TL},
                {13, 9, Square.TL},
                {13, 13, Square.DW},
                {14, 0, Square.TW},
                {14, 3, Square.DL},
                {14, 7, Square.TW},
                {14, 11, Square.DL},
                {14, 14, Square.TW},
        };

        // Create squares and set their colors
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int squareType = Square.REGULAR;

                // Check if this square is a special square
                for (int[] specialSquare : specialSquares) {
                    if (specialSquare[0] == row && specialSquare[1] == col) {
                        squareType = specialSquare[2];
                        break;
                    }
                }

                // Create the square
                int left = col * squareSize;
                int top = row * squareSize;
                int right = (col + 1) * squareSize;
                int bottom = (row + 1) * squareSize;
                squares[row][col] = new Square(left, top, right, bottom, squareType);
                //create the tiles empty starts as true
                //boardTiles[row][col] = state.boardTiles[row][col];
                boardTiles[row][col] = new Tile(left, top, right, bottom, true);

                // Set the square's color based on its type
                switch (squareType) {
                    case Square.CENTER:
                        squares[row][col].setColor(Color.rgb(245, 203, 166)); // Orange
                        break;
                    case Square.TL:
                        squares[row][col].setColor(Color.rgb(167, 175, 244)); // Lilac
                        break;
                    case Square.DL:
                        squares[row][col].setColor(Color.rgb(166, 245, 244)); // Blue
                        break;
                    case Square.TW:
                        squares[row][col].setColor(Color.rgb(255, 0, 0)); // Red
                        break;
                    case Square.DW:
                        squares[row][col].setColor(Color.rgb(166, 245, 187)); // Green
                        break;
                    case Square.STAR:
                        squares[row][col].setColor(Color.rgb(244, 167, 187)); //Pink
                        break;
                    default:
                        squares[row][col].setColor(Color.rgb(255, 255, 255)); // White
                        break;
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the squares
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                //boardTiles = state.boardTiles;
                squares[row][col].draw(canvas); //squares
                boardTiles[row][col].onDraw(canvas); //tiles on the board
            }
        }
        //black lines
        Paint line = new Paint();
        line.setColor(Color.BLACK);
        line.setStrokeWidth(4);
        // Draw the horizontal lines
        for (int i = 0; i < BOARD_SIZE + 1; i++) {
            int startX = 0;
            int startY = i * squareSize;
            int stopX = BOARD_SIZE * squareSize;
            int stopY = startY;
            canvas.drawLine(startX, startY, stopX, stopY, line);
        }

        // Draw the vertical lines
        for (int i = 0; i < BOARD_SIZE + 1; i++) {
            int startX = i * squareSize;
            int startY = 0;
            int stopX = startX;
            int stopY = BOARD_SIZE * squareSize;
            canvas.drawLine(startX, startY, stopX, stopY, line);
        }

        //draw the player's tiles
        for (int i = 0; i < 7; i++) {
            playerTiles[i].onDraw(canvas);
        }

        //draw the message
        canvas.drawText(message, 0, BOARD_SIZE * squareSize + 4 * bottomTileSize / 3 , text);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(playerID != 0){
            return true;
        }
        if (event.getAction() != MotionEvent.ACTION_UP) {
            //check if a bottom tile was clicked and set it to be the selected tile
            float x = event.getX();
            float y = event.getY();
            for (int i = 0; i < 7; i++) {
                if (x < playerTiles[i].getR() && x > playerTiles[i].getL() && y > playerTiles[i].getT() && y < playerTiles[i].getB()) {
                    for (int j = 0; j < 7; j++) {
                        playerTiles[j].setSelected(false);
                    }
                    playerTiles[i].setSelected(true);
                    break;
                }
            }
            return true;
        }
        //get the coords of the touch
        float x = event.getX();
        float y = event.getY();



        //check if the board was clicked on and  if the tile needs to be swapped with selected. If none selected then no swap
        boolean selected = false;
        int r = -1;
        int c = -1;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (x < boardTiles[row][col].getR() && x > boardTiles[row][col].getL() && y > boardTiles[row][col].getT() && y < boardTiles[row][col].getB()) {
                    r = row;
                    c = col;
                    for (int i = 0; i < 7; i++) {
                        if (playerTiles[i].isSelected() && boardTiles[row][col].getEmpty()) {
                            Tile.swap(boardTiles[row][col], playerTiles[i]);
                            break;
                        }
                        if(playerTiles[i].getSelected()){
                            selected = true;
                        }
                    }
                }
            }
        }

        //redraw
        this.invalidate();
        return true;
    }

    public void setMessage(String m){
        message = m;
        this.invalidate();
    }

    public String getMessage(){
        return message;
    }

    public void setState(ScrabbleGameState state) {
        this.state = state;
    }

    public void addToTileCounter(int add) {
        tileCounter = tileCounter + add;
    }

    public int getTileCounter(){return tileCounter;}

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
}