package com.example.gametest2.views;

import com.example.GameFramework.actionMessage.GameAction;
import com.example.gametest2.Letter;
import com.example.gametest2.R;
import com.example.gametest2.ScrabbleController;
import com.example.gametest2.ScrabbleGameState;
import com.example.gametest2.Square;
import com.example.gametest2.Tile;
import com.example.gametest2.actions.PlayWordAction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.Collections;

import java.util.ArrayList;
import java.util.Comparator;

public class Board extends SurfaceView implements View.OnTouchListener{
    // Set the dimensions of the board

    protected ScrabbleGameState state;
    private static Board instance;
    public static final int BOARD_SIZE = 15;
    public Square[][] squares;
    public Tile[] playerTiles = new Tile[7];
    public Tile[][] boardTiles = new Tile[BOARD_SIZE][BOARD_SIZE];
    private int squareSize;
    private int bottomTileSize = 150;

    Button b;
    ScrabbleController sc;
    ArrayList<Letter> letters;
    Letter let;
    private StringBuilder sb;
    String concatenatedString;
    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);
        instance = null;
        setWillNotDraw(false);
        squares = new Square[BOARD_SIZE][BOARD_SIZE];
        letters = new ArrayList<>();

        sb =  new StringBuilder();
        //make the board an ontouchlistener
        this.setOnTouchListener(this);
        sc = new ScrabbleController(this);
        b = findViewById(R.id.removeTiles);
        //b.setOnClickListener(sc);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //make the size of the bottom row tiles
        bottomTileSize = w/7;
        squareSize = Math.min(w, h) / BOARD_SIZE;
        //make the tiles

        for(int i = 0; i < 7; i++) {
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
                boardTiles[row][col] = new Tile(left,top,right,bottom, true);

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
                        squares[row][col].setColor(Color.rgb(244,167,187)); //Pink
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
        for(int i = 0; i < 7; i++) {
            playerTiles[i].onDraw(canvas);
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        //get the coords of the touch
        float x = event.getX();
        float y = event.getY();

        //check if a bottom tile was clicked and set it to be the selected tile
        for(int i = 0; i < 7; i++) {
            if (x < playerTiles[i].getR() && x > playerTiles[i].getL() && y > playerTiles[i].getT() && y < playerTiles[i].getB()) {
                for(int j = 0; j < 7;j++) {
                    playerTiles[j].setSelected(false);
                }
                playerTiles[i].setSelected(true);
                break;
            }
        }

        //check if the board was clicked on and  if the tile needs to be swapped with selected. If none selected then no swap
        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++) {
                if (x < boardTiles[row][col].getR() && x > boardTiles[row][col].getL() && y > boardTiles[row][col].getT() && y < boardTiles[row][col].getB()) {
                    for (int i = 0; i < 7; i++){
                        if (playerTiles[i].isSelected() && boardTiles[row][col].getEmpty()) {
                            Tile.swap(boardTiles[row][col], playerTiles[i]);
                            let = new Letter(row,col, playerTiles[i].getChar());
                            letters.add(let);





                        }
                    }
                    break;
                }
            }
        }

        //redraw
        this.invalidate();
        return true;
    }
    public ArrayList<Letter> getAR(){
        return letters;
    }
    public StringBuilder getSB(){
        Collections.sort(letters);
        for (Letter l : letters) {
            sb.append(l.getLetter());
        }
        return sb;
    }


    public void setPlayerTiles(Tile[] playerTiles) {
        for (int i = 0; i < 7; i++){
            this.playerTiles[i] = playerTiles[i];
        }
    }
    /*public static void organizeSB(StringBuilder sb) {
        // Split the StringBuilder into an array of characters
        char[] chars = sb.toString().toCharArray();

        // Create an array of integer pairs to hold the [row][col] values for each character
        int[][] positions = new int[chars.length][2];

        // Fill in the positions array with the [row][col] values for each character
        for (int i = 0; i < chars.length; i++) {
            positions[i][0] = chars[i] / BOARD_SIZE; // row value
            positions[i][1] = chars[i] % BOARD_SIZE; // col value
        }

        // Sort the positions array by row value, then by column value
        Arrays.sort(positions, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(a[0], b[0]);
            }
        });*/

        // Rebuild the StringBuilder in the sorted order



    public Tile[] getPlayerTiles(){
        return playerTiles;
    }

    //public void shuffle(Tile[] playerTiles) {
      //  Collections.shuffle(Arrays.asList(getPlayerTiles()));
    //}

   public void setState(ScrabbleGameState state){
        this.state = state;
    }//state never gets used is this necessary
    //needed in human player for recieveInfo
}