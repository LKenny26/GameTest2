package com.example.gametest2;

import com.example.GameFramework.Game;

public class GameView {
    private GameModel gm;

    //constructor
    public GameView() {
        gm = new GameModel();
    }

    //get model
    public GameModel getGameModel(){
        return gm;
    }

}
