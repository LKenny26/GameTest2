package com.example.gametest2;

import android.view.View;

import com.example.gametest2.views.Board;

public class ScrabbleController implements View.OnClickListener{
    private Board scrabbleBoard;

    public ScrabbleController(Board b) {
        scrabbleBoard = b;
    }

    @Override
    public void onClick(View v) {
        // Loop through all playerTiles and set isSelected to false
        for (int i = 0; i < 7; i++) {
            scrabbleBoard.playerTiles[i].setSelected(false);
        }

        // Loop through the boardTiles and swap back the tiles to their original state
        for (int row = 0; row < scrabbleBoard.BOARD_SIZE; row++) {
            for (int col = 0; col < scrabbleBoard.BOARD_SIZE; col++) {
                if (!scrabbleBoard.boardTiles[row][col].getEmpty()) {
                    Tile.swap(scrabbleBoard.boardTiles[row][col], scrabbleBoard.boardTiles[row][col].getOgTile());
                }
            }
        }

        // Redraw the view or perform any additional actions as needed
        scrabbleBoard.invalidate();
    }
}
