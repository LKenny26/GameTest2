package com.example.gametest2;

import android.graphics.Canvas;
import android.view.SurfaceView;

import com.example.GameFramework.Game;

public class GameView extends SurfaceView {
    private GameModel gm;

    //constructor
    public GameView() {
        //super();
        gm = new GameModel();
    }

    //get model
    public GameModel getGameModel(){
        return gm;
    }
    public void onDraw(Canvas canvas){


    }
}
