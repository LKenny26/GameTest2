package com.example.gametest2;

import com.example.GameFramework.Game;

public class GameView {
    private GameModel gm;
    private GameState gs;

    public GameView() {
        gm = new GameModel();
        gs = new GameState();
    }

    public GameModel getGameModel(){
        return gm;
    }

    public GameState getGameState(){
        return gs;
    }
}
