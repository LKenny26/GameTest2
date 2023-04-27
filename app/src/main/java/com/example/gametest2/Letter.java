package com.example.gametest2;

public class Letter implements Comparable<Letter> {
    private int row;
    private int col;
    private char letter;

    public Letter(int row, int col, char letter) {
        this.row = row;
        this.col = col;
        this.letter = letter;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getLetter() {
        return letter;
    }

    @Override
    public int compareTo(Letter other) {

        if (this.row == other.row) {
            return Integer.compare(this.col, other.col);
        } else {
            return Integer.compare(this.row, other.row);
        }
    }



}