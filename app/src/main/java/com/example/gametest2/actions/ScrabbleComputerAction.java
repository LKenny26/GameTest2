package com.example.gametest2.actions;

import android.graphics.Point;

import com.example.GameFramework.Game;
import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;
import com.example.gametest2.Tile;

import java.util.ArrayList;

public class ScrabbleComputerAction extends GameAction {
    private ArrayList<Tile> tp;
    private ArrayList<Point> tilep;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ScrabbleComputerAction(GamePlayer player, ArrayList<Tile> tiles, ArrayList<Point> tilePositions) {
        super(player);
        tp = tiles;
        tilep = tilePositions;
    }

    public ArrayList<Tile> getTilesToPlace() {
        return tp;
    }

    public ArrayList<Point> getTilePoints() {
        return tilep;
    }
}
