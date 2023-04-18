package com.example.gametest2;

import static org.junit.Assert.*;

import org.junit.Test;

public class TileTest {

    @Test
    public void setLetters() {
    }

    @Test
    public void getLetter() {
    }

    @Test
    public void getChar() {
    }

    @Test
    public void onDraw() {
    }

    @Test
    public void setCoords() {
    }

    @Test
    public void isSelected() {
    }

    @Test
    public void setSelected() {
    }

    @Test
    public void setLet() {
    }

    @Test
    public void testGetL() {
        Tile tile = new Tile(0, 0, 10, 10, false);
        assertEquals(0, tile.getL());
    }

    @Test
    public void testGetR() {
        Tile tile = new Tile(0, 0, 10, 10, false);
        assertEquals(10, tile.getR());
    }

    @Test
    public void testGetT() {
        Tile tile = new Tile(0, 0, 10, 10, false);
        assertEquals(0, tile.getT());
    }

    @Test
    public void testGetB() {
        Tile tile = new Tile(0, 0, 10, 10, false);
        assertEquals(10, tile.getB());
    }

    @Test
    public void getPoints() {
    }

    @Test
    public void setPoints() {
    }

    @Test
    public void getEmpty() {
    }

    @Test
    public void setEmpty() {
    }

    @Test
    public void swap() {
    }
}