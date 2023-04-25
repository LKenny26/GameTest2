package com.example.gametest2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    ScrabbleGameState gs = new ScrabbleGameState();

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void idIsCorrect() {
        assertEquals(0, gs.getPlayerID());

    }

    @Test
    public void iIsCorrect() {
        gs.setPlayerOneScore(49);
        assertEquals(49, gs.getPlayerOneScore());

    }

    @Test
    public void idCorrect() {
        gs.setPlayerTwoScore(50);
        assertEquals(50, gs.getPlayerTwoScore());
    }
}
