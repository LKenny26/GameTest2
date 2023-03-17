package com.example.gametest2;

import com.example.GameFramework.Game;

public class GameView {
    private GameModel gm;

    public GameView() {
        gm = new GameModel();
    }

    public GameModel getGameModel(){
        return gm;
    }

}
