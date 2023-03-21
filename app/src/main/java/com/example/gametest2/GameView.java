package com.example.gametest2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

import com.example.GameFramework.Game;

public class GameView extends SurfaceView{
    private GameModel gm;

    //constructor
    public GameView(Context context, AttributeSet attrs) {
        super(context,attrs);
        gm = new GameModel();
    }

    //get model
    public GameModel getGameModel(){
        return gm;
    }


}
